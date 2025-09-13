



//Authentication logic starts here

// This function contains the logic to decode the JWT token and return the payload
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




   
       let a =  document.getElementById('storyForm');
       a.addEventListener('submit', async function(e) 
       {
            e.preventDefault(); // prevent form from refreshing the page
            const token = localStorage.getItem("jwtToken"); // retrieve stored JWT

  if (!token) 
    {
    alert("You need to log in first.");
    window.location.href = "../loginPage/loginPage.html"; // redirect to login if no token
    return;
  }
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
                        'Content-Type': 'application/json',
                         "Authorization": `Bearer ${token}`
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


                if (response.status === 401) {
    alert("Session expired. Please log in again.");
    localStorage.removeItem("jwtToken");  // Remove expired token
    window.location.href = "../loginPage/loginPage.html"; // Redirect to login
    return;
}



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


