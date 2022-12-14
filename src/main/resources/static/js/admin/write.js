/*
* admin/ - write, update
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


// 공지 등록
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


// 문의사항
// 답변 등록
$(".a-submit").on("click", function (){
    if(!(askForm.inquireAnswer.value)){
        alert("답변을 등록해 주세요.");
        return;
    }
    askForm.submit();
});
// 답변 수정
$(".au-submit").on("click", function (){
    if(!(askUpdateForm.inquireAnswer.value)){
        alert("답변을 등록해 주세요.");
        return;
    }
    askUpdateForm.submit();
});