/*
* information/crops.html
* */

//서브헤더 보이기
$(document).ready(function () {
    $("#sub_header").css("display", "block");
})

//헤더 대카 표시
$(document).ready(function () {
    $("ul.nav_ul li:nth-child(5)").children("a").addClass("selected-header");
})

//서브헤더 소카 이름
$(document).ready(function () {
    $('div.menuList ul li:nth-child(1)').children().text("청년정책");
    $('div.menuList ul li:nth-child(2)').children().text("농업정보");
    $('div.menuList ul li:nth-child(3)').children().text("준비도 테스트");
    $('div.menuList ul li:nth-child(4)').children().text("단계별 테스트");
})


var btnList = [];
btnList = $('div.menuList ul li button');
$(btnList[0]).attr("onclick", "location.href='/information/policy'");
$(btnList[1]).attr("onclick", "location.href='/information/crops'");
$(btnList[2]).attr("onclick", "location.href='/information/ready'");
$(btnList[3]).attr("onclick", "location.href='/information/step'");