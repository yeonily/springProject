//  확인 = true, 취소 = false값 flag에 담기

// 글 삭제
function deleteWriteCheck() {
    var msg = "게시글을 삭제 하시겠습니까?";
    var flag = confirm(msg);

    if (flag) alert('삭제 완료');
}

// 배너글 수정
function reviseBannerCheck() {
    var msg = "글을 수정 하시겠습니까?";
    var flag = confirm(msg);

    if (flag) {
        location.href="/admin/banner";
        alert('수정 완료');
    }
}

// 농업정보글 수정
function reviseCropInformationCheck() {
    var msg = "글을 수정 하시겠습니까?";
    var flag = confirm(msg);

    if (flag) {
        location.href="/admin/cropInformation";
        alert('수정 완료');
    }
}

// 청년정책글 수정
function revisePolicyCheck() {
    var msg = "글을 수정 하시겠습니까?";
    var flag = confirm(msg);

    if (flag) {
        location.href="/admin/policy";
        alert('수정 완료');
    }
}

// 배너글 등록
function saveBannerCheck() {
    var msg = "글을 등록하시겠습니까?";
    var flag = confirm(msg);

    if (flag) {
        location.href="/admin/banner";
        alert('등록 완료');
    }
}

// 농업정보글 등록
function saveCropInformationCheck() {
    var msg = "글을 등록하시겠습니까?";
    var flag = confirm(msg);

    if (flag) {
        location.href="/admin/cropInformation";
        alert('등록 완료');
    }
}

// 공지글 등록
function saveNoticeCheck() {
    var msg = "글을 등록하시겠습니까?";
    var flag = confirm(msg);

    if (flag) {
        location.href="/admin/notice";
        alert('등록 완료');
    }
}

// 답변 등록
function doReplyCheck() {
    var msg = "답변을 등록하시겠습니까?";
    var flag = confirm(msg);

    if (flag){
        location.href="/admin/ask";
        alert('답변 완료');
    }
}