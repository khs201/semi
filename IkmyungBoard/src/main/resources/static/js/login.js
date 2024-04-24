var login = document.querySelector("#login");
var loginLayer = document.getElementById('loginlayer');
var closeLoginLayer = document.getElementById('closelayer');
var signup = document.querySelector(".signup");
var myInfo= document.querySelector(".my-info");
var pageLogo = document.querySelector(".pageLogo");

// 초기에는 로그인 레이어를 숨김
loginLayer.style.visibility = 'hidden';

login.addEventListener('click', function(event) {
    event.preventDefault();
    loginLayer.style.visibility = 'visible'; 
    loginLayer.style.opacity = '1'; // 로그인 레이어를 투명도를 1로 설정하여 표시
});

closeLoginLayer.addEventListener('click', function(event) {
    event.preventDefault();
    loginLayer.style.visibility = 'hidden'; // 로그인 레이어를 숨김
    loginLayer.style.opacity = '0'; // 로그인 레이어를 투명도를 0으로 설정하여 숨김
});

document.getElementById("pageLogo").onclick = function() {
    window.location.href = "/";
};










