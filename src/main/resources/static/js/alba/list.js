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
                    //알바 곧 마감해 REST
/*-----------------------------------------------------------*/

$(document).ready(function () {

    $.ajax({
        url: "/kind/main",
        type: "post",
        success: function (lists) {
            console.log("들어옴?");
            let text = "";

            lists.forEach(function (list) {
                var number = list.albaId;
                text += '<div class="alba_div" onclick="location.href='
                text += '/alba/detail?albaId='
                text += number
                text += '">'
                text += '<img src=@{albaImage}>'
                text += '<div>' + list.albaApplyCount + '/' + list.albaApplyTotalCount + '명' + '・' + '<span>' + list.albaPrice + '원' +'</span></div>'
                text += '<p class="alba_title">' + list.albaTitle + '</p>'
                text += '</div>'
                text += '</div>'
                text += '</div>'
                $("#albaList").html(text);
            });
            }
        });
    });

/*-----------------------------------------------------------*/
                        //알바 게시글 개수 REST
/*-----------------------------------------------------------*/

$(document).ready(function () {

    $.ajax({
        url: "/kind/count",
        type: "post",
        success: function (counts) {
            let text = '총' + '<span>' + counts + '</span>' + '건'
            $("#countText").html(text);
        }
    });
})

/*-----------------------------------------------------------*/
                        //알바 리스트 REST
/*-----------------------------------------------------------*/

// $(document).ready(function () {
//
//     $.ajax({
//         url: "/kind/newList",
//         type: "post",
//         // data: formData,
//         // contentType: false,
//         // processData: false,
//         success: function (recents) {
//             console.log("들어옴?");
//             let text = "";
//
//             recents.forEach(function (recent) {
//                 text += '<div class="list_td" th:object="${recent}">'
//                 text += '<div class="location"><p>' + recent.albaAddress + '</p></div>'
//                 text += '<div class="title"><a th:href="@{/alba/detail}">' + recent.albaTitle + '</a></div>'
//
//                 let albaWorkDate = new Date(recent.albaWorkDate);
//                 let albaWorkYear = albaWorkDate.getFullYear();
//                 let albaWorkMonth = albaWorkDate.getMonth() + 1;
//                 let albaWorkDay = albaWorkDate.getDate();
//
//                 text += '<div class="workDay">' + albaWorkYear + '년' + albaWorkMonth + '월' + albaWorkDay + '일' + '</div>'
//                 text += '<div class="pay">'
//                 text += '<p class="miniText">시급</p>'
//                 text += '<div>' + recent.albaPrice + '</div>'
//                 text += '</div>'
//
//                 let albaStartDate = new Date(recent.albaApplyStartDate);
//                 let albaStartYear = albaStartDate.getFullYear();
//                 let albaStartMonth = albaStartDate.getMonth() + 1;
//                 let albaStartDay = albaStartDate.getDate();
//
//                 text += '<div class="registerDay">' + albaStartYear + '년' + albaStartMonth + '월' + albaStartDay + '일' + '</div>'
//                 text += '</div>'
//                 $("#list-foreach").html(text);
//             });
//         }
//     });
// });

            /*-----------------------------------------------------------*/
            //클릭 시 체크 확인 플레그
            /*-----------------------------------------------------------*/

            let check = false;

            /*-----------------------------------------------------------*/
            //최신순 정렬 클릭 시
            /*-----------------------------------------------------------*/

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
                    success: function (recents) {
                        let text = "";
                        recents.forEach(function (recent) {
                            text += '<div class="list_td" th:object="${recent}">'
                            text += '<div class="location"><p>' + recent.albaAddress + '</p></div>'
                            text += '<div class="title">'
                            var number = list.albaId;
                            text += '<a href= "/alba/detail?albaId='
                            text +=  number
                            text += '">'
                            text += recent.albaTitle
                            text += '</a></div>'


                            let albaWorkDate = new Date(recent.albaWorkDate);
                            let albaWorkYear = albaWorkDate.getFullYear();
                            let albaWorkMonth = albaWorkDate.getMonth() + 1;
                            let albaWorkDay = albaWorkDate.getDate();

                            text += '<div class="workDay">' + albaWorkYear + '년' + albaWorkMonth + '월' + albaWorkDay + '일' + '</div>'
                            text += '<div class="pay">'
                            text += '<p class="miniText">시급</p>'
                            text += '<div>' + recent.albaPrice + '</div>'
                            text += '</div>'

                            let albaStartDate = new Date(recent.albaApplyStartDate);
                            let albaStartYear = albaStartDate.getFullYear();
                            let albaStartMonth = albaStartDate.getMonth() + 1;
                            let albaStartDay = albaStartDate.getDate();

                            text += '<div class="registerDay">' + albaStartYear + '년' + albaStartMonth + '월' + albaStartDay + '일' + '</div>'
                            text += '</div>'
                            $("#list-foreach").html(text);
                        })
                    }
            });
check = true;
})

                                        // let text = "";
                                        //
                                        // text += '<div id = "paging" style = "margin-bottom: 100px;">'
                                        // text += '<ul th:with= "start=${(albas.number/maxPage)*maxPage + 1}, end=(${(albas.totalPages == 0) ? 1 : (start + (maxPage - 1) < albas.totalPages ? start + (maxPage - 1) : albas.totalPages)})">'
                                        // text += '<li className = "sidePageNum prev" th:if= "${albas.pageable.pageNumber > 0}">'
                                        // text += '<a th:href = "@{/alba/list(page=${((albas.pageable.pageNumber/5)*5) - 1})}" className = "page">'
                                        // text += '<svg width = "16" height = "16" className = "sidePageDisabled" viewBox = "0 0 16 16" fill = "black" xmlns = "http://www.w3.org/2000/svg" style = "transform:rotate(270deg);" data - v - bd9f2bcc = "" data - v - 30 c80689 = "">'
                                        // text += '<path fill - rule = "evenodd" clip - rule = "evenodd" d = "M7.64645 4.64645C7.84171 4.45118 8.15829 4.45118 8.35355 4.64645L12.8536 9.14645C13.0488 9.34171 13.0488 9.65829 12.8536 9.85355C12.6583 10.0488 12.3417 10.0488 12.1464 9.85355L8 5.70711L3.85355 9.85355C3.65829 10.0488 3.34171 10.0488 3.14645 9.85355C2.95118 9.65829 2.95118 9.34171 3.14645 9.14645L7.64645 4.64645Z" data - v - bd9f2bcc = "" >'
                                        // text += '</path>'
                                        // text += '</svg>'
                                        // text += '</a>'
                                        // text += '</li>'
                                        // text += '<th:block  th:each="i: ${#numbers.sequence(start, end)}">'
                                        // text += '<li>'
                                        // text += '<a className="page" th:href="@{/alba/list(page=${i - 1})}" th:classappend="${i == albas.pageable.pageNumber + 1} ?' + 'selectPage' + ':' + '' + '"th:text="${i}">1</a>'
                                        // text += '</li>'
                                        // text += '</th:block >'
                                        // text += '<li className="sidePageNum next" th:if="${end < albas.totalPages}">'
                                        // text += '<a th:href="@{/alba/list(page=${end})}" className="page">'
                                        // text += '<svg width="16" height="16" viewBox="0 0 16 16" fill="black" xmlns="http://www.w3.org/2000/svg" style="transform:rotate(90deg);" data-v-bd9f2bcc="" data-v-30c80689=""><path fill-rule="evenodd" clip-rule="evenodd" d="M7.64645 4.64645C7.84171 4.45118 8.15829 4.45118 8.35355 4.64645L12.8536 9.14645C13.0488 9.34171 13.0488 9.65829 12.8536 9.85355C12.6583 10.0488 12.3417 10.0488 12.1464 9.85355L8 5.70711L3.85355 9.85355C3.65829 10.0488 3.34171 10.0488 3.14645 9.85355C2.95118 9.65829 2.95118 9.34171 3.14645 9.14645L7.64645 4.64645Z" data-v-bd9f2bcc=""></path></svg></a>'
                                        // text += '</li>'
                                        // text += '</ul>'
                                        // text += '</div>'
                                        // $("#paging").html(text);


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
                    success: function (lists) {
                        let text = "";

                        lists.forEach(function (list) {
                            text += '<div class="list_td" th:object="${list}">'
                            text += '<div class="location"><p>' + list.albaAddress + '</p></div>'
                            text += '<div class="title">'
                            var number = list.albaId;
                            text += '<a href= "/alba/detail?albaId='
                            text +=  number
                            text += '">'
                            text += list.albaTitle
                            text += '</a></div>'

                            let albaWorkDate = new Date(list.albaWorkDate);
                            let albaWorkYear = albaWorkDate.getFullYear();
                            let albaWorkMonth = albaWorkDate.getMonth() + 1;
                            let albaWorkDay = albaWorkDate.getDate();

                            text += '<div class="workDay">' + albaWorkYear + '년' + albaWorkMonth + '월' + albaWorkDay + '일' + '</div>'
                            text += '<div class="pay">'
                            text += '<p class="miniText">시급</p>'
                            text += '<div>' + list.albaPrice + '</div>'
                            text += '</div>'

                            let albaStartDate = new Date(list.albaApplyStartDate);
                            let albaStartYear = albaStartDate.getFullYear();
                            let albaStartMonth = albaStartDate.getMonth() + 1;
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
            /*-----------------------------------------------------------*/

            $("#deadline-btn").on("click", function () {
                // if(check == true){
                //     check = false;
                //     location.href = "/alba/list";
                //     return;
                // }
                console.log("들어옴2");

                $.ajax({
                    url: "/kind/collectList",
                    type: "post",
                    success: function (lists) {
                        console.log(lists);
                        let text = "";

                        lists.forEach(function (list) {
                            text += '<div class="list_td" th:object="${list}">'
                            text += '<div class="location"><p>' + list.albaAddress + '</p></div>'
                            text += '<div class="title">'
                            var number = list.albaId;
                            text += '<a href= "/alba/detail?albaId='
                            text +=  number
                            text += '">'
                            text += list.albaTitle
                            text += '</a></div>'

                            let albaWorkDate = new Date(list.albaWorkDate);
                            let albaWorkYear = albaWorkDate.getFullYear();
                            let albaWorkMonth = albaWorkDate.getMonth() + 1;
                            let albaWorkDay = albaWorkDate.getDate();

                            text += '<div class="workDay">' + albaWorkYear + '년' + albaWorkMonth + '월' + albaWorkDay + '일' + '</div>'
                            text += '<div class="pay">'
                            text += '<p class="miniText">시급</p>'
                            text += '<div>' + list.albaPrice + '</div>'
                            text += '</div>'

                            let albaStartDate = new Date(list.albaApplyStartDate);
                            let albaStartYear = albaStartDate.getFullYear();
                            let albaStartMonth = albaStartDate.getMonth() + 1;
                            let albaStartDay = albaStartDate.getDate();

                            text += '<div class="registerDay">' + albaStartYear + '년' + albaStartMonth + '월' + albaStartDay + '일' + '</div>'
                            text += '</div>'
                            $("#list-foreach").html(text);
                        })
                    }
                });
                check = true;
            })






