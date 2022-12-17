/*
* program/register.html
* */

vimg = $('<img>', {
    'src':'/image/program/next_register.gif',
    "class":"footer-img-next"})

function page1(){
    $("#pg1").show();
    $("#pg2").hide();
    $("#pg3").hide();
    $("#pg4").hide();
    $("#guide1").show();
    $("#guide2").hide();
    $("#guide3").hide();
    $("#guide4").hide();
    $(".before").attr("disabled", true);
    $(".next").html("다음").append(vimg);
    $(".next").removeClass("submitButton");
}

function page2(){
    $("#pg1").hide();
    $("#pg2").show();
    $("#pg3").hide();
    $("#pg4").hide();
    $("#guide1").hide();
    $("#guide2").show();
    $("#guide3").hide();
    $("#guide4").hide();
    $(".before").attr("disabled", false);
    $(".next").html("다음").append(vimg);
    $(".next").removeClass("submitButton");
}

function page3(){
    $("#pg1").hide();
    $("#pg2").hide();
    $("#pg3").show();
    $("#pg4").hide();
    $("#guide1").hide();
    $("#guide2").hide();
    $("#guide3").show();
    $("#guide4").hide();
    $(".before").attr("disabled", false);
    $(".next").html("다음").append(vimg);
    $(".next").removeClass("submitButton");
}

function page4(){
    $("#pg1").hide();
    $("#pg2").hide();
    $("#pg3").hide();
    $("#pg4").show();
    $("#guide1").hide();
    $("#guide2").hide();
    $("#guide3").hide(function(){
        $(".next").addClass("submitButton");
    });
    $(".before").attr("disabled", false);
    $(".next").html("제출하기");

}


//로딩 시 첫 페이지 먼저 보여주기
$(window).on("load", function () {
    $(this).addClass("pick");
    page1();
})

//왼쪽 탭 클릭하면 해당 부분 보여주기
$("#pg-1").on("click", function() {
    $(".side-box-title").removeClass("pick");
    $(this).addClass("pick");
    page1();
});

$("#pg-2").on("click", function() {
    $(".side-box-title").removeClass("pick");
    $(this).addClass("pick");
    page2();
});

$("#pg-3").on("click", function() {
    $(".side-box-title").removeClass("pick");
    $(this).addClass("pick");
    page3();
});

$("#pg-4").on("click", function() {
    $(".side-box-title").removeClass("pick");
    $(this).addClass("pick");
    page4();
});

//예시 선택하기
$(".ex-1").click(function () {
    $(".ex-1").addClass("active");
    $(".ex-2").removeClass("active");
    $(".example1").attr("src", "/image/program/ex1.gif")
})

$(".ex-2").click(function () {
    $(".ex-1").removeClass("active");
    $(".ex-2").addClass("active");
    $(".example1").attr("src", "/image/program/ex2.gif")
})

$(".ex-3").click(function () {
    $(".ex-3").addClass("active");
    $(".ex-4").removeClass("active");
    $(".example2").attr("src", "/image/program/ex3.gif")
})

$(".ex-4").click(function () {
    $(".ex-3").removeClass("active");
    $(".ex-4").addClass("active");
    $(".example2").attr("src", "/image/program/ex4.gif")
})

$(".ex-5").click(function () {
    $(".ex-5").addClass("active");
    $(".ex-6").removeClass("active");
    $(".example3").attr("src", "/image/program/ex5.gif")
})

$(".ex-6").click(function () {
    $(".ex-5").removeClass("active");
    $(".ex-6").addClass("active");
    $(".example3").attr("src", "/image/program/ex6.gif")
})

$(".ex-7").click(function () {
    $(".ex-7").addClass("active");
    $(".ex-8").removeClass("active");
    $(".example4").attr("src", "/image/program/ex7.gif")
})

$(".ex-8").click(function () {
    $(".ex-7").removeClass("active");
    $(".ex-8").addClass("active");
    $(".example4").attr("src", "/image/program/ex8.gif")
})

//예시 보여주기
$(".guide-title-container").on("click",function () {
    if($(this).parent().siblings(".guide-content").css("display")=="none"){
        $(this).parent().siblings(".guide-content").show();
        $(".arrow-icon").css({'transform':'rotate('+180+'deg)'});
    }else{
        $(this).parent().siblings(".guide-content").hide();
        $(".arrow-icon").css({'transform':'rotate('+0+'deg)'});
    }
})

//'예시보기' 클릭시 예시 보여주기
$(".e-chip1").on("click", function () {
    if($(".g-content1").css("display")=="none"){
        $(".g-content1").show();
        $(".a-icon1").css({'transform':'rotate('+180+'deg)'});
    }else{
        $(".op-o-g1").css("background-color", "#47c88040");
        setTimeout(fadeout, 200);
    }
})

$(".e-chip2").on("click", function () {
    if($(".g-content2").css("display")=="none"){
        $(".g-content2").show();
        $(".a-icon2").css({'transform':'rotate('+180+'deg)'});
    }else{
        $(".op-o-g2").css("background-color", "#47c88040");
        setTimeout(fadeout, 200);
    }
})

$(".e-chip3").on("click", function () {
    if($(".g-content3").css("display")=="none"){
        $(".g-content3").show();
        $(".a-icon3").css({'transform':'rotate('+180+'deg)'});
    }else{
        $(".op-o-g3").css("background-color", "#47c88040");
        setTimeout(fadeout, 200);
    }
})

$(".e-chip4").on("click", function () {
    if($(".g-content4").css("display")=="none"){
        $(".g-content4").show();
        $(".a-icon4").css({'transform':'rotate('+180+'deg)'});
    }else{
        $(".op-o-g4").css("background-color", "#47c88040");
        setTimeout(fadeout, 200);
    }
})

function fadeout(){
    $(".pg-opening-guide").css("background-color", "#fff");
}

//모달창
const btnModal = document.getElementById("btn-modal");
const modal = document.getElementById("c-overlay");
const closeModal = modal.querySelector(".button-1");
const stayModal = modal.querySelector(".button-2");
const modal2 = document.getElementById("c-overlay-2");
const modal3 = document.getElementById("c-overlay-3");
const okModal = document.querySelector(".button-ok");


btnModal.addEventListener("click", ev => {
    modal.style.display = "flex"
})

stayModal.addEventListener("click", evt => {
    modal.style.display = "none"
})

closeModal.addEventListener("click", evt => {
    window.location='/mypage/program';
})



/*다음 버튼*/
$("button.next").on("click", function() {
    if($("#pg-1").hasClass('pick')){
        $("#pg-1").removeClass("pick");
        $("#pg-2").addClass("pick");
        page2();
    }else if($("#pg-2").hasClass('pick')){
        $("#pg-2").removeClass("pick");
        $("#pg-3").addClass("pick");
        page3();
    }else if($("#pg-3").hasClass('pick')) {
        $("#pg-3").removeClass("pick");
        $("#pg-4").addClass("pick");
        page4();
    }
});

/*이전 버튼*/
$("button.before").on("click", function() {
     if($("#pg-2").hasClass('pick')){
        $("#pg-1").addClass("pick");
        $("#pg-2").removeClass("pick");
        page1();
    }else if($("#pg-3").hasClass('pick')) {
        $("#pg-2").addClass("pick");
        $("#pg-3").removeClass("pick");
        page2();
    }else if($("#pg-4").hasClass('pick')) {
        $("#pg-3").addClass("pick");
        $("#pg-4").removeClass("pick");
        page3();
    }
});

//제출하기 빨간줄 뜨기
$(".opening-footer").on("click", "button#submitRegister", function () {
    console.log("버튼 누름");
    var inputList = new Array();
    var textareaList = new Array();
    let check = false;

    $("input").each(function (index, item) {
        inputList.push($(item));
    });
    $("textarea").each(function (index, item) {
        textareaList.push($(item));
    });

    for (let i = 0; i < inputList.length; i++) {
        if (inputList[i].val() == '') {
            if(inputList[i].hasClass('c-text-field-input')){
                inputList[i].css("border", "1px solid red");
                check = false;
            }else if(inputList[i].hasClass('ql-editor')){
                inputList[i].parent().parent().css("border", "1px solid red");
                check = false;
            }else if(inputList[i].hasClass('c-text-field-input2')) {
                inputList[i].css("border", "1px solid red");
                check = false;
            }
        }
    }

    for (let i = 0; i < textareaList.length; i++) {
        if (textareaList[i].val() == '') {
            if(textareaList[i].hasClass('textarea-editor')){
                textareaList[i].parent().parent().css("border", "1px solid red");
                check = false;
            }else if(textareaList[i].hasClass('ql-editor-text')){
                textareaList[i].parent().parent().css("border", "1px solid red");
                check = false;
            }
        }
    }
    if(!check) {
        modal2.style.display = "flex"

        okModal.addEventListener("click", evt => {
            modal2.style.display = "none"
        })
        return;
    }
    if(check){
        $("button.okay").on("click", function(e){
            e.preventDefault();
            let text = "";
            $.each($(".uploadResult ul li"), function(i, li){
                let fileName = $(li).data("file-name");
                let fileUploadPath = $(li).data("file-upload-path");
                let fileUuid = $(li).data("file-uuid");
                let fileSize = $(li).data("file-size");
                let fileImageCheck = $(li).data("file-image-check");
                console.log(fileName);
                console.log(fileUploadPath);
                console.log(fileUuid);
                console.log(fileSize);
                console.log(fileImageCheck);

                text += `<input type="hidden" name="files[` + i + `].fileName" value="` + fileName + `">`;
                text += `<input type="hidden" name="files[` + i + `].fileUploadPath" value="` + fileUploadPath + `">`;
                text += `<input type="hidden" name="files[` + i + `].fileUuid" value="` + fileUuid + `">`;
                text += `<input type="hidden" name="files[` + i + `].fileSize" value="` + fileSize + `">`;
                text += `<input type="hidden" name="files[` + i + `].fileImageCheck" value="` + fileImageCheck + `">`;
            });
            console.log(text);
            $("form#writeForm").append(text).submit();

        });
    }

    modal3.style.display = "flex"



});

//빨간줄 사라지기
$('input[type=text]').on("keyup", function () {
    if($(this).hasClass('c-text-field-input')){
        $(this).css("border", "1px solid #e1e4e6");
    }else if($(this).hasClass('ql-editor')){
        $(this).parent().parent().css("border", "1px solid #e1e4e6");
    }else if($(this).hasClass('c-text-field-input2')) {
        $(this).css("border", "1px solid #e1e4e6");
    }
});

$('input[type=date]').on("change", function () {
        $(this).css("border", "1px solid #e1e4e6");
});

$('input[type=time]').on("change", function () {
    $(this).css("border", "1px solid #e1e4e6");
});

$('textarea').on("keyup", function () {
    if($(this).hasClass('textarea-editor')){
        $(this).parent().parent().css("border", "1px solid #e1e4e6");
    }else if($(this).hasClass('ql-editor-text')){
        $(this).parent().parent().css("border", "1px solid #e1e4e6");
    }
});

//버튼 클릭 시 css변경, 하나만 클릭하기
$(".select-chip").on("click", function(){
    if($(this).hasClass('selected-c-chip')){
        $(this).removeClass('selected-c-chip');
    }else{
        $(this).addClass('selected-c-chip');
        $(this).siblings().removeClass('selected-c-chip');
    }

})

// 글자수 세는 이벤트
$('#r-content').keyup(function () {
    let $content = $(this).val();

    $("#count-txt").html($content.length);

    // 글자수 제한
    if ($content.length > 76) {
        // 200자 부터는 타이핑 되지 않도록
        $(this).val($(this).val().substring(0, 76));
        // 200자 넘으면 알림창 뜨도록
        alert('글자수는 76자까지 입력 가능합니다.');
    };
});

//첨부파일 파일명 보이기
function fileUpload(){
    var fileValue = $("#file-input").val().split("\\");
    var fileName = fileValue[fileValue.length-1]; // 파일명
        $("#file-content").html(fileName);
        $(".c-callout-information-2").show();
}

//삭제 버튼
$('input[type="file"]').on("change",function(){
    if($("#file-content").html() == ""){
        $("#deleteBtn").hide();
    }else{
        $("#deleteBtn").show();
    }
});

//첨부파일 업로드 취소
function cancelFile(){
    $("input[type='file']").val("");
    $("#file-content").html("");
    console.log($("input[type='file']").val(""));
    $("#deleteBtn").hide();
    $(".c-callout-information-2").hide();
}

$("#first_select span").on("click" , function () {
    console.log("들어옴1");
    var first_text = $("#first_selected").find('.selected-c-chip').text();
    console.log(first_text);
})
$("#second_select span").on("click" , function () {
    console.log("들어옴2");
    var second_text = $("#first_selected").find('.selected-c-chip').text();
    console.log(second_text);
})



$(".button-ok").on("click",function () {
    //일반인용,멘티전용 클릭
    var first_text = $("#first_selected").find('.selected-c-chip').text();
    //초급,중급,고급 클릭
    var second_text = $("#second_selected").find('.selected-c-chip').text();
    console.log(first_text);
    console.log(second_text);
    // test1 = "BASIC";
    // test2 = "ALL_USER";

    var text = "";
    // text += '<input type="hidden" name="programType" value="BASIC">'
    // text += '<input type="hidden" name="programLevel" value="ALL_USER">'
    text += '<input type="hidden" name="programTypeString" value="'
    text += first_text
    text += '">'
    text += '<input type="hidden" name="programLevelString" value="'
    text += second_text
    text += '">'
    // text += '<input type="hidden" name="programId" value="'
    // text += 9999
    // text += '">'
    $("#writeForm").append(text);
})

// var first_text = $("#first_selected").find('.selected-c-chip').text();
// console.log(first_text);
//
// //초급,중급,고급 클릭
// var second_text = $("#second_selected").find('.selected-c-chip').text();
// console.log(second_text);