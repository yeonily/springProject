var $status = $(".list_td .title span"); // 모집 상태를 저장


/*-----------------------------------------------------------*/
                    //필터 선택 시 검정글자로 표시
/*-----------------------------------------------------------*/
$(".top-div-filter span").on("click", function() {
    $(this).css("color", "#000000");
    $(".top-div-filter span").not(this).css({color: '#959595'});
});


/*-----------------------------------------------------------*/
                    //모집마감일 경우 글씨 어둡게
/*-----------------------------------------------------------*/
for(var i = 0; i < $status.length; i++) {
    if($status.eq(i).text() == "모집마감") {
        $status.eq(i).parent().parent().parent().css("color", "#dbdbdb");
        $status.eq(i).parent().css("color", "#dbdbdb");
    }
}

/*-----------------------------------------------------------*/
                    //클릭 시 체크 확인 플레그
/-----------------------------------------------------------*/

let check = false;

/*-----------------------------------------------------------*/
                    //최신순 정렬 클릭 시
/-----------------------------------------------------------*/

$("#recent-btn").on("click", function () {
    // if(check == true){
    //     check = false;
    //     location.href = "/alba/list";
    //     return;
    // }
    console.log("들어옴3");

    $.ajax({
        url: "/kind/newList",
        type: "post",
        // data: formData,
        // contentType: false,
        // processData: false,
        success: function (lists) {
            let text = "";

            lists.forEach(function (list) {
                text += '<div class="list_td" th:object="${list}">'
                text += '<div class="location"><p>' + list.albaAddress + '</p></div>'
                text += '<div class="title"><a th:href="@{/alba/detail}">' + list.albaTitle + '(<span>모집중</span>)</a></div>'

                let albaWorkDate = new Date(list.albaWorkDate);
                let albaWorkYear = albaWorkDate.getFullYear();
                let albaWorkMonth = albaWorkDate.getMonth()+1;
                let albaWorkDay = albaWorkDate.getDate();

                text += '<div class="workDay">' + albaWorkYear + '년' + albaWorkMonth + '월' + albaWorkDay + '일' + '</div>'
                text += '<div class="pay">'
                text += '<p class="miniText">시급</p>'
                text += '<div>' + list.albaPrice + '</div>'
                text += '</div>'

                let albaStartDate = new Date(list.albaApplyStartDate);
                let albaStartYear = albaStartDate.getFullYear();
                let albaStartMonth = albaStartDate.getMonth()+1;
                let albaStartDay = albaStartDate.getDate();

                text += '<div class="registerDay">' + albaStartYear + '년' + albaStartMonth + '월' + albaStartDay + '일' + '</div>'
                text += '</div>'
                $("#list-foreach").html(text);
            })
        }
    });
    check = true;
})

/*-----------------------------------------------------------*/
                    //시급순 정렬 클릭 시
/*-----------------------------------------------------------*/


$("#pay-btn").on("click", function () {
    // if(check == true){
    //     check = false;
    //     location.href = "/alba/list";
    //     return;
    // }
    console.log("들어옴2");

    $.ajax({
        url: "/kind/payList",
        type: "post",
        // data: formData,
        // contentType: false,
        // processData: false,
        success: function (lists) {
            let text = "";

            lists.forEach(function (list) {
                text += '<div class="list_td" th:object="${list}">'
                text += '<div class="location"><p>' + list.albaAddress + '</p></div>'
                text += '<div class="title"><a th:href="@{/alba/detail}">' + list.albaTitle + '(<span>모집중</span>)</a></div>'

                let albaWorkDate = new Date(list.albaWorkDate);
                let albaWorkYear = albaWorkDate.getFullYear();
                let albaWorkMonth = albaWorkDate.getMonth()+1;
                let albaWorkDay = albaWorkDate.getDate();

                text += '<div class="workDay">' + albaWorkYear + '년' + albaWorkMonth + '월' + albaWorkDay + '일' + '</div>'
                text += '<div class="pay">'
                text += '<p class="miniText">시급</p>'
                text += '<div>' + list.albaPrice + '</div>'
                text += '</div>'

                let albaStartDate = new Date(list.albaApplyStartDate);
                let albaStartYear = albaStartDate.getFullYear();
                let albaStartMonth = albaStartDate.getMonth()+1;
                let albaStartDay = albaStartDate.getDate();

                text += '<div class="registerDay">' + albaStartYear + '년' + albaStartMonth + '월' + albaStartDay + '일' + '</div>'
                text += '</div>'
                $("#list-foreach").html(text);
                })
            }
    });
    check = true;
})

/*-----------------------------------------------------------*/
                    //모집중 정렬 클릭 시


$("#progress-btn").on("click", function () {
    // if(check == true){
    //     check = false;
    //     location.href = "/alba/list";
    //     return;
    // }
    console.log("들어옴2");

    $.ajax({
        url: "/kind/collectList",
        type: "post",
        // data: formData,
        // contentType: false,
        // processData: false,
        success: function (lists) {
            let text = "";

            lists.forEach(function (list) {
                text += '<div class="list_td" th:object="${list}">'
                text += '<div class="location"><p>' + list.albaAddress + '</p></div>'
                text += '<div class="title"><a th:href="@{/alba/detail}">' + list.albaTitle + '(<span>모집중</span>)</a></div>'

                let albaWorkDate = new Date(list.albaWorkDate);
                let albaWorkYear = albaWorkDate.getFullYear();
                let albaWorkMonth = albaWorkDate.getMonth()+1;
                let albaWorkDay = albaWorkDate.getDate();

                text += '<div class="workDay">' + albaWorkYear + '년' + albaWorkMonth + '월' + albaWorkDay + '일' + '</div>'
                text += '<div class="pay">'
                text += '<p class="miniText">시급</p>'
                text += '<div>' + list.albaPrice + '</div>'
                text += '</div>'

                let albaStartDate = new Date(list.albaApplyStartDate);
                let albaStartYear = albaStartDate.getFullYear();
                let albaStartMonth = albaStartDate.getMonth()+1;
                let albaStartDay = albaStartDate.getDate();

                text += '<div class="registerDay">' + albaStartYear + '년' + albaStartMonth + '월' + albaStartDay + '일' + '</div>'
                text += '</div>'
                $("#list-foreach").html(text);
            })
        }
    });
    check = true;
})






