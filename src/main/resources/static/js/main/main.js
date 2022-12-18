/*검색창 숨기기*/
$(".search_form").css("visibility", "hidden");


/*메인 배너 슬라이드*/
var swiper1 = new Swiper(".mySwiper1", {
    navigation: {
        nextEl: ".swiper-button-next",
        prevEl: ".swiper-button-prev"
    },
    pagination: {
        el: ".swiper-pagination"
    },
    autoplay: {
        delay: 5000,
    },
    mousewheel: false,
    allowTouchMove : true,
    loop: true,
    loopAdditionalSlides : 1,
});

/*두 번째 배너 슬라이드*/
var swiper2 = new Swiper(".mySwiper2", {
    slidesPerView: 4,
    spaceBetween: 30,
    slidesPerGroup: 2,
    navigation: {
        nextEl: ".swiper-button.next2",
        prevEl: ".swiper-button.prev2",
    },
    allowTouchMove : true,
    mousewheel: false,
});

/*세 번째(커뮤니티) 슬라이드*/
var swiper3 = new Swiper(".mySwiper3", {
    freeMode: true,
    slidesPerView: 2,
    spaceBetween: 30,
    navigation: {
        nextEl: ".swiper-button.next3",
        prevEl: ".swiper-button.prev3",
    },
    mousewheel: false,
    allowTouchMove : true,
});

/*네 번째(프로그램) 슬라이드*/
var swiper4 = new Swiper(".mySwiper4", {
    slidesPerView: 4,
    spaceBetween: 30,
    navigation: {
        nextEl: ".swiper-button.next4",
        prevEl: ".swiper-button.prev4",
    },
    allowTouchMove : true,
    mousewheel: false,
});

/*다섯 번째(아르바이트) 슬라이드*/
var swiper5 = new Swiper(".mySwiper5", {
    slidesPerView: 4,
    spaceBetween: 30,
    navigation: {
        nextEl: ".swiper-button.next5",
        prevEl: ".swiper-button.prev5",
    },
    allowTouchMove : true,
    mousewheel: false,
});

$(".main_banner").on("mouseover", function() {
    $("div.main_banner div.swiper-button-next").css({visibility: "visible", transition : "all 0.5s", opacity: 1});
    $("div.main_banner div.swiper-button-prev").css({visibility: "visible", transition : "all 0.5s", opacity: 1});
});
$(".main_banner").on("mouseout", function() {
    $("div.main_banner div.swiper-button-next").css({visibility: "hidden", transition : "all 0.5s", opacity: 0});
    $("div.main_banner div.swiper-button-prev").css({visibility: "hidden", transition : "all 0.5s", opacity: 0});
});


/*현재 화면의 크기를 구분하여 슬라이드 적용*/
let bodySize = ($("body").css("width").split("px"));
if(bodySize[0] < 768) {
    /*멘토 슬라이드*/
    swiper2 = new Swiper(".mySwiper2", {
        slidesPerView: 2.3,
        spaceBetween: 16,
        // slidesPerGroup: 3,
        allowTouchMove : true,
        mousewheel: false,
    });

    /*커뮤니티 슬라이드*/
    swiper3 = new Swiper(".mySwiper3", {
        freeMode: true,
        slidesPerView: 1.4,
        spaceBetween: 17,
        mousewheel: false,
        allowTouchMove : true,
    });

    /*프로그램 슬라이드*/
    swiper4 = new Swiper(".mySwiper4", {
        slidesPerView: 2.3,
        spaceBetween: 16,
        allowTouchMove : true,
        mousewheel: false,
    });

    /*아르바이트 슬라이드*/
    swiper5 = new Swiper(".mySwiper5", {
        slidesPerView: 2.3,
        spaceBetween: 16,
        allowTouchMove : true,
        mousewheel: false,
    });
}

//게시판 댓글 총 개수 넣기
// var boardCount = $(".slideContent").length;
// console.log(boardCount);
function replyCountResult(){
    var ret = []
    $('.boardCount').each(function(index,item){
        ret.push($(this).val())
    });
    $('.resultReplyCount').each(function (index,item) {
        $(this).text(ret[index]);
    })
    console.log(ret);
    return ret;

};

replyCountResult();

//댓글 단 사람 닉네임 첫번째 글자 출력
function nicknameFirst() {
    var ret = []
    $('.nicknameValue').each(function () {
        var result = $(this).val().substr(0,1);
        ret.push(result);
        console.log(ret);
    })

    $('.nicknameFirst').each(function (index, item) {
        $(this).text(ret[index]);
    })

}
nicknameFirst();

//테스트
console.log("스코어는:" + $(".scores").val());