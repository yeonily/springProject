/*
* community/register.html
* */


// 글자수 세는 이벤트
$('#r-content').keyup(function () {
    let $content = $(this).val();

    $("#count-txt").html($content.length);

    // 글자수 제한
    if ($content.length > 1000) {
        // 200자 부터는 타이핑 되지 않도록
        $(this).val($(this).val().substring(0, 1000));
        // 200자 넘으면 알림창 뜨도록
        alert('글자수는 1000자까지 입력 가능합니다.');
    };
});

// 입력 폼 포커스 시 이벤트
$('#r-title').on("focus", function(){
    $(this).css("border-color", "#47c880");
    $(this).css("border-width", "2px");
    $(this).next().css("opacity", 1);

});

$('#r-title').on("blur", function(){
    $(this).css("border-color", "#e1e4e7");
    $(this).css("border-width", "1px");
    $(this).next().css("opacity", 0);
});

$('#r-content').on("focus", function(){
    $(this).closest('.r-content').css("border-color", "#47c880");
    $(this).closest('.r-content').css("border-width", "2px");
    $(this).next().css("opacity", 1);

});

$('#r-content').on("blur", function(){
    $(this).closest('.r-content').css("border-color", "#e1e4e7");
    $(this).closest('.r-content').css("border-width", "1px");
    $(this).next().css("opacity", 0);
});

// 전부 입력해야 버튼 활성화
$(".writeBtn").prop("disabled", true);

$('.r-input').on("keyup", function() {
    if (!($("#r-title").val() == "") && !($("#r-content").val() == "")){
        $(".writeBtn").prop("disabled", false);
    } else {
        $(".writeBtn").prop("disabled", true);
    }
});

// 이미지 삭제 버튼
// $("div.uploadFile").mouseenter(function(){
//     $(this).next().show();
// });
// $("div.uploadFile").mouseleave(function(){
//     $(this).next().hide();
// });

$('div.uploadFile').hover(function() {
    $(this).next().show();
    $(this).css("filter", "grayscale(80%)");
}, function(){
    $(this).next().hide();
    $(this).css("filter", "grayscale(0)");
});

