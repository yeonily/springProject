/*
* mento/chatting.html
* */

/*헤더 멘토 표시*/
$(document).ready(function () {
    $("ul.nav_ul li:nth-child(1)").children("a").addClass("selected-header");
})

/*-----------------------------------------------------------*/
/*멘토 신청 버튼 클릭 시 모달*/
/*-----------------------------------------------------------*/
$("#apply_button").on("click", function(){
    if($("#apply_button").val() == '멘토 끊기'){
        $("#quitmodal").show();
    }
    else {
        $("#modal").show();
    }
});
$("button.cancel").on("click", function(){
    $("#quitmodal").hide();
    $("#modal").hide();
});

/*한줄평 작성 후 멘토 신청 완료 버튼 클릭 시*/
$("button.applyBtn").on("click", function(){

    /*한줄평 작성 안했을 시*/
    if($(".modal_input").val() == ''){
        $(".modal_input").css("border", "1px solid red");
        $(".modal_input").attr("placeholder", "인사말을 입력해주세요");
        return;
    }
    $("#modal").hide();
    $("#modalSuccess").show();
});
$("#completeBtn").on("click",function () {
    $("#modalSuccess").hide();
    $("#apply_button").val("멘토 끊기");
})

/*멘토 끊기 버튼 눌렀을 시*/
$(".quitBtn").on("click",function () {
    $("#quitmodal").hide();
    $("#apply_button").val("멘토 신청");
})

/*취소 눌렀을 시*/
$(".quitCancel").on("click" , function () {
    $("#quitmodal").hide();
})

/*버튼 hover*/
$("#apply_button").hover(function () {
    $(this).css("background-color","#357e55");
}, function () {
    $(this).css("background-color","#47c880");
})
