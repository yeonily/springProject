/*
* program/list.html
* program/applyfin.html
* */

//헤더 대카 표시
$(document).ready(function () {
    $("ul.nav_ul li:nth-child(2)").children("a").addClass("selected-header");
})

//카테고리 선택 표시
$(".filter-button").on("click", function(){
    if($(this).hasClass('selected-button')){
        $(this).attr('class', 'filter-button');
    }else{
        $(this).attr('class', 'selected-button');
        $(this).siblings().attr('class', 'filter-button');
    }

})
