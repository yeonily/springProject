/*
* program/detail.html
* */

//sidebox fixed
const content = document.querySelector('.content');
const sidebox = document.querySelector('.pg-detail-sidebox-wrapper');

// 컨텐츠 영역부터 브라우저 최상단까지의 길이 구하기
const contentTop = content.getBoundingClientRect().top + window.scrollY;

window.addEventListener('scroll', function(){
    if(window.scrollY >= contentTop) {
        sidebox.classList.add('fixed');
    }else{
        sidebox.classList.remove('fixed');
    }

});

//헤더 대카 표시
$(document).ready(function () {
    $("ul.nav_ul li:nth-child(2)").children("a").addClass("selected-header");
})
