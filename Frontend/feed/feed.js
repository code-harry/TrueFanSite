let currentPage = 0;
const pageSize = 10;
let loading = false;
let allLoaded = false;



function getJwtPayload(token) {
  try {
    const base64Url = token.split('.')[1];
    const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    const jsonPayload = decodeURIComponent(
      atob(base64).split('').map(c =>
        '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2)
      ).join('')
    );
    return JSON.parse(jsonPayload);
  } catch (e) {
    console.error("Invalid token format", e);
    return null;
  }
}


function isTokenExpired(token) {
  const payload = getJwtPayload(token);
  if (!payload || !payload.exp) return true;
  return payload.exp < (Date.now() / 1000);
}




window.addEventListener('load', () => 
  {
  const token = localStorage.getItem('jwtToken');
  if(!token) {
    alert("You need to log in first.");
    window.location.href = '../loginPage/loginPage.html'; // redirect to login if no token
    return;
  }
  if (token && isTokenExpired(token)) {
    localStorage.removeItem('jwtToken');
    alert("Session expired. Please log in again.");
    // Redirect to login page if token is expired
    window.location.href = '../loginPage/loginPage.html';
  }
});





async function fetchStories(page, size) 
{
   loading = true;

  const token = localStorage.getItem("jwtToken"); // retrieve stored JWT

  if (!token) {
    alert("You need to log in first.");
    window.location.href = "../loginPage/loginPage.html"; // redirect to login if no token
    return;
  }

  const response = await fetch(`http://localhost:8080/api/stories?page=${page}&size=${size}`, {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
      "Authorization": `Bearer ${token}` // attach token here
    }
  });

  if (!response.ok) {
    loading = false;
    if (response.status === 401) {
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
    div.innerHTML = `<h2>${story.title}</h2><p><strong>Language:</strong> ${story.language}</p>
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
  if (allLoaded || loading) return;

  const scrollPosition = window.innerHeight + window.scrollY;
  const nearBottom = document.body.offsetHeight - 100; // 100px from bottom

  if (scrollPosition >= nearBottom) 
    {
    currentPage++;
    fetchStories(currentPage, pageSize).then(stories => 
      {
      appendStories(stories);
    });
  }
});
