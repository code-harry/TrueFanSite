let a = document.querySelector("#Login");
let b = document.querySelector("#Sign");
a.addEventListener("click", function () {
  window.location.href = "../loginPage/loginPage.html";
});
b.addEventListener('click', function () 
{
  window.location.href = "../signupPage/signupPage.html";
});
let d = document.querySelector("#Guest");
d.addEventListener('click', function () {
  window.location.href = "../Guest/Guest.html";
});
let c = document.querySelector("div");