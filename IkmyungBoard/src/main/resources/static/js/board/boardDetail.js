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

