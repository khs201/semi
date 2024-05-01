// 좋아요 버튼 
const likeBtn = document.querySelector("#likeBtn");

// 수정 삭제 버튼
const updateBtn = document.querySelector("#updateBtn");
const deleteBtn = document.querySelector("#deleteBtn");

// 목록으로 버튼 (구현 예정@@@@@@@@@@@@)
const goToListBtn = document.querySelector("#goToListBtn");


// 업데이트 버튼 클릭 시
if(updateBtn != null){
updateBtn.addEventListener("click", () => {
    // 현재 : /board/1/2010?cp=1 
    // 목표 : /editBoard/1/2010/update?cp=1  (GET 방식)
    location.href =  location.pathname.replace('board', 'editBoard')
                     + "/update"
                     + location.search;
}) 


}


/* 목록으로 돌아가는 버튼 */
const goToList = document.querySelector("#goToListBtn");

goToListBtn.addEventListener("click",() => {

  // 상세조회 : /board/1/2011?cp=1

  // 목록     : /board/1?cp=1

  let url = location.pathname;
  url = url.substring(0,url.lastIndexOf("/"));

  location.href = url + location.search;
                        // 쿼리스트링

});


// 삭제하기 버튼 클릭 시!!
if(deleteBtn != null){
  deleteBtn.addEventListener("click", () => {

    if( !confirm("삭제 하시겠습니까?") ) {
      alert("취소됨")
      return;
    }

    const url = location.pathname.replace("board","editBoard")  + "/delete"; 

    // form태그 생성
    const form = document.createElement("form");
    form.action = url;
    form.method = "POST";

    // cp값을 저장할 input 생성
    const input = document.createElement("input");
    input.type = "hidden";
    input.name = "cp";

    // 쿼리스트링에서 원하는 파라미터 얻어오기
    const params = new URLSearchParams(location.search)
    const cp = params.get("cp");
    input.value = cp;

    form.append(input);

    // 화면에 form태그를 추가한 후 제출하기
    document.querySelector("body").append(form);
    form.submit();
  });
}


 


  // 1. #boardLike가 클릭 되었을 때
  likeBtn.addEventListener("click", e => {

  // 2. 로그인 상태가 아닌 경우 동작 X
  if(loginMemberNo == null){
    alert("로그인 후 이용해 주세요");
    return;
  }

  // 3. 준비된 3개의 변수를 객체로 저장 (JSON 변환 예정)
  const obj = {
    "memberNo"  : loginMemberNo,
    "boardNo"   : boardNo,
    "likeCheck" : likeCheck
  };

  // 4. 좋아요 INSERT/DELETE 비동기 요청
  fetch("/board/like", {
    method  : "POST",
    headers : {"Content-Type" : "application/json"},
    body    : JSON.stringify(obj)
  })



.then(resp => resp.text()) // 서버 응답을 텍스트로 변환
.then(count => {
    if (count == -1) {
        console.log("좋아요 처리 실패");
        return;
    }

    // likeCheck 값 0 <-> 1 변환
    likeCheck = likeCheck == 0 ? 1 : 0;

    // 하트를 채웠다/비웠다 바꾸기
    e.target.classList.toggle("like");

    // 버튼 다음에 있는 숫자 요소를 찾아서 텍스트 업데이트
    const numElement = e.target.nextElementSibling;
    if (numElement) {
        numElement.innerText = count;
        console.log(count);
    } else {
        console.log("숫자 요소를 찾을 수 없습니다.");
    }
});


});