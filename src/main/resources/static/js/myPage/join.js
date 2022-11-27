/*
* myPage/joinForm.js
* */

let nickCheckCount = 0;
let nickname = "";

// input에 이름 입력 시 실시간으로 출력
$("input[name='name']").keyup(function(){
    $("span#name").html($(this).val());
});


// 유저 구분에 따라 텍스트 출력
$("input#user").prop("checked", true)
$("input[name='grade']").on("change", function(){
    if($(this).val() == 'user') {
        $("#farmer-check").hide();
    } else {
        $("#farmer-check").show();
    }
});

// 휴대폰 번호일 때 자동 하이픈(-) 입력 - 미적용
const autoHyphen = (target) => {
    target.value = target.value
        .replace(/[^0-9]/g, '')
        .replace(/^(\d{0,3})(\d{0,4})(\d{0,4})$/g, "$1-$2-$3").replace(/(\-{1,2})$/g, "");
}


function alam() {
    $(".overlay-l").show();
    $(".m-al").show();
    setTimeout(function() { $(".overlay-l").fadeOut();}, 500);
}

/*------------- 닉네임 중복체크 -------------*/
$(".joinBtn").prop("disabled", true);

function joinNickCheck (){ /* 중복 체크 */
    nickCheckCount = 1;
    if(!joinForm.nickname.value) {
        joinForm.nickname.focus();
        return;
    }
    nickname = joinForm.nickname.value;
}

// input 값 모두 입력했을 때 회원 가입하기 버튼 활성화
joinForm.name.addEventListener("keyup", join)
joinForm.year.addEventListener("change", join)
joinForm.month.addEventListener("change", join)
joinForm.day.addEventListener("change", join)
joinForm.phone.addEventListener("keyup", join)
joinForm.address.addEventListener("keyup", join)

function join() {
    if(!(joinForm.name.value && joinForm.phone.value && joinForm.address.value && joinForm.year.value && joinForm.month.value && joinForm.day.value)) {
        console.log("true")
        $(".joinBtn").prop("disabled", true);
    } else {
        console.log("false")
        $(".joinBtn").prop("disabled", false);
    }

    // if(!joinForm.year.value) {
    //     joinForm.year.focus();
    //     $(".joinBtn").prop("disabled", true);
    //     return;
    // }
    // if(!joinForm.year.value) {
    //     joinForm.year.focus();
    //     $(".joinBtn").prop("disabled", true);
    //     return;
    // }
    // if(!joinForm.month.value) {
    //     joinForm.month.focus();
    //     $(".joinBtn").prop("disabled", true);
    //     return;
    // }
    // if(!joinForm.day.value) {
    //     joinForm.day.focus();
    //     $(".joinBtn").prop("disabled", true);
    //     return;
    // }
    // if(!joinForm.phone.value) {
    //     joinForm.phone.focus();
    //     $(".joinBtn").prop("disabled", true);
    //     return;
    // }
    // if(!joinForm.address.value) {
    //     joinForm.address.focus();
    //     $(".joinBtn").prop("disabled", true);
    //     return;
    // }

}