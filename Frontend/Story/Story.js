async function loadStory() {
    const params = new URLSearchParams(window.location.search);
    const title = params.get("title");
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