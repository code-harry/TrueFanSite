
//Authentication logic starts here
function getJwtPayload(token) 
{
  try {
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



// Authentication logic ends here


//function that will call the api to load the story
async function loadStory() 
{
    //getting the query parameters from the url
    const params = new URLSearchParams(window.location.search);
    // If no query parameters, exit the function
if(!params)
{
    return;
}



    //getting the query parameter with the key title
    const title = params.get("title");

    // If no title is provided, exit the function
    if(!title)
    {
        return
    }
    const messagesDiv = document.getElementById('messages'); // Get the messages div
    const storyDisplayDiv = document.getElementById('storyContentDisplay'); // Get the story display div

    // Clear previous messages and content
    messagesDiv.innerHTML = '';
    storyDisplayDiv.style.display = 'none'; // Hide story content by default

    // 1. Basic validation for the title on the client-side
    if (!title || title.trim() === '') {
        messagesDiv.innerHTML = '<p>Please provide a story title in the URL (e.g., ?title=YourStoryTitle).</p>';
        return;
    }

    // 2. Make the API call
    const response = await fetch(`http://localhost:8080/api/story?title=${encodeURIComponent(title)}`);

    // 3. Handle different HTTP status codes from the API
    if (response.status === 404) {
        messagesDiv.innerHTML = '<p>The story you are looking for was not found.</p>';
        return;
    }

    if (response.status === 400) {
        messagesDiv.innerHTML = '<p>Invalid request. Please ensure the title parameter is correct.</p>';
        return;
    }

    if (!response.ok) {
        messagesDiv.innerHTML = `<p>Error loading story: ${response.status} ${response.statusText}</p>`;
        return;
    }

    // 4. If response is OK (200), parse the JSON and display the story
    const story = await response.json();

    alert(story.title); // Pop-up the title for quick confirmation

    // Show the story content container
    storyDisplayDiv.style.display = 'block';

    // Update the HTML elements
    document.getElementById('storyTitle').innerText = 'Name: ' + story.title; // Add back "Name:"
    document.getElementById('storyContent').innerText = 'Content: ' + story.content; // Add back "Content:"
    document.getElementById('storyMedia').innerText = story.media;
    document.getElementById('storyLanguage').innerText = story.language;
    document.getElementById('storyGenre').innerText = story.genre;
    document.getElementById('storyAge').innerText = story.age;
}

loadStory();