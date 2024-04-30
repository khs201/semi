/* 글쓰기 버튼 클릭 시 */
const insertBtn = document.querySelector("#writeBtn");

if (insertBtn != null) { // 글쓰기 버튼이 존재할 때 (로그인 상태인 경우) << 아직 안해놓음@@@@
insertBtn.addEventListener("click", () => {

  // * boardCode 얻어오는 방법 : 
  // 1) @PathVariable("boardCode") 얻어와 전역 변수 저장
  // 2) location.pathname.split("/")[2]

  // location.href : get 방식 요청
  location.href = `/editBoard/${boardCode}/write`;

  
});
}


/* 검색 관련된 요소 */

const searchKey = document.querySelector("#searchKey");
const searchQuery = document.querySelector("#searchQuery");

const options = document.querySelectorAll("#searchKey > option");



// 검색창에 이전 검색 기록을 남겨놓기

// 즉시 실행 함수  (() => {})();
// - 함수가 정의 되자마자 바로 실행

// 장점 1. 변수명 중복 해결
// 장점 2. 조금 더 빠름(속도적 우위)


(()=>{

  // 쿼리스트링 값을 key, value 구분해서 저장하는 객체 반환
  
  const params = new URL(location.href).searchParams;

  const key = params.get("key"); // t, c, tc, w 중 하나
  const query = params.get("query"); // 검색어

  if(key != null){ // 검색을 했을 때
      searchQuery.value = query; // 검색어를 화면에 출력

      // option태그를 하나씩 순차 접근해서 value가 key랑 같으면
      // selected 속성 추가
      for(let op of options){
          if(op.value == key){
              op.selected = true;
          }
      }
  }
}
)
();


/* 인기글 버튼 */
const popularBtn = document.querySelector("#popularBtn");

popularBtn.addEventListener("click", () => {

  location.href = `/board/${boardCode}/popular`;

});















/* popularBtn.addEventListener("click", () => {
  
  fetch(`/board/${boardCode}/popular`)
  .then(response => response.json())
  .then(data => {

    // 데이터를 받아온 후 처리
    const boardList = data; // 받아온 데이터에서 게시글 목록 가져오기
    
    // 게시글 목록을 표시할 HTML 요소 선택
    const boardListContainer = document.querySelector("#boardListContainer");
  

    // 받아온 게시글 목록을 순회하면서 HTML 요소를 생성하여 화면에 표시합니다.
    boardList.forEach(board => {
      const boardItem = document.createElement('div');
      boardItem.classList.add('board-item');
      
      // 게시글 정보를 표시하는 HTML 생성
      const boardInfo = `
        <h2>${board.boardTitle}</h2>
        <p>글쓴이: ${board.memberNickname}</p>
        <p>추천 수: ${board.likeCount}</p>
        <p>날짜: ${board.boardWriteDate}</p>
      `;
      
      // HTML을 게시글 아이템에 추가
      boardItem.innerHTML = boardInfo;
      
      // 게시글 아이템을 게시글 목록 컨테이너에 추가
      boardListContainer.appendChild(boardItem);
    });
  })
}); */
