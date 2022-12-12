/*
* admin/policy - write, update
* */

var msg = "글을 저장하지 않고 나가시겠습니까?";

// 정책
$(".p-submit").on("click", function (){
    if(!(policyForm.policyKeyword.value)){
        policyForm.policyKeyword.focus();
        return;
    }
    if(!(policyForm.policyTitle.value)){
        policyForm.policyTitle.focus();
        return;
    }
    if(!(policyForm.policyContent.value)){
        policyForm.policyContent.focus();
        return;
    }

    policyForm.submit();
});

function pBackList() {
    var flag = confirm(msg);

    if (flag) {
        location.href="/admin/policy";
    }
}

// 농업정보
$(".c-submit").on("click", function (){
    if(!(cropForm.cropKeyword.value)){
        cropForm.cropKeyword.focus();
        return;
    }
    if(!(cropForm.cropTitle.value)){
        cropForm.cropTitle.focus();
        return;
    }
    if(!(cropForm.cropContent.value)){
        cropForm.cropContent.focus();
        return;
    }

    cropForm.submit();
});

function cBackList() {
    var flag = confirm(msg);

    if (flag) {
        location.href="/admin/crop";
    }
}

// 문의 글
$(".c-submit").on("click", function (){
    if(!(askForm.inquireAnswer.value)){
        askForm.inquireAnswer.focus();
        return;
    }

    askForm.submit();
});


function aBackList() {
    if(askForm.inquireAnswer.value){
        var flag = confirm(msg);
        if (flag) {
            location.href="/admin/help";
        }
    } else {
        location.href="/admin/help";
    }
}

// 공지 목록 돌아가기
function nBackList() {
    var flag = confirm(msg);
    if (flag) {
        location.href="/admin/notice";
    }
}

$(".n-submit").on("click", function(){
    let text = "";
    if(!(noticeForm.noticeTitle.value)){
        noticeForm.noticeTitle.focus();
        return;
    }
    if(!(noticeForm.noticeContent.value)){
        noticeForm.noticeContent.focus();
        return;
    }

    $.each($("#preview-image ul li"), function(i, li){
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
        text += `<input type="hidden" name="noticeFiles[` + i + `].fileName" value="` + fileName + `">`;
        text += `<input type="hidden" name="noticeFiles[` + i + `].fileUploadPath" value="` + fileUploadPath + `">`;
        text += `<input type="hidden" name="noticeFiles[` + i + `].fileUuid" value="` + fileUuid + `">`;
        text += `<input type="hidden" name="noticeFiles[` + i + `].fileSize" value="` + fileSize + `">`;
        text += `<input type="hidden" name="noticeFiles[` + i + `].fileImageCheck" value="` + fileImageCheck + `">`;
    });
    $("form#noticeWriteForm").append(text).submit();
});

// 배너
$(".b-submit").on("click", function (){
    if(!(bannerForm.bannerTitle.value)){
        bannerForm.bannerTitle.focus();
        return;
    }
    if(!(bannerForm.bannerInfo.value)){
        bannerForm.bannerInfo.focus();
        return;
    }
    if(!(bannerForm.image.value)){
        alert("배너를 등록해주세요.")
        return;
    }

    bannerForm.submit();
});

function bBackList() {
    var flag = confirm(msg);

    if (flag) {
        location.href="/admin/banner";
    }
}
