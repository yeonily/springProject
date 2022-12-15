/*
* mento/detail.html
* */

/*헤더 멘토 표시*/
$(document).ready(function () {
    $("ul.nav_ul li:nth-child(1)").children("a").addClass("selected-header");
})

//sidebox fixed
const content = document.querySelector('.main_intro');
const sidebox = document.querySelector('.main_fixed');


// 컨텐츠 영역부터 브라우저 최상단까지의 길이 구하기
const contentTop = content.getBoundingClientRect().top + window.scrollY;

window.addEventListener('scroll', function(){

    if(window.scrollY >= contentTop) {
        sidebox.classList.add('fixed');
    }else{
        sidebox.classList.remove('fixed');
    }

});

//멘토 후기 별 체크
// $("#star_count_check>.star_check").on("click" , function () {
//     console.log($(this));
//     if($(this).hasClass("star_check") === true){
//         $(this).nextAll().attr('class','star_uncheck');
//     }else{
//         $(this).prevAll().attr('class','star_check');
//         $(this).attr('class','star_check');
//     }
// })
$("#write-review").on("click" , "#star_count_check>.starCheck", function () {
    console.log($(this));
    if($(this).hasClass("star_check") === true){
        $(this).nextAll().attr('class','starCheck star_uncheck');
    }else{
        $(this).prevAll().attr('class','starCheck star_check');
        $(this).attr('class','star_check');
    }
})


/*
* 별 개수 확인
 */

let counts = $("#star_count_check>.star_count");
let starScore = "";
let starText = "";

counts.each(function (i, item) {
    starScore = $(item).children('.star_check').length;
    switch (starScore) {
        case 5 :
            starText = "매우 추천해요!";
            break
        case 4 :
            starText = "추천해요!";
            break
        case 3 :
            starText = "보통이에요!";
            break
        case 2 :
            starText = "비추천해요!";
            break
        case 1 :
            starText = "매우 비추천해요!";
            break
    }
    console.log(starText);
    $(item).children('.main_reply_gray').text(starText);
})

/*-----------------------------------------------------------*/
/*멘토 신청 버튼 클릭 시 모달*/
/*-----------------------------------------------------------*/
$("button#mento_submit").on("click", function(){
    $("#modal").show();
});
$("button.cancel").on("click", function(){
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

/*글자 수 세는 이벤트*/
$("#write-review").on("keyup", '#main_reply_textarea', function() {
    let $content = $(this).val();

    $("#main_reply_textarea_p_count").html($content.length);

    if($content.length == 0 ){
        $("#main_reply_button").css("background-color", "#d1d9e1");
        $("#main_reply_button").attr("disabled", true);

        return;
    }
    else{
        $("#main_reply_button").attr("disabled", false);
        $("#main_reply_button").css("background-color","#47c880");

        $("#main_reply_button").hover(function () {
            console.log("들어옴");
            $(this).css("background-color" ,"#357e55");
        },function (){
            console.log("들어옴2");
            $(this).css("background-color","#47c880");
        });
    }

    // 글자수 제한
    if ($content.length > 200) {
        // 200자 부터는 타이핑 되지 않도록
        $(this).val($(this).val().substring(0, 200));
        // 200자 넘으면 알림창 뜨도록
        alert('글자수는 200자까지 입력 가능합니다.');
    };

});


// 댓글 작성 후 후기 작성 버튼 눌렀을 때
$("#write-review").on("click", "#main_reply_button", function(e){
    e.preventDefault();
    // 글자가 적혀있지 않을 때
    if($("#main_reply_textarea").length == 0 ){
        alert("수기가 입력되지 않았습니다.");
        return;
    }

    reviewService.reviewAdd({
        reviewContent: $("textarea[name=reviewContent]").val(),
        mentorBoardId: mentorBoardId,
        reviewStar: $("#star_count_check .star_check").length
    }, function () {
        show();
        // reviewService.getTotal(mentorBoardId, get)
    });
    $("textarea[name=reviewContent]").val("");
})
