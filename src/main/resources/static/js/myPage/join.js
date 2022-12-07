/*
* myPage/joinForm.js
* */

let nickCheck = false;
let nickname = "";

// input에 이름 입력 시 실시간으로 출력
$("input.inputName").keyup(function(){
    $("span#name").html($(this).val());
});


// 유저 구분에 따라 텍스트 출력
$("input#user").prop("checked", true)
$("input.grade").on("change", function(){
    if($(this).val() == 'USER') {
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

    nickCheck = false;
    if(!joinForm.memberNickname.value) {
        joinForm.memberNickname.focus();
        return;
    }
    memberNickname = joinForm.memberNickname.value;
    console.log(memberNickname);

    $.ajax({
        url: "/register/form",
        type : "post",
        data : {memberNickname: memberNickname},
        success : function(data){
            if(data == 0){
                console.log("성공");
                $("#n-message1").css("display", "block");
                $("#n-message2").css("display", "none");
                nickCheck = true;
            }else{
                console.log("씰패");
                $("#n-message1").css("display", "none");
                $("#n-message2").css("display", "block");
                nickCheck = false;
            }
        }

    });
}





// input 값 모두 입력했을 때 회원 가입하기 버튼 활성화
joinForm.memberName.addEventListener("keyup", join)
joinForm.year.addEventListener("change", join)
joinForm.month.addEventListener("change", join)
joinForm.day.addEventListener("change", join)
joinForm.memberPhone.addEventListener("keyup", join)
joinForm.memberLocation.addEventListener("keyup", join)

function join() {
    if(!(joinForm.memberName.value && joinForm.memberPhone.value && joinForm.memberLocation.value && joinForm.year.value && joinForm.month.value && joinForm.day.value)) {
        console.log("true")
            if(!nickCheck){
                 $(".joinBtn").prop("disabled", true);
            }
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



if($("#n-message2").css("display") == "block"){
    $(".joinBtn").prop("disabled", true);
}



$(".joinBtn").on("click", function () {

    // 생년월일
    let year = $("select#year").val();
    let month = $("select#month").val() < 10 ? 0+$("select#month").val() : $("select#month").val();
    let day = $("select#day").val();

    let birth = year+"-"+month+"-"+day;

    $("input#memberBirth").val(birth);
    console.log($("input#memberBirth").val());
    console.log($("input#memberEmail").val());
    console.log($("input#memberOauthId").val());
    console.log(birth);


    joinForm.submit();
})