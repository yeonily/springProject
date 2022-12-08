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
$("button.infoBtn").on("click", function(){
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
    if(!nickForm.nickname.value) {
        nickForm.nickname.focus();
        return;
    }

    $("button.nick-save").attr("disabled", false);
}

function nickSave() { /* 저장 */
    nickForm.submit();
}


/*------------- 개인 정보 수정 -------------*/
function myInfoSave() {
    if(!myInfoForm.phone.value || myInfoForm.phone.value.length != 13) {
        myInfoForm.phone.focus();
        return;
    }
    if(!myInfoForm.address.value) {
        myInfoForm.address.focus();
        return;
    }

    myInfoForm.submit();
}


/*------------- new 멘토 정보 입력 -------------*/
function newMentorSave (){ /* 중복 체크 */
    if(!newMentorForm.newMainCrops.value) {
        newMentorForm.newMainCrops.focus();
        return;
    }
    if(newMentorForm.newYears.value == "0") {
        newMentorForm.newYears.focus();
        return;
    }

    newMentorForm.submit();
}


/*------------- 멘토 정보 수정 -------------*/
function mentorSave (){ /* 중복 체크 */
    if(!mentorForm.mainCrops.value) {
        mentorForm.mainCrops.focus();
        return;
    }
    if(mentorForm.years.value == "0") {
        mentorForm.years.focus();
        return;
    }

    mentorForm.submit();
}


/*------------- 회원 탈퇴 -------------*/
function quitBtn (){ /* 중복 체크 */

    myInfoForm.submit();

}
