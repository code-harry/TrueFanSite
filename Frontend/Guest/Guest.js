let a = document.querySelector("#Post");
a.addEventListener("click",function()
{
    window.location.href = "../PostingAStory/post.html";
});
let b = document.querySelector("#Read");
b.addEventListener("click",function()
{
    window.location.href = "../Feed/feed.html";
});

let c = document.querySelector("#search");
c.addEventListener("click",function()
{
    window.location.href = "../Search/search.html";
});