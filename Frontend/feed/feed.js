"use strict"


























// --- Page Initialization ---
//Old code without JQuery
// document.addEventListener('DOMContentLoaded', () => {
//     const token = localStorage.getItem('jwtToken');
//     if (!token || isTokenExpired(token)) 
//       {
//         localStorage.removeItem('jwtToken');
//         alert("Session expired or you are not logged in. Please log in again.");
//         window.location.href = '../loginPage/loginPage.html';
//         return;
//     }
    
//     displayUsernameOnPage();
//     setupEventListeners();
//     loadMoreStories();
// });

//New code with JQuery
$(document).on("DOMContentLoaded", function() {
       const token = localStorage.getItem('jwtToken');
    if (!token || isTokenExpired(token)) 
      {
        localStorage.removeItem('jwtToken');
        alert("Session expired or you are not logged in. Please log in again.");
        window.location.href = '../loginPage/loginPage.html';
        return;
    }
    
    displayUsernameOnPage();
    setupEventListeners();
    loadMoreStories();
});



// --- Global Variables ---
let currentPage = 0;
const pageSize = 10;
let loading = false;
let allLoaded = false;


// --- Event Listener Setup ---
function setupEventListeners() {

    //Old code without JQuery
    // const homeButton = document.getElementById('homeButton');
    // if (homeButton) {
    //     homeButton.addEventListener('click', () => {
    //         window.location.href = '../Home/home.html'; 
    //     });
    // }

    //New code with JQuery
    $("#homeButton").on("click",function()
    {
        window.location.href = '../Home/home.html'; 
    });


    //Old code without JQuery
    // const logoutButton = document.getElementById('logoutButton');


    //New code with JQuery
    const logoutButton = $("#logoutButton")[0] ;

    if (logoutButton) 
    {
        logoutButton.addEventListener('click', handleLogout);
    }

    //Old code without jquery
    // window.addEventListener('scroll', () => {
    //     const { scrollTop, scrollHeight, clientHeight } = document.documentElement;
    //     if (clientHeight + scrollTop >= scrollHeight - 100) {
    //         loadMoreStories();
    //     }
    // });


    //New code with JQuery
    $(window).on('scroll', function() 
    {
        const { scrollTop, scrollHeight, clientHeight } = document.documentElement;
        if (clientHeight + scrollTop >= scrollHeight - 100) 
            {
            loadMoreStories();
        }
    });
}


// --- Main Functions ---

function displayUsernameOnPage() {
    const token = localStorage.getItem('jwtToken');
    if (!token) return;

    try {
        const payload = getJwtPayload(token);
        const username = payload.sub;

        const displayElement = document.getElementById('usernameDisplay');
        if (displayElement && username) {
            displayElement.textContent = `Hi, ${username}`;
        }
    } catch (e) {
        console.error("Could not display username:", e);
    }
}

async function handleLogout() {
    const token = localStorage.getItem('jwtToken');
    if (!token) {
        alert("No active session found.");
        window.location.href = '../loginPage/loginPage.html';
        return;
    }

    try {
        const response = await fetch('http://localhost:8080/auth/logout', {
            method: 'POST',
            headers: { 'Authorization': `Bearer ${token}` }
        });

        if (response.ok) 
            {
            alert("You have been successfully logged out.");
        } 
        else 
            {
            alert("Logout failed on server, but your session will be cleared locally.");
        }
    } catch (error) 
    {
        console.error('Logout error:', error);
        alert("An error occurred during logout. Your session will be cleared locally.");
    } finally {
        localStorage.removeItem('jwtToken');
        window.location.href = '../loginPage/loginPage.html';
    }
}


async function loadMoreStories() 
{
    if (loading || allLoaded) return;

    loading = true;
    document.getElementById('loading').classList.remove('hidden');

    try 
    {
        const stories = await fetchStories(currentPage, pageSize);
        if (stories && stories.length > 0) 
            {
            appendStories(stories);
            currentPage++;
        }
    } catch (error) 
    {
        console.error("Failed to load stories:", error);
    } 
    finally
    {
        loading = false;
        document.getElementById('loading').classList.add('hidden');
    }
}


// --- API and DOM Functions ---

async function fetchStories(page, size) 
{
    const token = localStorage.getItem("jwtToken");

    try {
        const response = await fetch(`http://localhost:8080/api/stories?page=${page}&size=${size}`, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${token}`
            }
        });

        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        const stories = await response.json();
        if (stories.length < size) 
            {
            allLoaded = true;
            document.getElementById('endOfFeed').classList.remove('hidden');
        }
        return stories;

    } 
    catch (error) 
    {
        console.error("Fetch API failed:", error);
        allLoaded = true;
        return null;
    }
}

function appendStories(stories)
 {
        const feed = document.getElementById('storyFeed');
    stories.forEach(story => {
        const div = document.createElement('div');
        div.className = 'story-card';
        
        const title = document.createElement('h2');
        title.textContent = story.title;
        div.appendChild(title);

        const metaInfo = document.createElement('div');
        metaInfo.className = 'meta-info';
        metaInfo.innerHTML = `
            <p><strong>Language:</strong> ${story.language}</p>
            <p><strong>Genre:</strong> ${story.genre}</p>
            <p><strong>Age:</strong> ${story.age}</p>
            <p><strong>Media:</strong> ${story.media}</p>
        `;
        div.appendChild(metaInfo);

        div.onclick = () => {
            window.location.href = `../Story/story.html?title=${encodeURIComponent(story.title)}`;
        };
        feed.appendChild(div);
    });
}


// --- Authentication Helper Functions ---

function getJwtPayload(token) {
    try {
        const base64Url = token.split('.')[1];
        const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
        const jsonPayload = decodeURIComponent(
            atob(base64).split('').map(c => '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2)).join('')
        );
        return JSON.parse(jsonPayload);
    } catch (e) {
        console.error("Invalid token format", e);
        return null;
    }
}

function isTokenExpired(token) 
{
    const payload = getJwtPayload(token);
    if (!payload || !payload.exp) return true;
    return payload.exp < (Date.now() / 1000);
}

