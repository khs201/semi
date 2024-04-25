// 빠른 로그인
const quickLoginBtns = document.querySelectorAll(".quick-login");

quickLoginBtns.forEach((item, index) => {
  // item : 현재 반복 시 꺼내 온 객체
  // index : 현재 반복 중인 인덱스

  // 배열에 이벤트 추가 불가
  // quickLoginBtns 요소를 하나씩 꺼내서 이벤트 리스너 추가
  item.addEventListener("click", e => {

    const id = item.innerText; // 버튼에 작성된 아이디 얻어오기

    location.href = "/login/quickLogin?memberId=" + id;
  })
})








