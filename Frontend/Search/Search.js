// document.getElementById('searchForm').addEventListener('submit', async (e) => {
    
//     // Prevent the form from refreshing the page
//     e.preventDefault();
//     // Get the values from the form inputs
//     const title = document.getElementById('storyName').value;
//     const language = document.getElementById('language').value;
//     const genre = document.getElementById('genre').value;
//     const media = document.getElementById('media').value;
//     const age = document.getElementById('age').value;
//     const query = new URLSearchParams();
//     if (!title && !language && !genre && !media && !age)
//         {
//             alert("Please enter at least one search criteria.");
//             return;
//         }
    
    
//     // Check if each field is not empty before appending to the query
//         if (title)
//         {
//             // If title is provided, append it to the query
//             query.append("title", title);
//         } 
//     if (language)
//         {
//             // If language is provided, append it to the query
//             query.append("language", language);
//         } 
//     if (genre)
//         {
//             // If genre is provided, append it to the query
//             query.append("genre", genre);
//         } 


//     if( media)
//         {
//     // If media is provided, append it to the query 
// query.append("media", media);
//         } 
//     if (age)
//         {
//     // If age is provided, append it to the query
// query.append("age", age);
//         } 



//     const res = await fetch(`http://localhost:8080/api/search?${query}`);
//     alert(res.json);
//     const stories = await res.json();
//         if (stories.length === 0) 
//             {
//             alert("No stories found matching your criteria.");
//             return;
//         }
//         alert(`${stories.length} stories found matching your criteria.`);
//     const results = document.getElementById('results');
//     results.innerHTML = "";
//     stories.forEach(story => {
//       const div = document.createElement("div");
//       div.innerHTML = `<h3>${story.title}</h3><p>${story.content}</p><hr>`;
//       results.appendChild(div);
//     });
//   });




document.getElementById('searchForm').addEventListener('submit', async (e) => {
    // Prevent the form from refreshing the page
    e.preventDefault();

    // Get the values from the form inputs
    const title = document.getElementById('storyName').value;
    const language = document.getElementById('language').value;
    const genre = document.getElementById('genre').value;
    const media = document.getElementById('media').value;
    const age = document.getElementById('age').value;

    const query = new URLSearchParams();

    if (!title && !language && !genre && !media && !age) {
        alert("Please enter at least one search criteria.");
        return;
    }

    // Check if each field is not empty before appending to the query
    if (title) {
        query.append("title", title);
    }
    if (language) {
        query.append("language", language);
    }
    if (genre) {
        query.append("genre", genre);
    }
    if (media) {
        query.append("media", media);
    }
    if (age) {
        query.append("age", age);
    }

    const res = await fetch(`http://localhost:8080/api/search?${query}`);

    // --- IMPORTANT: Handle API response properly, just like in loadStory ---
    if (!res.ok) { // Check for non-2xx responses
        if (res.status === 400) {
            alert("Invalid search request. Please check your criteria.");
        } else if (res.status === 404) { // Or if your search API returns 404 for no results
            alert("No stories found matching your criteria.");
        } else {
            alert(`An error occurred during search: ${res.status} ${res.statusText}`);
        }
        // You might also want to clear previous results or display the error in the 'results' div
        document.getElementById('results').innerHTML = `<p style="color: red;">Error during search: ${res.status} ${res.statusText}</p>`;
        return;
    }
    // --- END IMPORTANT ---


    const stories = await res.json();

    if (stories.length === 0) {
        alert("No stories found matching your criteria.");
        document.getElementById('results').innerHTML = "<p>No stories found matching your criteria.</p>"; // Display on page
        return;
    }

    alert(`${stories.length} stories found matching your criteria.`); // Keep alert for now

    const results = document.getElementById('results');
    results.innerHTML = ""; // Clear previous search results

    stories.forEach(story => {
        const div = document.createElement("div");
        div.className = 'story-card'; // Add a class for styling, consistent with your feed
        div.innerHTML = `<h3>${story.title}</h3>
                         <p>${story.content}</p>
                         <p><strong>Language:</strong> ${story.language}</p>
                         <p><strong>Genre:</strong> ${story.genre}</p>
                         <p><strong>Media:</strong> ${story.media}</p>
                         <p><strong>Age:</strong> ${story.age}</p>`; // Include other details if desired

        // Make the div clickable, linking to the story.html page
        div.onclick = () => {
            // Ensure the path to story.html is correct relative to the current page
            // Assuming your current search page is also in a directory like 'Search/' or 'index.html'
            // and story.html is in 'Story/'. Adjust '../Story/story.html' if needed.
            window.location.href = `../Story/story.html?title=${encodeURIComponent(story.title)}`;
        };

        results.appendChild(div);
    });
});