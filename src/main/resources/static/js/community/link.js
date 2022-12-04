/*
* community, notice 링크 연결
* */

/*
* information/policy.html
* */

$(".fake-header").css("height", "113px");

//헤더 대카 표시
$(document).ready(function () {
    $("ul.nav_ul li:nth-child(4)").children("a").addClass("selected-header");
})

//서브헤더 보이기
$(document).ready(function () {
    $("#sub_header").css("display", "block");
});


//서브헤더 소카 이름
$(document).ready(function () {
    $('div.menuList ul li:nth-child(1)').children().text("커뮤니티");
    $('div.menuList ul li:nth-child(2)').children().text("공지사항");
    $('div.menuList ul li:nth-child(3)').children().detach();
    $('div.menuList ul li:nth-child(4)').children().detach();
})

var btnList = [];
btnList = $('div.menuList ul li button');
$(btnList[0]).attr("onclick", "location.href='/community/community'");
$(btnList[1]).attr("onclick", "location.href='/notice'");