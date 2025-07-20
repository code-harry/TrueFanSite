async function fetchStories() {
            const response = await fetch('http://localhost:8080/api/stories');
            const stories = await response.json();
            const feed = document.getElementById('storyFeed');

            stories.forEach(story => {
                const div = document.createElement('div');
                div.className = 'story-card';
                div.innerHTML = `<h2>${story.title}</h2><p><strong>Language:</strong> ${story.language}</p>`;
                div.onclick = () => {
                    // Pass the story title in URL
                    window.location.href = `../Story/story.html?title=${encodeURIComponent(story.title)}`;
                };
                feed.appendChild(div);
            });
        }

        fetchStories();