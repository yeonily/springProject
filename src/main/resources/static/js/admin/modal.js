
/*-----------------------------------------------------------*/
/*모집마감일 경우*/
/*-----------------------------------------------------------*/
var $status = $("#main_fixed_category"); // 모집 상태를 저장

if($("#main_fixed_category").text() == "모집마감" || $(".left-div span").text() == "모집마감") {
    $("#main_fixed_category").text("모집마감");
    $(".left-div span").text("모집마감");
    $("#main_fixed_button").attr("disabled", "false");
    $("#main_fixed_button").html("모집마감");
    $("#main_fixed_button").css({background : "#357e55", cursor : "default"});
}


/*-----------------------------------------------------------*/
/*지원하기 버튼 클릭 시 모달*/
/*-----------------------------------------------------------*/
$("button#main_fixed_button").on("click", function(){
    $("#modal").show();
});
$("button.cancel").on("click", function(){
    $("#modal").hide();
});

/*메일전송 완료 버튼 클릭 시*/
$("button.applyBtn").on("click", function(){
    $("#modal").hide();
    $("#modalSuccess").show();
});



/*-----------------------------------------------------------*/
/*삭제하기 버튼 클릭 시 모달*/
/*-----------------------------------------------------------*/
$("button#remove_button").on("click", function() {
    if(confirm("삭제하기를 선택하셨습니다. 확인(삭제) 또는 취소 버튼을 클릭해주세요.")) {
        alert("글 삭제가 정상적으로 처리되었습니다.");
        location.href = "/alba/list";
    } else {
        alert("글 삭제가 취소되었습니다.");
    }
});