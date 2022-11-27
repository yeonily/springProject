var $status = $(".list_td .title span"); // 모집 상태를 저장


/*-----------------------------------------------------------*/
                    //필터 선택 시 검정글자로 표시
/*-----------------------------------------------------------*/
$(".top-div-filter span").on("click", function() {
    $(this).css("color", "#000000");
    $(".top-div-filter span").not(this).css({color: '#959595'});
});


/*-----------------------------------------------------------*/
                    //모집마감일 경우 글씨 어둡게
/*-----------------------------------------------------------*/
for(var i = 0; i < $status.length; i++) {
    if($status.eq(i).text() == "모집마감") {
        $status.eq(i).parent().parent().parent().css("color", "#dbdbdb");
        $status.eq(i).parent().css("color", "#dbdbdb");
    }
}




