"use strict"


// signupPage.js

document.addEventListener("DOMContentLoaded", () => {

    const signupForm = document.querySelector("form");
    const homeForm = document.getElementById("homeForm");
    const loginForm = document.getElementById("login");

    // Handle signup form submission
    signupForm.addEventListener("submit", async (e) => {
        e.preventDefault();

        const username = document.getElementById("username").value.trim();
        const password = document.getElementById("password").value.trim();
        const confirmPassword = document.getElementById("confirmPassword").value.trim();

        // Basic client-side validation
        if (password !== confirmPassword) {
            alert("Passwords do not match!");
            return;
        }

        try {
            const response = await fetch("http://localhost:8080/auth/signup", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({ username, password })
            });

            if (response.ok) 
                {
                alert("Signup successful! Redirecting to login...");
                const data = await response.json();
                localStorage.setItem("jwtToken", data.token);
                window.location.href = "../feed/feed.html"; // redirect to feed page after signup
            } 
            else if(response.status === 409)
            {
                alert("Username already exists. Please choose a different one.");
            }
            else 
                {
                const errorData = await response.json();
                alert(`Signup failed: ${errorData.message || "Unknown error"}`);
            }
        } 
        catch (error) 
        {
            console.error("Error during signup:", error);
            alert("An error occurred. Please try again.");
        }
    });

    // Navigation: back to home
    homeForm.addEventListener("submit", (e) => 
        {
        e.preventDefault();
        window.location.href = "../Guest/guest.html";
    });

    // Navigation: go to login
    loginForm.addEventListener("submit", (e) => 
        {
        e.preventDefault();
        window.location.href = "../loginPage/loginPage.html";
    });

});
