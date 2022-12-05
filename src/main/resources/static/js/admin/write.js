/*
* admin/policy - write, update
* */

// 정책정보
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

function backList() {
    var msg = "글을 저장하지 않고 나가시겠습니까?";
    var flag = confirm(msg);

    if (flag) {
        location.href="/admin/policy";
    }
}