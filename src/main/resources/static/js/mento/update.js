/*
* mento/write.html
* */

// $("#first_page").show();
// $("#second_page").hide();


/*
* 예시 클릭 시 바뀌는 부분
* */

//경력 소개 예시 눌렀을 때
$("#carrer_intro_example").on("click" , function () {
    $("#aside_first_page").show();
    $("#aside_second_page").hide();
    $("#carrer_intro").show();
    $("#first_page_arrow1").css("transform","rotate(0deg)");
    $("#first_page_background1").css("background-color", "#47c88040");
    setTimeout(fadeout, 200);
})
//멘토 추천 이유 예시 눌렀을 때
$("#mento_result_example").on("click" , function () {
    $("#aside_first_page").show();
    $("#aside_second_page").hide();
    $("#mento_result").show();
    $("#first_page_arrow2").css("transform","rotate(0deg)");
    $("#first_page_background2").css("background-color", "#47c88040");
    setTimeout(fadeout, 200);
})
//멘토 홍보글 제목 작성 예시 눌렀을 때
$("#mento_intro_title_example").on("click" , function () {
    $("#aside_first_page").hide();
    $("#aside_second_page").show();
    $("#mento_intro_title").show();
    $("#second_page_arrow1").css("transform","rotate(0deg)");
    $("#second_page_background1").css("background-color", "#47c88040");
    setTimeout(fadeout, 200);
})
//멘토 홍보글 내용 작성 예시 눌렀을 때
$("#mento_intro_content_example").on("click" , function () {
    $("#aside_first_page").hide();
    $("#aside_second_page").show();
    $("#mento_intro_content").show();
    $("#second_page_arrow2").css("transform","rotate(0deg)");
    $("#second_page_background2").css("background-color", "#47c88040");
    setTimeout(fadeout, 200);
})

function fadeout(){
    $(".aside_right_inner1").css("background-color", "#fff");
}

//경력 예시 화살표 눌렀을 때
$("#first_page_arrow1").on("click", function () {
    if($("#carrer_intro").css("display")=="none"){
        $("#first_page_arrow1").css("transform","rotate(0deg)");
        $("#carrer_intro").show();
    }
    else{
        $("#first_page_arrow1").css("transform","rotate(180deg)");
        $("#carrer_intro").hide();
    }
})

//멘토 추천 이유 화살표 눌렀을 때
$("#first_page_arrow2").on("click", function () {
    if($("#mento_result").css("display")=="none"){
        $("#first_page_arrow2").css("transform","rotate(0deg)");
        $("#mento_result").show();
    }
    else{
        $("#first_page_arrow2").css("transform","rotate(180deg)");
        $("#mento_result").hide();
    }
})

//멘토 홍보글 제목 예시 화살표 눌렀을 때
$("#second_page_arrow1").on("click", function () {
    if($("#mento_intro_title").css("display")=="none"){
        $("#second_page_arrow1").css("transform","rotate(0deg)");
        $("#mento_intro_title").show();
    }
    else{
        $("#second_page_arrow1").css("transform","rotate(180deg)");
        $("#mento_intro_title").hide();
    }
})

//멘토 홍보글 내용 예시 화살표 눌렀을 때
$("#second_page_arrow2").on("click", function () {
    if($("#mento_intro_content").css("display")=="none"){
        $("#second_page_arrow2").css("transform","rotate(0deg)");
        $("#mento_intro_content").show();
    }
    else{
        $("#second_page_arrow2").css("transform","rotate(180deg)");
        $("#mento_intro_content").hide();
    }
})


// 다음 버튼 누를시 이동, 버튼 색 변경
function nextButton(){
    $("#first_page").hide();
    $("#second_page").show();
    $("#first_aside").hide();
    $("#second_aside").hide();
    $("#third_aside").show();
    $("#last_aside").show();
    $("#footer_button_prev").css("cursor","pointer");
    $("#footer_button_prev").css("background-color","#f3f4f5");
    $("#footer_button_prev").css("color","#767d82");
    $(".list_sub").css("color","#202325");
    $(".list_sub").css("font-weight",500);
    $(".list_main").css("color" , "#adb3b8");
    $(".list_main").css("font-weight",400);
    $("#footer_button_next").hide();
    $(".submitBtn").show();

}

//  이전 버튼 누를 시 이동, 버튼 색 변경
function prevButton(){
    $("#second_page").hide();
    $("#first_page").show();
    $("#first_aside").show();
    $("#second_aside").show();
    $("#third_aside").hide();
    $("#last_aside").hide();
    $("#footer_button_prev").css("cursor","not-allowed");
    $("#footer_button_prev").css("background-color","#f3f4f5");
    $("#footer_button_prev").css("color","#767d82");
    $(".list_main").css("color","#202325");
    $(".list_main").css("font-weight",500);
    $(".list_sub").css("color" , "#adb3b8");
    $(".list_sub").css("font-weight",400);
    $("#footer_button_next").text("다음");
    $("#footer_button_next").append("<img src=\"/image/program/next_register.gif\" class=\"footer_next_image\">");
    $("#footer_button_next").show();
    $(".submitBtn").hide();
}

//왼쪽 사이드 바 클릭 시 변경
function listMainClick(){
    $("#first_page").show();
    $("#second_page").hide();
    $("#first_aside").show();
    $("#second_aside").show();
    $("#third_aside").hide();
    $("#last_aside").hide();
    $("#footer_button_prev").css("cursor","not-allowed");
    $("#footer_button_prev").css("background-color","#f3f4f5");
    $("#footer_button_prev").css("color","#767d82");
    $(".list_main").css("color","#202325");
    $(".list_main").css("font-weight",500);
    $(".list_sub").css("color" , "#adb3b8");
    $(".list_sub").css("font-weight",400);
    $("#footer_button_next").text("다음");
    $("#footer_button_next").append("<img src=\"/image/program/next_register.gif\" class=\"footer_next_image\">")
    $("#footer_button_next").show();
    $(".submitBtn").hide();
    $("#aside_first_page").show();
    $("#aside_second_page").hide();
    $("#first_title").show();
    $("#second_title").hide();
}

function listSubClick(){
    $("#first_page").hide();
    $("#second_page").show();
    $("#first_aside").hide();
    $("#second_aside").hide();
    $("#third_aside").show();
    $("#last_aside").show();
    $("#footer_button_prev").css("cursor","pointer");
    $("#footer_button_prev").css("background-color","#f3f4f5");
    $("#footer_button_prev").css("color","#767d82");
    $(".list_sub").css("color","#202325");
    $(".list_sub").css("font-weight",500);
    $(".list_main").css("color" , "#adb3b8");
    $(".list_main").css("font-weight",400);
    $("#footer_button_next").hide();
    $(".submitBtn").show();
    $("#aside_first_page").hide();
    $("#aside_second_page").show();
    $("#first_title").hide();
    $("#second_title").show();
}

let arrayFile = [];
/*$("a.list").on("click", function(e){
    e.preventDefault();
    location.href = "/board/list" + queryString;
});*/

/*파일이 넣어졌을 때*/
$("input[type='file']").on("change", function(){
    let formData = new FormData();
    let $inputFile = $("input[name='upload']");
    let files = $inputFile[0].files;

    console.log(Array.from(files));

    Array.from(files).forEach(file => arrayFile.push(file));
    const dataTransfer = new DataTransfer();
    arrayFile.forEach(file => dataTransfer.items.add(file));
    $(this)[0].files = dataTransfer.files;

    console.log($(this)[0].files);

    $(files).each(function(i, file){
        formData.append("upload", file);
    });

    $.ajax({
        url: "/mentofile/upload",
        type: "post",
        data: formData,
        contentType: false,
        processData: false,
        success: showUploadResult
    });
});

/*파일 선택 시 미리보기*/
function showUploadResult(files){
    let text = "";
    $(files).each(function(i, file){
        text += `<li data-file-size="` + file.fileSize + `" data-file-name="` + file.fileName + `" data-file-upload-path="` + file.fileUploadPath + `" data-file-uuid="` + file.fileUuid + `" data-file-image-check="` + file.fileImageCheck + `">`;
        text += `<span>X</span>`;
        if(!file.fileImageCheck){
            text += `<img src="/image/program/count.gif" width="100">`;
        }else{
            text += `<img src="/mentofile/display?fileName=` + file.fileUploadPath + `/s_` + file.fileUuid + "_" + file.fileName + `">`;
        }
        text += `<p>` + file.fileName +`(` + parseInt(file.fileSize / 1024) + `KB)</p>`
        text += `</li>`;
    });
    $(".uploadResult ul").append(text);
}

/*미리보기 삭제*/
$(".uploadResult ul").on("click", "span", function(){
    const $li = $(this).closest("li");
    let i = $(".uploadResult ul span").index($(this));
    let uploadPath = $li.data("file-upload-path");
    let fileName = $li.data("file-uuid") + "_" + $li.data("file-name");
    $.ajax({
        url: "/mentofile/delete",
        type: "post",
        data: {uploadPath: uploadPath, fileName: fileName, fileImageCheck: $li.data("file-image-check")},
        success: function(){
            $li.remove();
            arrayFile.splice(i, 1);
            const dataTransfer = new DataTransfer();
            arrayFile.forEach(file => dataTransfer.items.add(file));
            $("input[name='upload']")[0].files = dataTransfer.files;
        }
    });
});

//클릭 시
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
    $("form#writeForm").append(text);
});

/*제출하기 버튼 클릭 시 유효성 검사*/
$(".submitBtn").on("click" , function () {
    var inputList = new Array();
    var textareaList = new Array();
    let check = true;

    $("input:not(.note-editor input , #attach)").each(function(index, item){
        inputList.push($(item));
    });

    $("textarea").each(function(index, item) {
        textareaList.push($(item));
    });

    console.log(textareaList.length);
    for(let i = 0; i < inputList.length; i++) {
        if(inputList[i].val() == null) {
            inputList[i].css("border", "1px solid red");
            check = false;
        }
    }
    for(let i = 0; i < textareaList.length; i++) {
        console.log(textareaList[i].val());
        if(textareaList[i].val() == null) {
            textareaList[i].css("border", "1px solid red");
            check = false;
        }
    }

    if(!check) {
        alert("입력하지 않은 값이 있습니다.");
    } else {
        alert("글이 정상적으로 등록되었습니다.");
        $("form#writeForm").submit();
    }
})


/*function addFile(e){
    var sel_files = [];
    var files = e.target.files;
    var filesArr = Array.prototype.slice.call(files);

    filesArr.forEach(function (f) {
        if(!f.type.match("image.*")){
            alert("확장자는 이미지 확장자만 가능합니다.");
            return
        }
        sel_files.push(f);

        var reader = new FileReader();
        reader.onload = function (e) {
            var img_html = "<img src=\"" + e.target.result + "\" />";
            $(".imgs_wrap").append(img_html);
        }
        reader.readAsDataURL(f);
    })
}*/


/*
/!*첨부파일 추가*!/
var fileNo = 0;
var filesArr = new Array();

function addFile(obj){
    var maxFileCnt = 5;   // 첨부파일 최대 개수
    var attFileCnt = document.querySelectorAll('.filebox').length;    // 기존 추가된 첨부파일 개수
    var remainFileCnt = maxFileCnt - attFileCnt;    // 추가로 첨부가능한 개수
    var curFileCnt = obj.files.length;  // 현재 선택된 첨부파일 개수

    console.log(remainFileCnt);
    console.log(curFileCnt);

    // 첨부파일 개수 확인
    if (curFileCnt > remainFileCnt) {
        alert("첨부파일은 최대 " + maxFileCnt + "개 까지 첨부 가능합니다.");
    }

    //math.min : 매개변수로 받은 값 중 가장 작은값 리턴
    for (var i = 0; i < Math.min(curFileCnt, remainFileCnt); i++) {

        const file = obj.files[i];
        console.log(file);

        // 첨부파일 검증
        if (validation(file)) {
            // 파일 배열에 담기
            var reader = new FileReader();
            reader.onload = function (e) {
                filesArr.push(file);
               console.log(e.target.result);
                var img_html = "<img src\"" +   e.target.result + "\" />";
                $(".imgs_wrap").append(img_html);
            };
            reader.readAsDataURL(file)

            // 목록 추가
            /!*let htmlData = '';
            htmlData += '<div id="file' + fileNo + '" class="filebox">';
            htmlData += '   <p class="name">' + file.name + '</p>';
            htmlData += '   <a class="delete" onclick="deleteFile(' + fileNo + ');"><i class="far fa-minus-square"></i></a>';
            htmlData += '</div>';
            $('.file-list').append(htmlData);*!/
            fileNo++;
        } else {
            continue;
        }
    }
    // 초기화
    document.querySelector("input[type=file]").value = "";
}

/!* 첨부파일 검증 *!/
function validation(obj){
    const fileTypes = ['application/pdf', 'image/gif', 'image/jpeg', 'image/png', 'image/bmp', 'image/tif', 'application/haansofthwp', 'application/x-hwp'];
    if (obj.name.length > 100) {
        alert("파일명이 100자 이상인 파일은 제외되었습니다.");
        return false;
    } else if (obj.size > (100 * 1024 * 1024)) {
        alert("최대 파일 용량인 100MB를 초과한 파일은 제외되었습니다.");
        return false;
    } else if (obj.name.lastIndexOf('.') == -1) {
        alert("확장자가 없는 파일은 제외되었습니다.");
        return false;
    } else if (!fileTypes.includes(obj.type)) {
        alert("첨부가 불가능한 파일은 제외되었습니다.");
        return false;
    } else {
        return true;
    }
}

/!* 첨부파일 삭제 *!/
function deleteFile(num) {
    document.querySelector("#file" + num).remove();
    filesArr[num].is_delete = true;
}
*/


/*나가기 모달창*/
$("#header_right_button2").on("click", function () {
    $("#c-overlay").show();
})

$(".button-1").on("click",function () {
    location.href = '/mento/list';
})

$(".button-2").on("click",function () {
    $("#c-overlay").hide();
})

