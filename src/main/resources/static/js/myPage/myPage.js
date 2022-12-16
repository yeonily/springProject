/*
*   html/myPage/setting.html
* */

// 헤더 검색창 없애기
$(".search_form").css("visibility", "hidden");

// 더보기 메뉴 슬라이드
$("div.more-btn").on("click", function(){
    if($('.slide-menu').css("height") == "0px"){
        $('.slide-menu').css("height", "88px");
        $('.more-btn>svg').css("transform", "rotate(0)");
    } else {
        $('.slide-menu').css("height", "0px");
        $('.more-btn>svg').css("transform", "rotate(180deg)");
    }
})

// setting.html - 닉네임 중복확인 버튼 클릭 후 저장 버튼 활성화
$(".nick-save").attr("disabled", true);

$("input[name='nickname']").on("focus",function(){
    $(".nick-save").attr("disabled", true);
});


// setting.html - 휴대폰 번호일 때 자동 하이픈(-) 입력 - 미적용
const autoHyphen = (target) => {
    target.value = target.value
        .replace(/[^0-9]/g, '')
        .replace(/^(\d{0,3})(\d{0,4})(\d{0,4})$/g, "$1-$2-$3").replace(/(\-{1,2})$/g, "");
}

// setting.html - 멘토 등록할 때 입력 폼
function mentorFormShow(){
    $("#mentor-none").hide();
    $(".newMentorForm").show();
}

$("#mentorCancel").on("click", function(){
    $("#mentor-none").show();
    $(".newMentorForm").hide();
});

// quit.html - 탈퇴 모달창 이벤트
$("button.quit").on("click", function(){
    $("#modal").show();
});
$("div.modal-close").on("click", function(){
    $("#modal").hide();
    $(".quit-reason").hide();
    $("input[name='reason']").prop("checked", false);
    $("button.quitBtn").attr("disabled", true);
    $("input.q-r-input").val("");
});


// quit.html - 탈퇴 이유 클릭시 이벤트
$("button.quitBtn").attr("disabled", true);

$("input[name='reason']").on("change", function (){
    $("button.quitBtn").attr("disabled", true);
    if($(this).val() == "reason-etc"){
        $(".quit-reason").show();
        $("input.q-r-input").keyup(function (){
            if($(this).val()=="") {
                $("button.quitBtn").attr("disabled", true);
            } else {
                $("button.quitBtn").attr("disabled", false);
            }
        });
    } else {
        $(".quit-reason").hide();
        $("button.quitBtn").attr("disabled", false);
    }
});


// 애니메이션 모달 알람
function alam() {
    $(".overlay-l").show();
    $(".m-al").show();
    setTimeout(function() { $(".overlay-l").fadeOut();}, 500);
}

// mentoring.html - 한 줄 소개 보기
$("div.contents").on("click", "button.infoBtn", function(){
    let $info = $(this).parents('div.m-l').next();
    let $moreSVG = $(this).children('svg');

    if($info.css("height") == '0px'){
        $(".myInfo").css('height', '0px');
        $(".myInfo").css('padding', '0');
        $('.infoBtn>svg').css("transform", "rotate(180deg)");

        $info.css('height', 'auto');
        $info.css('padding', '7px 10px');
        $moreSVG.css("transform", "rotate(0deg)");
    } else {
        $info.css('height', '0px');
        $info.css('padding', '0');
        $moreSVG.css("transform", "rotate(180deg)");
    }
})

/*------------- 닉네임 수정 -------------*/

function nickCheck (){ /* 중복 체크 */

    memberNickname = nickForm.memberNickname.value;
    console.log(memberNickname);

    $.ajax({
        url: "/mypage/setting",
        type : "post",
        data : {memberNickname: memberNickname},
        success : function(data){
            if(data == 0){
                console.log("성공");
                $("#n-message1").css("display", "block");
                $("#n-message2").css("display", "none");
                $("button.nick-save").attr("disabled", false);
            }else{
                console.log("씰패");
                $("#n-message1").css("display", "none");
                $("#n-message2").css("display", "block");
                $("button.nick-save").attr("disabled", true);
            }
        }

    });


}

// let memberId = [[${member.memberId}]];
function nickSave() { /* 닉네임 저장 */
    nickForm.submit();
}
$("button.nick-save").on("click", nickSave);

/*------------- 개인 정보 수정 -------------*/
function myInfoSave() {
    if(!myInfoForm.memberPhone.value || myInfoForm.memberPhone.value.length != 13) {
        myInfoForm.memberPhone.focus();
        return;
    }
    if(!myInfoForm.memberLocation.value) {
        myInfoForm.memberLocation.focus();
        return;
    }

    myInfoForm.submit();
}

$("button#infoBtn").on("click", myInfoSave);


/*------------- new 멘토 정보 입력 -------------*/
function newMentorSave (){ /* 중복 체크 */
    if(!newMentorForm.mentorCrop.value) {
        newMentorForm.mentorCrop.focus();
        return;
    }
    if(newMentorForm.mentorYear.value == "0") {
        newMentorForm.mentorYear.focus();
        return;
    }

    newMentorForm.submit();

}

$("button#mentorBtn").on("click", newMentorSave);


/*------------- 멘토 정보 년도 수정 -------------*/
$(document).ready(function(){
    let yearsVal = $('select.selectedyear').attr('data-status');
    if(yearsVal =="1~3년차"){
        $('select.selectedyear option[value="1~3년차"]').attr("selected", true);
    }else if(yearsVal =="3~5년차"){
        $('select.selectedyear option[value="3~5년차"]').attr("selected", true);
    }else if(yearsVal =="5~7년차"){
        $('select.selectedyear option[value="5~7년차"]').attr("selected", true);
    }else if(yearsVal =="7~10년차"){
        $('select.selectedyear option[value="7~10년차"]').attr("selected", true);
    }else if(yearsVal =="10년차 이상"){
        $('select.selectedyear option[value="10년차 이상"]').attr("selected", true);
    }


})
/*------------- 멘토 정보 수정 -------------*/
function mentorSave (){ /* 중복 체크 */
    if(!mentorForm.mentorCrop.value) {
        mentorForm.mentorCrop.focus();
        return;
    }
   /* if(mentorForm.mentorYear.value == "0") {
        mentorForm.mentorYear.focus();
        return;
    }*/

    mentorForm.submit();
}

$("button#mentorUpdateBtn").on("click", mentorSave);

/*------------- 회원 탈퇴 -------------*/
function quitBtn (){ /* 중복 체크 */

    quitForm.submit();

}
