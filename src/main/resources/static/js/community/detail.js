/*
* /community/detail.html
* */

// 헤더 검색창 없애기
$(".search_form").css("visibility", "hidden");

// 댓글 입력할 시 div 눌러야 입력 폼 활성화
$("#re-before").on("click", function (){
    $(this).hide();
    $(this).next().show();
});


// 댓글 내용이 있을 때 버튼 활성화
$("button.reply-register").prop("disabled", true);
$("#re-register").keyup(function(){
    if($(this).val() == "") {
        $("button.reply-register").prop("disabled", true);
    } else {
        $("button.reply-register").prop("disabled", false);
    }
});

// 작성자 일때 수정/삭제 옵션 버튼
$(".drop-option").on("click", function (){
    if (!($(this).next().hasClass("show"))){
        $(".drop-option-menu").removeClass("show");
        $(".drop-option-menu").hide();

        $(this).next().addClass("show");
        $(this).next().show();

    } else if($(this).next().hasClass("show")) {
        $(".drop-option-menu").removeClass("show");
        $(".drop-option-menu").hide();
    }
});

// 작성자 일때 수정/삭제 옵션 버튼
$("#reply-lists").on("click",".drop-option", function (){
    if (!($(this).next().hasClass("show"))){
        $(".drop-option-menu").removeClass("show");
        $(".drop-option-menu").hide();

        $(this).next().addClass("show");
        $(this).next().show();

    } else if($(this).next().hasClass("show")) {
        $(".drop-option-menu").removeClass("show");
        $(".drop-option-menu").hide();
    }
});

// textarea 크기 자동조절
$(".reply-input").keyup(function () {
    $(this).css("height", "46px");
    $(this).css("height", (10 + this.scrollHeight) + "px");
});

// 댓글 수정 눌렀을 때
$("button.m-save").prop("disabled", true);

$(".re-modify").on("click", function(){
    let $modify = $(this).closest('.drop-option-wrap');
    let $reply = $(this).parents('.top-wrap').next();
    let $txt = $("textarea.reply-modify")
    let $temp = $reply.text();;

    $(document).ready(function () {
        $txt.each(function(i, e){
            $(this).replaceWith("<p class='commu-content re-content'>" + $(this).html() + "</p>");
        });
    });
    $(".drop-option-menu").hide();
    $(".drop-option-menu").removeClass("show");

    $('.modify-group').hide();
    $('.drop-option-wrap').show();

    $modify.hide();
    $modify.next().show();

    $reply.replaceWith("<textarea class='reply-modify'>" + $temp + "</textarea>");
    let $modifyText = $(this).parents('.top-wrap').next();

// 댓글 수정버튼 활성화
    $modifyText.keyup(function(){
        console.log($(this).val());

        if($(this).val() == $temp || $(this).val() == "") {
            $("button.m-save").prop("disabled", true);
        } else {
            $("button.m-save").prop("disabled", false);
        }
    });
});

// 댓글 수정취소 눌렀을 때
$(".m-cancel").on("click", function(){
    let $cancel = $(this).closest('.modify-group');
    let $reply = $(this).parents('.top-wrap').next();

    $(".drop-option-menu").hide();
    $(".drop-option-menu").removeClass("show");

    $cancel.hide();
    $cancel.prev().show();

    $reply.replaceWith("<p class='commu-content re-content'>" + $reply.html() + "</p>");
});