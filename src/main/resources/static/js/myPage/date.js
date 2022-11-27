/*
* myPage/joinForm.html
* */

$(document).ready(function () {
    setDateBox();
});

// select box 연도 , 월 표시
function setDateBox() {
    var dt = new Date();
    var year = "";
    var com_year = dt.getFullYear();

    // 발행 뿌려주기
    $("#year").append("<option value=''>연도</option>");

    // 올해 기준으로 -50년부터 +1년을 보여준다.
    for (var y = (com_year - 15); y >= (com_year - 50); y--) {
        $("#year").append("<option value='" + y + "'>" + y + "</option>");
    }

    // 월 뿌려주기(1월부터 12월)
    var month;
    $("#month").append("<option value=''>월</option>");
    for (var i = 1; i <= 12; i++) {
        $("#month").append("<option value='" + i + "'>" + i + "</option>");
    }

    // 일 뿌려주기(1일부터 31일)
    var day;
    $("#day").append("<option value=''>일</option>");
    for (var i = 1; i <= 31; i++) {
        $("#day").append("<option value='" + i + "'>" + i + "</option>");
    }

}