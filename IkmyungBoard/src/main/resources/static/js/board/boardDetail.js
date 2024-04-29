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



  .then(resp => resp.text()) // 반화 결과 text(글자) 형태로 변환
  .then(count => {
    // count == 첫 번째 then의 파싱되어 반환된 값(-1 또는 게시글 좋아요 수)
    // console.log("result : ", result);

    if(count == -1){
      console.log("좋아요 처리 실패");
      return;
    }


    // 5. likeCheck 값 0 <-> 1 변환
    //  (왜? 클릭 될 때 마다 INSERT/DELETE 동작을 번갈아 가면서 할 수 있음)
    likeCheck = likeCheck == 0 ? 1 : 0;

    // 6. 하트를 채웠다/비웠다 바꾸기
    e.target.classList.toggle("fa-regular");
    e.target.classList.toggle("fa-solid");

    // 7. 게시글 좋아요 수 수정
    // e.target.nextElementSibling.innerText = count;

  });


});