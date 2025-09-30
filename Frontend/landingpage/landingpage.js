"use strict"

//Old code without JQUERY
// let a = document.querySelector("#Login");

let a = $("#Login")[0];

//Old code without JQUERY
// let b = document.querySelector("#Sign");

let b = $("#Sign")[0];


a.addEventListener("click", function () {
  window.location.href = "../loginPage/loginPage.html";
});
b.addEventListener('click', function () 
{
  window.location.href = "../signupPage/signupPage.html";
});

//Old code without JQuery
// let d = document.querySelector("#Guest");


//New code with JQuery
let d = $("#Guest")[0];


d.addEventListener('click', function () {
  window.location.href = "../Guest/Guest.html";
});
let c = document.querySelector("div");