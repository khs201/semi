/* REST(REpresentational State Transfer) API 

- 자원(데이터, 파일) 을 이름(주소)으로
 구분(representational) 하여
 자원의 상태(State)를 주고 받는 것(Trnasfer)

 -> 자원의 이름(주소)를 명시하고
    HTTP Method(GET, POST, PUT, DELETE)를 이용해
    지정된 자원에 대한 CRUD 진행

    자원의 이름(주소)는 하나만 지정 (ex. /comment)

    삽입 == POST (Create)
    조회 == GET (Read)
    수정 == PUT (Update)
    삭제 == DELETE (Delete)
  
*/

/* ***** 댓글 등록(ajax) ***** */

const addContent = document.querySelector("#addComment"); // button
const commentContent = document.querySelector("#commentContent"); // textarea

// 댓글 등록 버튼 클릭 시
addContent.addEventListener("click", e => {

  // 로그인이 되어있지 않은 경우
  if (loginMemberNo == null) {
    alert("로그인 후 이용해 주세요");
    return; // early return;
  }

  // 댓글 내용이 작성되지 않은 경우
  if (commentContent.value.trim().length == 0) {
    alert("내용 작성 후 등록 버튼을 클릭해 주세요");
    commentContent.focus();
    return;
  }


  // ajax를 이용해 댓글 등록 요청
  const data = {
    "commentContent": commentContent.value,
    "boardNo": boardNo,
    "memberNo": loginMemberNo  // 또는 Session 회원 번호 이용도 가능
  };

  fetch("/comment", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data) // data 객체를 JSON 문자열로 변환
  })

    .then(response => response.text())
    .then(result => {

      if (result > 0) {
        alert("댓글이 등록 되었습니다");
        commentContent.value = ""; // 작성한 댓글 내용 지우기
        selectCommentList(); // 댓글 목록을 다시 조회해서 화면에 출력

      } else {
        alert("댓글 등록 실패");
      }

    })
    .catch(err => console.log(err));


})

