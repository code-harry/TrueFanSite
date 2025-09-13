let currentPage = 0;
const pageSize = 10;
let loading = false;// This tells if the javascript code is in the middle of loading more stories or not
let allLoaded = false;// This indicates if all the stories have been loaded or not


// This function contains the logic to decode the JWT token and return the payload
function getJwtPayload(token) 
{
  try 
  {
    const base64Url = token.split('.')[1];
    const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    const jsonPayload = decodeURIComponent(
      atob(base64).split('').map(c =>
        '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2)
      ).join('')
    );
    return JSON.parse(jsonPayload);
  } 
  catch (e) 
  {
    console.error("Invalid token format", e);
    return null;
  }
}

// Function to check if a token is expired
function isTokenExpired(token) 
{
  // Getting the payload- which is the second part of the JWT token
  const payload = getJwtPayload(token);

  // If the payload is null or doesn't have an exp field, consider the token expired
  if (!payload || !payload.exp)
    {
      return true;
    }  

    // If the payload is less than the curent time, the token is expired otherwise the token is valid
  return payload.exp < (Date.now() / 1000);

}



// The first code that gets executed when the page is loaded
window.addEventListener('load', () => 
  {
    // getting the jwt token from local storage and storing it in the variable called token
  const token = localStorage.getItem('jwtToken');

  // If the token is not present, alert the user and redirect to login page
  if(!token) 
    {
    alert("You need to log in first.");
    window.location.href = '../loginPage/loginPage.html'; // redirect to login if no token
    return;
  }

  // If the token is present, check if it is expired
  if (token && isTokenExpired(token)) 
    {
    localStorage.removeItem('jwtToken');
    alert("Session expired. Please log in again.");
    // Redirect to login page if token is expired
    window.location.href = '../loginPage/loginPage.html';
  }
});




// Represents the function that fetches stories from the backend
async function fetchStories(page, size) 
{
  // Making the loading variable true to indicate that we are in the middle of loading more stories
   loading = true;

  // retrieve stored JWT
  const token = localStorage.getItem("jwtToken"); 

  if (!token || isTokenExpired(token)) 
    {
    alert("You need to log in first.");
    window.location.href = "../loginPage/loginPage.html"; // redirect to login if no token
    return;
  }

  const response = await fetch(`http://localhost:8080/api/stories?page=${page}&size=${size}`, 
    {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
      "Authorization": `Bearer ${token}` // attach token here
    }
  });

  if (!response.ok) 
    {
    loading = false;
    if (response.status === 401) 
      {
      alert("Session expired. Please log in again.");
      window.location.href = "/login.html";
    }
    throw new Error("Failed to fetch stories");
  }

  const stories = await response.json();
  loading = false;

  if (stories.length < size) {
    allLoaded = true; // No more stories to load
  }

  return stories;
}

function appendStories(stories) 
{
  const feed = document.getElementById('storyFeed');

  stories.forEach(story => 
    {
    const div = document.createElement('div');
    div.className = 'story-card';
    div.innerHTML = `<h2>Title of the story:${story.title}</h2><p><strong>Language:</strong> ${story.language}</p>
    <p><strong>Genre:</strong> ${story.genre}</p>
    <p><strong>Age:</strong> ${story.age}</p>
    <p><strong>Media:</strong> ${story.media}</p>`;
    div.onclick = () => {
      window.location.href = `../Story/story.html?title=${encodeURIComponent(story.title)}`;
    };
    feed.appendChild(div);
  });
}

// Initial load
fetchStories(currentPage, pageSize).then(stories => 
  {
  appendStories(stories);
});

// Listen for scroll event
window.addEventListener('scroll', () => 
  {
    // If the all stories are loaded or if we are already loading, do nothing
  if (allLoaded || loading)
    {
      return;
    } 

    // The pixel position of the bottom of the visible part of the page
  const scrollPosition = window.innerHeight + window.scrollY;


  const nearBottom = document.body.offsetHeight - 100; // 100px from bottom

  // If the user has scrolled to near the bottom, load more stories
  if (scrollPosition >= nearBottom) 
    {
      //increment the current page and fetch the stories for that page
    currentPage++;
    // calling the function
    fetchStories(currentPage, pageSize).then(stories => 
      {
      appendStories(stories);
    });
  }
});
