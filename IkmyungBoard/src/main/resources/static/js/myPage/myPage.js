const changePw = document.querySelector("#changePw");
const deleteImage = document.querySelector("#deleteImage");
const profileImg = document.querySelector("#profileImg");

if(changePw != null){

    changePw.addEventListener("submit", e => {

        const currentPw = document.querySelector("#currentPw");
        const newPw = document.querySelector("#newPw");
        const newPwConfirm = document.querySelector("#newPwConfirm");

        let str;

        if( currentPw.value.trim().length == 0 )  str = "현재 비밀번호를 입력해 주세요";
        else if( newPw.value.trim().length == 0 ) str = "새 비밀번호를 입력 해주세요";
        else if( newPwConfirm.value.trim().length == 0 ) str = "새 비밀번호 확인을 입력 해주세요";


        if(str != undefined){ // str에 값이 대입됨 == if 중 하나가 실행됨
          alert(str);
          e.preventDefault();
          return;
        }

        // 새 비밀번호 정규식
        const regExp = /^(?=.*[a-zA-Z0-9!@,#,-,_])^[a-zA-Z0-9!@,#,-,_]{6,20}$/;

        if( !regExp.test(newPw.value)){
          alert("새 비밀번호가 유효하지 않습니다");
          e.preventDefault();
          return;
        }

        // 새 비밀번호 == 새 비밀번호 확인
        if( newPw.value != newPwConfirm.value){
            alert("새 비밀번호가 일치하지 않습니다");
            e.preventDefault();
            return;
        }
    })
}


// 탈퇴하기 유효성 검사

const secession = document.querySelector("#secession");

if(secession != null){

  secession.addEventListener("submit", e => {

    const memberPw = document.querySelector("#memberPw");
    const agree = document.querySelector("#agree");

    // - 비밀번호 입력 되었는지 확인
    if(memberPw.value.trim().length == 0){
      alert("비밀번호를 입력해 주세요");
      e.preventDefault();
      return;
    }

    // - 약관 동의 체크 확인
    
    // checkbox 또는 radio  checked 속성
    // - checked  ->  체크시 true, 미체크시 false 반환
    // - checked = true -> 체크하기
    // - checked = false -> 체크 해제하기

    // if( agree.checked == false ){
    if( !agree.checked ){ // 체크 안됐을 때
      alert("약관에 동의해주세요");
      e.preventDefault();
      return;
    }

    // - 정말 탈퇴? 물어보기
    if( !confirm("정말 탈퇴 하시겠습니까?") ){ // 취소 선택 시
      alert("취소 되었습니다");
      e.preventDefault();
      return;
    }

  })
}





// x 버튼 클릭 시 이미지 지우기
deleteImage.addEventListener("click", () =>{

    profileImg.src = "/images/user.png"

    imageInput.value = '';

    imageInput = undefined;

    statusChech = 0;
});


// -------------------------------------------------------------------

/* 프로필 이미지 추가/변경/삭제 */

// 프로필 이미지 페이지 form 태그
const profile = document.querySelector("#profile");

// 프로필 이미지가 새로 업로드 되거나 삭제 되었음을 기록하는
// 상태 변수

// -1 : 초기상태(변화 없음)
//  0 : 프로필 이미지 삭제
//  1 : 새 이미지 선택
let statusCheckCheck = -1;


// input type="file" 태그의 값이 변경 되었을 때
// 변경된 상태를 백업해서 저장할 변수
// -> 파일이 선택/취소된 input을 복제해서 저장

// 요소. cloneNode(true|false) : 요소 복제(ture 작성 시 하위 요소도 복제)
let backupInput;


if(profile != null){

// img 태그 (프로필 이미지가 보여지는 요소)
const profileImg = document.querySelector("#profileImg");

// input type="file" 태그 (실제 업로드할 프로필 이미지를 선택하는 요소)
let imageInput = document.querySelector("#imageInput");

// x버튼 (프로필 이미지를 제거하고 기본 이미지로 변경하는 요소)
const deleteImage = document.querySelector("#deleteImage");




/* input type="file"의 값이 변했을때 동작할 함수(이벤트 핸들러) */
const changeImageFn = e => {

  // 업로드 가능한 파일 최대 크기 지정하여 필터링
  const maxSize = 1024 * 1024 * 5;   

  console.log("e.tartget", e.target); // input
  console.log("e.tartget.value", e.target.value); // 변경된 값(파일명)

  // 선택된 파일에 대한 정보가 담긴 벼올을 반환
  // -> 왜 배열?? multiple 옵션에 대한 대비
  console.log("e.tartget.files", e.target.files);

  
  // 업로드된 파일이 1개 있으면 files[0]에 저장됨
    
  // 업로드된 파일이 없으면 files[0] == undefined
  console.log("e.tartget.files[0]", e.target.files[0]);

  const file = e.target.files[0];

  // ----------- 업로드된 파일이 없다면(취소한 경우) -------------
  if(file == undefined){
    console.log("파일 선택 후 취소됨");

    // 파일 선택 후 취소 -> value == ''
    // -> 선택한 파일 없음으로 기록됨
    // -> backupInput으로 교체 시켜서 
    //    이전 이미지가 남아 있는 것 처럼 보이게 함

    // 백업의 백업본
    const temp = backupInput.cloneNode(true);

    // input 요소 다음에 백업 요소 추가
    imageInput.after(backupInput);

    // 화면에 존재하는 기존 input 제거
    imageInput.remove();

    // imageInput 변수에 백업을 대입해서 대신하도록 함
    imageInput = backupInput;

    // 화면에 추가된 백업본에는 
    // 이벤트 리스너가 존재하지 않기 떄문에 추가
    imageInput.addEventListener("change", changeImageFn);
    
    // 한번 화면에 추가된 요소(backupInput)는 재사용 불가능
    // backupInputdml 백업본이 temp를 backupInput 으로 변경
    backupInput = temp;

    return;
  }


  // ---------- 선택된 파일이 최대 크기를 초과한 경우 -------------
  if(file.size > maxSize){
    alert("5MB 이하의 이미지 파일을 선택해 주세요.");

    // 선택한 이미지가 없는데 5MB 초과하는 이미지를 선택한 경우
    if(statusCheck == -1){
      imageInput.value = '';
    } else { // 기존 선택한 이미지가 있는데
             // 다음 선택한 이미지가 최대 크기를 초과한 경우

         // 백업의 백업본
    const temp = backupInput.cloneNode(true);

    // input 요소 다음에 백업 요소 추가
    imageInput.after(backupInput);

    // 화면에 존재하는 기존 input 제거
    imageInput.remove();

    // imageInput 변수에 백업을 대입해서 대신하도록 함
    imageInput = backupInput;

    // 화면에 추가된 백업본에는 
    // 이벤트 리스너가 존재하지 않기 떄문에 추가
    imageInput.addEventListener("change", changeImageFn);
    
    // 한번 화면에 추가된 요소(backupInput)는 재사용 불가능
    // backupInputdml 백업본이 temp를 backupInput 으로 변경
    backupInput = temp;

    
    }

    return;
  }


  // ---------- 선택된 이미지 미리보기 -----------

  // JS에서 파일을 읽을 때 사용하는 객체
  // - 파일을 읽고 클라이언트 컴퓨터에 저장할 수 있음
  const reader = new FileReader();

  // 선택한 파일(file) 을 읽어와
  // BASE64 인코딩 형태로 읽어와 result 변수에 저장
  reader.readAsDataURL(file); // -> 읽어오기 이벤트(load)

  // 읽어오기 끝났을 때
  reader.addEventListener("load", e => {

    // e.target == reader

    // 읽어온 이미지 파일이 BASE64 형태로 반환됨
    const url = e.target.result; // reader.result

    // 프로필 이미지(img)에 src속성으로 url값 세팅
    profileImg.setAttribute("src", url);

    // 새 이미지 선택 상태를 기록
    statusCheck = 1;

    // 파일이 선택된 input을 복제해서 백업
    backupInput = imageInput.cloneNode(true);

  });

}

// change 이벤트 : 새로운 값이 기존 값과 다를 경우 발생
imageInput.addEventListener("change", changeImageFn);


// ---------- x버튼 클릭 시 기본 이미지로 변경 -------------
deleteImage.addEventListener("click", () => {
 
  // 프로필 이미지(img)를 기본 이미지로 변경
  profileImg.src = "/images/user.png";

  // input에 저장된 값(value)를 ''(빈칸)으로 변경
  // -> input에 저장된 파일 정보가 모두 사라짐 == 데이터 삭제
  imageInput.value = '';

  backupInput = undefined; //백업본도 삭제

  // 삭제 상태임을 기록
  statusCheck = 0;
 });

 // ------------ #profile (form) 제출 시 -----------------
profile.addEventListener("submit", e => {
    
  let flag = true;
  
  
  // 기존 프로필 이미지가 없다가 새 이미지가 선택된 경우
  if(loginMemberProfileImg == null && statusCheck == 1) flag = false;
  
  // 기존 프로필 이미지가 있다가 삭제한 경우
  if(loginMemberProfileImg != null && statusCheck == 0) flag = false;
    
  // 기존 프로필 이미지가 있다가 새 이미지가 선택된 경우
  if(loginMemberProfileImg != null && statusCheck == 1) flag = false;
  
  
  if(flag){ // flag 값이 true인 경우
    e.preventDefault();
    alert("이미지 변경 후 클릭하세요")
   }
  });
}