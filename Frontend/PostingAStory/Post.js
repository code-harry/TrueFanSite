
   
       let a =  document.getElementById('storyForm');
       a.addEventListener('submit', async function(e) {
            e.preventDefault(); // prevent form from refreshing the page

            const storyName = document.getElementById('storyName').value;
            const storyContent = document.getElementById('storyContent').value;
            const storyMedia = document.getElementById('media').value;
            const storyLanguage = document.getElementById('language').value;
            const storyGenre = document.getElementById('genre').value;
            const storyAge = document.getElementById('age').value;
            // alert("Story Name: " + storyLanguage + "\n" );
            try {
                const response = await fetch('http://localhost:8080/api/stories', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({
                        title: storyName,
                        content: storyContent,
                        media: storyMedia,
                        language: storyLanguage,
                        genre: storyGenre,
                        age: storyAge
                    })
                });
 const text = await response.text(); // get response as plain text

        if (text === "This name cannot be used for a story") 
        {
            alert(text);
        } 
        else if (response.ok) 
            {
                    alert("Story submitted successfully!");
                    // Optional: clear form
                    document.getElementById('storyForm').reset();
                }
                 else 
                {
                    alert("Failed to submit story.");
                }
            } 
            catch (error) 
            {
                console.error("Error submitting story:", error);
                alert("An error occurred.");
            }
        });


