const login = document.querySelector("#login");
const loginLayer = document.getElementById('loginlayer');
const closeLoginLayer = document.getElementById('closelayer');
const signup = document.querySelector(".signup");
const myInfo= document.querySelector(".my-info");
const pageLogo = document.querySelector(".pageLogo");



// 초기에는 로그인 레이어를 숨김
loginLayer.style.visibility = 'hidden';
if(login != null){
  login.addEventListener('click', e => {
    e.preventDefault();
    loginLayer.style.visibility = 'visible'; 
    loginLayer.style.opacity = '1'; // 로그인 레이어를 투명도를 1로 설정하여 표시
  });

  closeLoginLayer.addEventListener('click', e => {
    e.preventDefault();
    loginLayer.style.visibility = 'hidden'; // 로그인 레이어를 숨김
    loginLayer.style.opacity = '0'; // 로그인 레이어를 투명도를 0으로 설정하여 숨김
  });
}
document.getElementById("pageLogo").onclick = function() {
    window.location.href = "/";
};


/* 쿠키에서 key가 일치하는 value 얻어오기 함수 */

// 쿠키는 "K=V; K=V;" 형식

// 배열.map(함수) : 배열의 각 요소를 이용해 함수 수행 후
//                  결과 값으로 새로운 배열을 만들어서 반환
const getCookie = key => {
    const cookies = document.cookie; // "K=V; K=V"
  
    // cookies 문자열을 배열 형태로 변환
    const cookieList = cookies.split("; ")  // ["K=V", "K=V"]
                      .map( el => el.split("=") ); // ["K", "V"]
  
    // 배열 -> 객체로 변환 (그래야 다루기 쉽다)
  
    const obj = {}; // 비어있는 객체 선언
  
    for(let i=0 ; i<cookieList.length ; i++){
      const k = cookieList[i][0]; // key 값
      const v = cookieList[i][1];
      obj[k] = v; // 객체에 추가
    }
  
    // console.log("obj", obj);
  
    return obj[key]; // 매개 변수로 전달 받은 key와 
                    // obj 객체에 저장된 키가 일치하는 요소의 값 반환
    
  }

  const loginId = document.querySelector("#login-form input[name='memberId']");

  if(loginId != null){

    const saveId = getCookie("saveId");

    if(saveId != undefined){
        loginId.value = saveId;

      document.querySelector("input[name='saveId']").checked = true;
    }
  }

  const loginForm = document.querySelector("#login-Form");
  const loginPw = document.querySelector("#logi-Form input[name='memberPw']");

  if(loginForm != null){
    loginForm.addEventListener("submit", e =>{

      if(loginEmail.value.trim().length == 0){
        alert("이메일을 작성해 주세요!");
        e.preventDefault();
        loginEmail.focus();
        return;
      }

      if(loginPw.value.trim().length === 0){
        alert("비밀번호를 작성해 주세요!");
        e.preventDefault();
        loginPw.focus();
        return;
      }
    });
  }



  
   











