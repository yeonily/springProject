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