/*
* program/list.html
* program/applyfin.html
* */

//헤더 대카 표시
$(document).ready(function () {
    $("ul.nav_ul li:nth-child(2)").children("a").addClass("selected-header");
})

//카테고리 선택 표시
$(".filter-button").on("click", function(){
    if($(this).hasClass('selected-button')){
        $(this).attr('class', 'filter-button');
    }else{
        $(this).attr('class', 'selected-button');
        $(this).siblings().attr('class', 'filter-button');
    }

})

/*클릭 시 체크 확인 플래그*/
let check = false;



//진행중 정렬 클릭 시
$("#progress-btn").on("click", function () {
    if(check == true){
        check = false;
        location.href = "/program/list";
        return;
    }
    $("#current-status").text(this.innerHTML);
    console.log("들어옴2");
    $.ajax({
        url: "/sort/continue",
        type: "post",
        // data: formData,
        // contentType: false,
        // processData: false,
        success: function (lists) {
            let text = "";

            lists.forEach(function (list) {
                if(list.programPrice == 0){
                    list.programPrice = "무료"
                }

                text += '<div class="list-container" th:object="${list}">'
                var number = list.programId;
                text += '<a href="/program/detail?programId='
                text += number
                text += '">'
                text += '<article class="list-article">'
                text += '<div class="list-image-container">'
                text += '<div class="list-point">'
                text += '<div>'
                if(list.programType == 'ONLY_MENTEE'){
                    text += '<span class="list-point-m">멘토 전용</span>'
                }
                text += '</div>'
                text += '</div>'
                text += '<img class="pg-image" src="/image/program/pgimage.gif">'
                text += '</div>'
                text += '<div class="list-info">'
                text += '<div class="pg-subtitle">'
                text += '<div class="pg-location">' + list.programLocation +'</div>'
                text += '<div class="pg-dot"> · </div>'
                text += '<div class="pg-price">' + list.programPrice + '</div>'
                text += '</div>'
                text += '<div class="pg-title">'
                text += '<div class="pg-title-name">'+ list.programTitle +'</div>'
                text += '</div>'
                text += '<div class="pg-caption">'

                let date = new Date(list.programWorkDate);
                let month = date.getMonth()+1;
                let day = date.getDate();

                text += '<span class="pg-date">'+ month + '월' + day  + '일 시작'   +'</span>'
                text += '</div>'
                text += '</div>'
                text += '</article>'
                text += '</a>'
                text += '</div>'
                $("#list-foreach").html(text);
            })
        }
    });
    check = true;

})

// 최근 등록일 순 클릭 시
$("#recent-register-btn").on("click", function () {
    if(check == true){
        check = false;
        location.href = "/program/list";
        return;
    }
    $("#current-status").text(this.innerHTML);
    console.log("들어옴2");
    $.ajax({
        url: "/sort/recentApply",
        type: "post",
        success: function (lists) {
            let text = "";

            lists.forEach(function (list) {
                if(list.programPrice == 0){
                    list.programPrice = "무료"
                }

                text += '<div class="list-container" th:object="${list}">'
                let number = list.programId;
                text += '<a href="/program/detail?programId='
                text += number
                text += '">'
                text += '<article class="list-article">'
                text += '<div class="list-image-container">'
                text += '<div class="list-point">'
                text += '<div>'
                if(list.programType == 'ONLY_MENTEE'){
                    text += '<span class="list-point-m">멘토 전용</span>'
                }
                text += '</div>'
                text += '</div>'
                text += '<img class="pg-image" src="/image/program/pgimage.gif">'
                text += '</div>'
                text += '<div class="list-info">'
                text += '<div class="pg-subtitle">'
                text += '<div class="pg-location">' + list.programLocation +'</div>'
                text += '<div class="pg-dot"> · </div>'
                text += '<div class="pg-price">' + list.programPrice + '</div>'
                text += '</div>'
                text += '<div class="pg-title">'
                text += '<div class="pg-title-name">'+ list.programTitle +'</div>'
                text += '</div>'
                text += '<div class="pg-caption">'
                let date = new Date(list.programWorkDate);
                let month = date.getMonth()+1;
                let day = date.getDate();
                text += '<span class="pg-date">'+ month + '월' + day  + '일 시작'   +'</span>'
                text += '</div>'
                text += '</div>'
                text += '</article>'
                text += '</a>'
                text += '</div>'
                $("#list-foreach").html(text);
            })
        }
    });
    check = true;

})
// 최근 마감일 순 클릭 시
$("#recent-end-btn").on("click", function () {
    if(check == true){
        check = false;
        location.href = "/program/list";
        return;
    }
    $("#current-status").text(this.innerHTML);
    console.log("들어옴2");
    $.ajax({
        url: "/sort/recentEnd",
        type: "post",
        success: function (lists) {
            console.log("들어옴3");
            console.log(lists);
            let text = "";

            lists.forEach(function (list) {
                if(list.programPrice == 0){
                    list.programPrice = "무료"
                }

                text += '<div class="list-container" th:object="${list}">'
                var number = list.programId;
                text += '<a href="/program/detail?programId='
                text += number
                text += '">'
                text += '<article class="list-article">'
                text += '<div class="list-image-container">'
                text += '<div class="list-point">'
                text += '<div>'
                if(list.programType == 'ONLY_MENTEE'){
                    text += '<span class="list-point-m">멘토 전용</span>'
                }
                text += '</div>'
                text += '</div>'
                text += '<img class="pg-image" src="/image/program/pgimage.gif">'
                text += '</div>'
                text += '<div class="list-info">'
                text += '<div class="pg-subtitle">'
                text += '<div class="pg-location">' + list.programLocation +'</div>'
                text += '<div class="pg-dot"> · </div>'
                text += '<div class="pg-price">' + list.programPrice + '</div>'
                text += '</div>'
                text += '<div class="pg-title">'
                text += '<div class="pg-title-name">'+ list.programTitle +'</div>'
                text += '</div>'
                text += '<div class="pg-caption">'
                let date = new Date(list.programWorkDate);
                let month = date.getMonth()+1;
                let day = date.getDate();
                text += '<span class="pg-date">'+ month + '월' + day  + '일 시작'   +'</span>'
                text += '</div>'
                text += '</div>'
                text += '</article>'
                text += '</a>'
                text += '</div>'
                $("#list-foreach").html(text);
            })
        }
    });
    check = true;

})
// 멘티 전용 클릭 시
$("#mentee-btn").on("click", function () {
    if(check == true){
        check = false;
        location.href = "/program/list";
        return;
    }
    console.log("들어옴2");
    $("#current-status").text(this.innerHTML);
    $.ajax({
        url: "/sort/onlyMentee",
        type: "post",
        success: function (lists) {
            console.log("들어옴3");
            console.log(lists);
            let text = "";

            lists.forEach(function (list) {
                if(list.programPrice == 0){
                    list.programPrice = "무료"
                }

                text += '<div class="list-container" th:object="${list}">'
                var number = list.programId;
                text += '<a href="/program/detail?programId='
                text += number
                text += '">'
                text += '<article class="list-article">'
                text += '<div class="list-image-container">'
                text += '<div class="list-point">'
                text += '<div>'
                if(list.programType == 'ONLY_MENTEE'){
                    text += '<span class="list-point-m">멘토 전용</span>'
                }
                text += '</div>'
                text += '</div>'
                text += '<img class="pg-image" src="/image/program/pgimage.gif">'
                text += '</div>'
                text += '<div class="list-info">'
                text += '<div class="pg-subtitle">'
                text += '<div class="pg-location">' + list.programLocation +'</div>'
                text += '<div class="pg-dot"> · </div>'
                text += '<div class="pg-price">' + list.programPrice + '</div>'
                text += '</div>'
                text += '<div class="pg-title">'
                text += '<div class="pg-title-name">'+ list.programTitle +'</div>'
                text += '</div>'
                text += '<div class="pg-caption">'
                let date = new Date(list.programWorkDate);
                let month = date.getMonth()+1;
                let day = date.getDate();
                text += '<span class="pg-date">'+ month + '월' + day  + '일 시작'   +'</span>'
                text += '</div>'
                text += '</div>'
                text += '</article>'
                text += '</a>'
                text += '</div>'
                $("#list-foreach").html(text);
            })
        }
    });
    check = true;

})

// 일반인용 클릭 시
$("#user-btn").on("click", function () {
    if(check == true){
        check = false;
        location.href = "/program/list";
        return;
    }
    $("#current-status").text(this.innerHTML);
    console.log("들어옴2");
    $.ajax({
        url: "/sort/onlyUser",
        type: "post",
        success: function (lists) {
            console.log("들어옴3");
            console.log(lists);
            let text = "";

            lists.forEach(function (list) {
                if(list.programPrice == 0){
                    list.programPrice = "무료"
                }

                text += '<div class="list-container" th:object="${list}">'
                var number = list.programId;
                text += '<a href="/program/detail?programId='
                text += number
                text += '">'
                text += '<article class="list-article">'
                text += '<div class="list-image-container">'
                text += '<div class="list-point">'
                text += '<div>'
                if(list.programType == 'ONLY_MENTEE'){
                    text += '<span class="list-point-m">멘토 전용</span>'
                }
                text += '</div>'
                text += '</div>'
                text += '<img class="pg-image" src="/image/program/pgimage.gif">'
                text += '</div>'
                text += '<div class="list-info">'
                text += '<div class="pg-subtitle">'
                text += '<div class="pg-location">' + list.programLocation +'</div>'
                text += '<div class="pg-dot"> · </div>'
                text += '<div class="pg-price">' + list.programPrice + '</div>'
                text += '</div>'
                text += '<div class="pg-title">'
                text += '<div class="pg-title-name">'+ list.programTitle +'</div>'
                text += '</div>'
                text += '<div class="pg-caption">'
                let date = new Date(list.programWorkDate);
                let month = date.getMonth()+1;
                let day = date.getDate();
                text += '<span class="pg-date">'+ month + '월' + day  + '일 시작'   +'</span>'
                text += '</div>'
                text += '</div>'
                text += '</article>'
                text += '</a>'
                text += '</div>'
                $("#list-foreach").html(text);
            })
        }
    });
    check = true;

})

// 유료 클릭 시
$("#pay-btn").on("click", function () {
    if(check == true){
        check = false;
        location.href = "/program/list";
        return;
    }
    $("#current-status").text(this.innerHTML);
    console.log("들어옴2");
    $.ajax({
        url: "/sort/usePrice",
        type: "post",
        success: function (lists) {
            console.log("들어옴3");
            console.log(lists);
            let text = "";

            lists.forEach(function (list) {
                if(list.programPrice == 0){
                    list.programPrice = "무료"
                }

                text += '<div class="list-container" th:object="${list}">'
                var number = list.programId;
                text += '<a href="/program/detail?programId='
                text += number
                text += '">'
                text += '<article class="list-article">'
                text += '<div class="list-image-container">'
                text += '<div class="list-point">'
                text += '<div>'
                if(list.programType == 'ONLY_MENTEE'){
                    text += '<span class="list-point-m">멘토 전용</span>'
                }
                text += '</div>'
                text += '</div>'
                text += '<img class="pg-image" src="/image/program/pgimage.gif">'
                text += '</div>'
                text += '<div class="list-info">'
                text += '<div class="pg-subtitle">'
                text += '<div class="pg-location">' + list.programLocation +'</div>'
                text += '<div class="pg-dot"> · </div>'
                text += '<div class="pg-price">' + list.programPrice + '</div>'
                text += '</div>'
                text += '<div class="pg-title">'
                text += '<div class="pg-title-name">'+ list.programTitle +'</div>'
                text += '</div>'
                text += '<div class="pg-caption">'
                let date = new Date(list.programWorkDate);
                let month = date.getMonth()+1;
                let day = date.getDate();
                text += '<span class="pg-date">'+ month + '월' + day  + '일 시작'   +'</span>'
                text += '</div>'
                text += '</div>'
                text += '</article>'
                text += '</a>'
                text += '</div>'
                $("#list-foreach").html(text);
            })
        }
    });
    check = true;

})

// 무료 클릭 시
$("#free-btn").on("click", function () {
    if(check == true){
        check = false;
        location.href = "/program/list";
        return;
    }
    $("#current-status").text(this.innerHTML);
    console.log("들어옴2");
    $.ajax({
        url: "/sort/freePrice",
        type: "post",
        success: function (lists) {
            console.log("들어옴3");
            console.log(lists);
            let text = "";

            lists.forEach(function (list) {
                if(list.programPrice == 0){
                    list.programPrice = "무료"
                }

                text += '<div class="list-container" th:object="${list}">'
                var number = list.programId;
                text += '<a href="/program/detail?programId='
                text += number
                text += '">'
                text += '<article class="list-article">'
                text += '<div class="list-image-container">'
                text += '<div class="list-point">'
                text += '<div>'
                if(list.programType == 'ONLY_MENTEE'){
                    text += '<span class="list-point-m">멘토 전용</span>'
                }
                text += '</div>'
                text += '</div>'
                text += '<img class="pg-image" src="/image/program/pgimage.gif">'
                text += '</div>'
                text += '<div class="list-info">'
                text += '<div class="pg-subtitle">'
                text += '<div class="pg-location">' + list.programLocation +'</div>'
                text += '<div class="pg-dot"> · </div>'
                text += '<div class="pg-price">' + list.programPrice + '</div>'
                text += '</div>'
                text += '<div class="pg-title">'
                text += '<div class="pg-title-name">'+ list.programTitle +'</div>'
                text += '</div>'
                text += '<div class="pg-caption">'
                let date = new Date(list.programWorkDate);
                let month = date.getMonth()+1;
                let day = date.getDate();
                text += '<span class="pg-date">'+ month + '월' + day  + '일 시작'   +'</span>'
                text += '</div>'
                text += '</div>'
                text += '</article>'
                text += '</a>'
                text += '</div>'
                $("#list-foreach").html(text);
            })
        }
    });
    check = true;

})