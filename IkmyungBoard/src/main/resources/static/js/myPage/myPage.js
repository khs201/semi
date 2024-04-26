const changePw = document.querySelector("#changePw");

if(changePw != null){

    changePw.addEventListener("submit", e => {

        const newPw = document.querySelector("#newPw");
        const newPwConfirm = document.querySelector("#newPwConfirm");

        if( newPw.value != newPwConfirm.value){
            alert("새 비밀번호가 일치하지 않습니다");
            e.preventDefault();
            return;
        }
    })
}