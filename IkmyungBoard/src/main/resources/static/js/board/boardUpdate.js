/* 선택된 이미지 미리보기 */

const inputImageList = document.getElementsByClassName("inputImage"); // input 태그
const deleteImageList = document.getElementsByClassName("delete-image"); // x버튼




// x버튼이 눌러져 삭제된 이미지의 순서를 저장
// * Set : 중복 저장 X, 순서 유지 X
const deleteOrder = new Set();

// 이미지 선택 이후 취소를 누를 경우를 대비한 백업 이미지
// (백업 원리 -> 복제품으로 기존 요소를 대체함)
const backupImageList = new Array(inputImageList.length);

/* ***** input 태그 값 변경 시(파일 선택 시) 실행할 함수 ***** */
/**
 * @param inputImage : 파일이 선택된 input 태그
 * @param order : 이미지 순서
 */
const changeImageFn = (inputImage, order) => {
    // byte단위로 10MB 지정
    const maxSize = 1024 * 1024 * 10;
  
    // 여러 파일 처리
    Array.from(inputImage.files).forEach(file => {
      // 파일 선택 -> 취소해서 파일이 없는 경우
      if (!file) {
        console.log("파일 선택 취소됨");
        // 백업 및 복원 로직이 필요하면 여기에 추가
        return;
      }
  
      // 선택된 파일의 크기가 최대 크기(maxSize) 초과
      if (file.size > maxSize) {
        alert("10MB 이하의 이미지를 선택해주세요");
  
        // 백업 처리
        if (!backupImageList[order] || backupImageList[order].value === '') {
          inputImage.value = ""; // 잘못 업로드된 파일 값 삭제
          return;
        }
  
        // 백업본 복제 및 복원
        const temp = backupImageList[order].cloneNode(true);
        inputImage.after(temp);
        inputImage.remove();
        inputImage = temp;
        inputImage.addEventListener("change", e => changeImageFn(e.target, order));
        return;
      }
  
      // 파일 리더를 사용한 이미지 미리보기
      const reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onload = e => {
        const url = e.target.result;
        previewList[order].src = url; // 미리보기 업데이트
        backupImageList[order] = inputImage.cloneNode(true); // 백업
        deleteOrder.delete(order); // 삭제된 순서 관리
      };
    });
  };



//   // **** input태그에 이미지가 선택된 경우(값이 변경된 경우) ****
//   inputImageList[0].addEventListener("change", e => {
//     changeImageFn(e.target, i);
//   })
for(let i = 0; i < inputImageList.length; i++){
    inputImageList[i].addEventListener("change", function(e) {
        changeImageFn(e.target, i);
    });
}




for(let i=0 ; i<previewList.length ; i++){


  // **** x 버튼 클릭 시 ****
  deleteImageList[i].addEventListener("click", () => {

    // img, input, backup의 인덱스가 모두 일치한다는 특징을 이용

    // 삭제된 이미지 순서를 deleteOrder에 기록

    // 미리보기 이미지가 있을 때에만
    if (previewList[i].getAttribute("src") != null && previewList[i].getAttribute("src") != "") {

      // 기존에 이미지가 존재하고 있을 때에만
      if (orderList.includes(i)) {

        deleteOrder.add(i);
      }
    }


    
    previewList[i].src       = ""; // 미리보기 이미지 제거
    inputImageList[i].value  = ""; // input에 선택된 파일 제거
    backupImageList[i]       = undefined; // 백업본 제거
    // 요소 선택해서 네모 박스 삭제

  });
}




// -------------------------------------------

// 제출 시 유효성 검사
const boardUpdateFrm = document.querySelector("#boardUpdateFrm");

boardUpdateFrm.addEventListener("submit", e => {

  const boardTitle = document.querySelector("[name='boardTitle']");
  const boardContent = document.querySelector("[name='boardContent']");

  if(boardTitle.value.trim().length == 0){
    alert("제목을 작성해 주세요");
    boardTitle.focus();
    e.preventDefault();
    return;
  }

  if(boardContent.value.trim().length == 0){
    alert("내용을 작성해 주세요");
    boardContent.focus();
    e.preventDefault();
    return;
  }

  // input 태그에 삭제할 이미지 순서(Set)를 배열로 만든 후 대입
  // -> value(문자열) 저장 시 배열은 toString()호출되서 양쪽 []가 사라짐
  document.querySelector("[name='deleteOrder']").value
    = Array.from( deleteOrder );


  document.querySelector("[name='querystring']").value = location.search;
});






/* 테스트 */
// 이미지 업로드 input에 이벤트 리스너 추가
document.getElementById('imageUpload').addEventListener('change', function(e) {
    const files = e.target.files; // 선택된 파일들
    const previewContainer = document.getElementById('previewContainer'); // 미리보기 컨테이너
    previewContainer.innerHTML = ''; // 기존의 미리보기를 초기화

    // 선택된 파일 각각에 대해 미리보기 생성
    Array.from(files).forEach((file, index) => {
        const reader = new FileReader();
        reader.onload = function(event) {
            const img = document.createElement('img');
            img.className = 'preview';
            img.style.width = '100px'; // 미리보기 이미지 크기 설정
            img.src = event.target.result;
            previewContainer.appendChild(img); // 이미지 미리보기를 컨테이너에 추가

            // 삭제 버튼 생성
            const deleteBtn = document.createElement('span');
            deleteBtn.textContent = '×';
            deleteBtn.className = 'delete-image';
            deleteBtn.onclick = function() { // 삭제 버튼 클릭 이벤트
                img.remove(); // 이미지 삭제
                deleteBtn.remove(); // 삭제 버튼 삭제
            };
            previewContainer.appendChild(deleteBtn);
        };
        reader.readAsDataURL(file);
    });
});


document.addEventListener('DOMContentLoaded', function() {
    const deleteButtons = document.querySelectorAll('.delete-image');
    
    deleteButtons.forEach(function(button) {
        button.addEventListener('click', function() {
            const imageId = this.getAttribute('id'); // 삭제 버튼의 id를 기반으로
            const img = document.getElementById('img' + imageId); // 대응되는 이미지 찾기
            if (img) {
                img.remove(); // 이미지 요소 제거
            }
            this.remove(); // 삭제 버튼 요소 제거
        });
    });
});











