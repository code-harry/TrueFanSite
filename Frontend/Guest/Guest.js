"use strict"



// The below code would have been uncommented if the authentication had to be here for this page

// The authentication logic is handled here

// // This function contains the logic to decode the JWT token and return the payload
// function getJwtPayload(token) 
// {
//   try {
//     const base64Url = token.split('.')[1];
//     const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
//     const jsonPayload = decodeURIComponent(
//       atob(base64).split('').map(c =>
//         '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2)
//       ).join('')
//     );
//     return JSON.parse(jsonPayload);
//   } 
//   catch (e) 
//   {
//     console.error("Invalid token format", e);
//     return null;
//   }
// }

// // Function to check if a token is expired
// function isTokenExpired(token) 
// {
//   // Getting the payload- which is the second part of the JWT token
//   const payload = getJwtPayload(token);

//   // If the payload is null or doesn't have an exp field, consider the token expired
//   if (!payload || !payload.exp)
//     {
//       return true;
//     }  

//     // If the payload is less than the curent time, the token is expired otherwise the token is valid
//   return payload.exp < (Date.now() / 1000);

// }

// // The first code that gets executed when the page is loaded
// window.addEventListener('load', () => 
//   {
//     // getting the jwt token from local storage and storing it in the variable called token
//   const token = localStorage.getItem('jwtToken');

//   // If the token is not present, alert the user and redirect to login page
//   if(!token) 
//     {
//     alert("You need to log in first.");
//     window.location.href = '../loginPage/loginPage.html'; // redirect to login if no token
//     return;
//   }

//   // If the token is present, check if it is expired
//   if (token && isTokenExpired(token)) 
//     {
//     localStorage.removeItem('jwtToken');
//     alert("Session expired. Please log in again.");
//     // Redirect to login page if token is expired
//     window.location.href = '../loginPage/loginPage.html';
//   }
// });




// The actual logic of the page

//Old code without JQuery
// let a = document.querySelector("#Post");
// a.addEventListener("click",function()
// {
//     window.location.href = "../PostingAStory/post.html";
// });


//New code with JQuery
$("#Post").on("click",function()
{
    window.location.href = "../PostingAStory/post.html";
});



//Old code without JQuery
// let b = document.querySelector("#Read");
// b.addEventListener("click",function()
// {
//     window.location.href = "../Feed/feed.html";
// });

//New code with JQuery
$("#Read").on("click",function()
{
    window.location.href = "../Feed/feed.html";
});




//Old code without JQuery
// let c = document.querySelector("#search");
// c.addEventListener("click",function()
// {
//     window.location.href = "../Search/search.html";
// });


//New code with JQuery
$("#search").on("click",function()
{
    window.location.href = "../Search/search.html";
});



//Old code without JQuery
// let d = document.querySelector("#Login");
// d.addEventListener("click",function()
// {
//     // localStorage.removeItem("jwtToken");
//     window.location.href = "../loginPage/loginPage.html";
// });


//New code with JQuery
$("#Login").on("click",function()
{
    window.location.href = "../loginPage/loginPage.html";
});


//Old code without JQuery
// let e = document.querySelector("#Signup");
// e.addEventListener("click",function()
// {
//     window.location.href = "../signupPage/signupPage.html";
// });

//New code with JQuery
$("#Signup").on("click",function()
{
    window.location.href = "../signupPage/signupPage.html";
});