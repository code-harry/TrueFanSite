let a = document.querySelector("#homeForm");

let b = document.querySelector("#login");

a.addEventListener("submit", function (e) 
{
    e.preventDefault(); // prevent form from refreshing the page
    window.location.href = "../landingpage/landingpage.html";
});


b.addEventListener("submit", function (e) 
{
    e.preventDefault(); // prevent form from refreshing the page
    window.location.href = "../loginPage/loginPage.html";
})