// No authentication is needed for this page as it is accessible to all users









document.getElementById("loginForm").addEventListener("submit", async function (event) 
{
    event.preventDefault(); // stop normal form submission

    const username = document.getElementById("username").value.trim();
    const password = document.getElementById("password").value;

    try 
    {
        const response = await fetch("http://localhost:8080/auth/login", 
            {
            method: "POST",
            headers: 
            {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ username, password })
        });

        if (!response.ok) 
            {
            const errorMsg = await response.text();
            alert("Login failed: " + errorMsg);
            return;
        }

        const data = await response.json();
        console.log("JWT token:", data.token);

        // Store token for future API calls
        localStorage.setItem("jwtToken", data.token);

        alert("Login successful!");
        window.location.href = "../Home/home.html"; // redirect after login
    } catch (error) 
    {
        console.error("Error:", error);
        alert("Something went wrong. Please try again.");
    }
});

document.getElementById("Home").addEventListener("click", function () {
    window.location.href = "../Guest/Guest.html"; // adjust your home page path
});



//Authentication logic starts here



// This function contains the logic to decode the JWT token and return the payload
function getJwtPayload(token) 
{
  try 
  {
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

//Authentication logic ends here


document.addEventListener('DOMContentLoaded', function() {
    // Your code goes here.
    // This will run as soon as the DOM is ready.
    const token = localStorage.getItem('jwtToken');
    if(!token) 
    {
    return;
    }
    if (token && isTokenExpired(token)) 
    {
        // alert("Your session has expired. Please log in again.");
        localStorage.removeItem('jwtToken'); // Clear the expired token
        return;
    }
    alert("You are already logged in.");
    window.location.href = '../feed/feed.html'; // redirect to feed if already logged in



});