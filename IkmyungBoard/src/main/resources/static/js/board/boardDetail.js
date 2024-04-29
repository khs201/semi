// 좋아요 버튼 (구현 예정@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@)
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