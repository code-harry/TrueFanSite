let currentPage = 0;
const pageSize = 10;
let loading = false;
let allLoaded = false;

async function fetchStories(page, size) {
  loading = true;
  const response = await fetch(`http://localhost:8080/api/stories?page=${page}&size=${size}`);
  const stories = await response.json();
  loading = false;

  if (stories.length < size) {
    allLoaded = true; // No more stories to load
  }

  return stories;
}

function appendStories(stories) {
  const feed = document.getElementById('storyFeed');

  stories.forEach(story => {
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
fetchStories(currentPage, pageSize).then(stories => {
  appendStories(stories);
});

// Listen for scroll event
window.addEventListener('scroll', () => {
  if (allLoaded || loading) return;

  const scrollPosition = window.innerHeight + window.scrollY;
  const nearBottom = document.body.offsetHeight - 100; // 100px from bottom

  if (scrollPosition >= nearBottom) {
    currentPage++;
    fetchStories(currentPage, pageSize).then(stories => {
      appendStories(stories);
    });
  }
});
