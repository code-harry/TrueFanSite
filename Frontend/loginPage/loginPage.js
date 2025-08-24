document.getElementById("loginForm").addEventListener("submit", async function (event) 
{
    event.preventDefault(); // stop normal form submission

    const username = document.getElementById("username").value.trim();
    const password = document.getElementById("password").value;

    try {
        const response = await fetch("http://localhost:8080/auth/login", 
            {
            method: "POST",
            headers: {
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
        window.location.href = "../feed/feed.html"; // redirect after login
    } catch (error) {
        console.error("Error:", error);
        alert("Something went wrong. Please try again.");
    }
});

document.getElementById("Home").addEventListener("click", function () {
    window.location.href = "../Guest/Guest.html"; // adjust your home page path
});
