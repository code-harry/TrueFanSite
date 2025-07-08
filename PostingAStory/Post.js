
   
       let a =  document.getElementById('storyForm');
       a.addEventListener('submit', async function(e) {
            e.preventDefault(); // prevent form from refreshing the page

            const storyName = document.getElementById('storyName').value;
            const storyContent = document.getElementById('storyContent').value;

            try {
                const response = await fetch('http://localhost:8080/api/stories', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({
                        title: storyName,
                        content: storyContent
                    })
                });

                if (response.ok) {
                    alert("Story submitted successfully!");
                    // Optional: clear form
                    document.getElementById('storyForm').reset();
                } else {
                    alert("Failed to submit story.");
                }
            } catch (error) {
                console.error("Error submitting story:", error);
                alert("An error occurred.");
            }
        });


