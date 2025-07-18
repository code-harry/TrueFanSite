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
    if(title===null && language===null && genre===null && media===null && age===null)
        {
            alert("Please enter at least one search criteria.");
            return;
        }
    
    
    // Check if each field is not empty before appending to the query
        if (title)
        {
            // If title is provided, append it to the query
            query.append("title", title);
        } 
    if (language)
        {
            // If language is provided, append it to the query
            query.append("language", language);
        } 
    if (genre)
        {
            // If genre is provided, append it to the query
            query.append("genre", genre);
        } 


    if( media)
        {
    // If media is provided, append it to the query 
query.append("media", media);
        } 
    if (age)
        {
    // If age is provided, append it to the query
query.append("age", age);
        } 



    const res = await fetch(`http://localhost:8080/api/search?${query}`);
    alert(res.json);
    const stories = await res.json();
        if (stories.length === 0) 
            {
            alert("No stories found matching your criteria.");
            return;
        }

    const results = document.getElementById('results');
    results.innerHTML = "";
    stories.forEach(story => {
      const div = document.createElement("div");
      div.innerHTML = `<h3>${story.title}</h3><p>${story.content}</p><hr>`;
      results.appendChild(div);
    });
  });