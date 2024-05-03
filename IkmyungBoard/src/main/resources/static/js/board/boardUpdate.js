/* 선택된 이미지 미리보기 */

const inputImageList = document.getElementsByClassName("inputImage"); // input 태그
const deleteImageList = document.getElementsByClassName("delete-image"); // x버튼

let previewContainer = document.getElementById('previewContainer'); // 미리보기 컨테이너

// byte단위로 10MB 지정
const maxSize = 1024 * 1024 * 10;



// x버튼이 눌러져 삭제된 이미지의 PK를 저장
// * Set : 중복 저장 X, 순서 유지 X
const deleteImgNo = new Set();


// 이미지 선택 이후 취소를 누를 경우를 대비한 백업 이미지
// (백업 원리 -> 복제품으로 기존 요소를 대체함)
let backupImageList;



const deletebtnFn = btn => {
  btn.addEventListener("click", (e) => {

    // id가 존재 == PK 값 존재 == 기존 존재하던 이미지
    if(e.target.id != ''){
      deleteImgNo.add(e.target.id);
    }


    const arr = Array.from(document.querySelectorAll(".delete-image"));
    // console.log(arr.indexOf(e.target));
    const targetIndex = arr.indexOf(e.target)
    

    const dataTranster = new DataTransfer();

    const files = imageUpload.files;
    Array.from(files)
      .filter( (file,index) => index != targetIndex)
      .forEach( file => dataTranster.items.add(file) );

    imageUpload.files = dataTranster.files;

    e.target.previousElementSibling.remove();
    e.target.remove();

    // 백업
  backupImageList = previewContainer.cloneNode(true);
  });
}

// x버튼 이벤트 추가
for (let btn of deleteImageList) {
  deletebtnFn(btn);
}



// 이미지 업로드 input에 이벤트 리스너 추가
document.getElementById('imageUpload').addEventListener('change', function (e) {
  const files = e.target.files; // 선택된 파일들

  Array.from(document.querySelectorAll(".delete-image"))
    .filter(btn => btn.id != '')
    .forEach(imgNo => deleteImgNo.add(imgNo.id));

  previewContainer.innerHTML = ''; // 기존의 미리보기를 초기화

  // 선택된 파일 각각에 대해 미리보기 생성
  Array.from(files).forEach((file, index) => {

    if (file.size > maxSize) {
      alert("10MB 이하의 이미지를 선택해주세요");

      const temp = backupImageList.cloneNode(true);
      previewContainer.after(temp);
      previewContainer.remove();
      previewContainer = temp;
      return;
    }

    const reader = new FileReader();

    reader.readAsDataURL(file);

    reader.onload = function (event) {
      const img = document.createElement('img');
      img.className = 'preview';
      img.style.width = '100px'; // 미리보기 이미지 크기 설정
      img.src = event.target.result;
      previewContainer.appendChild(img); // 이미지 미리보기를 컨테이너에 추가

      // 삭제 버튼 생성
      const deleteBtn = document.createElement('span');
      deleteBtn.textContent = '×';
      deleteBtn.className = 'delete-image';
      deletebtnFn(deleteBtn); // x버튼 동작

      previewContainer.appendChild(deleteBtn);
    };
  });





  // 백업
  backupImageList = previewContainer.cloneNode(true);
});






// -------------------------------------------

// 제출 시 유효성 검사
const boardUpdateFrm = document.querySelector("#boardUpdateFrm");

boardUpdateFrm.addEventListener("submit", e => {

  const boardTitle = document.querySelector("[name='boardTitle']");
  const boardContent = document.querySelector("[name='boardContent']");

  if (boardTitle.value.trim().length == 0) {
    alert("제목을 작성해 주세요");
    boardTitle.focus();
    e.preventDefault();
    return;
  }

  if (boardContent.value.trim().length == 0) {
    alert("내용을 작성해 주세요");
    boardContent.focus();
    e.preventDefault();
    return;
  }

  // input 태그에 삭제할 이미지 순서(Set)를 배열로 만든 후 대입
  // -> value(문자열) 저장 시 배열은 toString()호출되서 양쪽 []가 사라짐
  document.querySelector("[name='deleteImgNo']").value
    = Array.from(deleteImgNo);


  document.querySelector("[name='querystring']").value = location.search;
});


/* 숨겨진 이미지 업로드 버튼이랑 연결하는 코드 */
document.getElementById('customButton').addEventListener('click', function() {
  document.getElementById('imageUpload').click();
});



