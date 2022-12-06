/*-----------------------------------------------------------*/
                        /*채팅방 선택 시*/
/*-----------------------------------------------------------*/
$(".left_lists").on("click", function (){
    const roomId = $(this).find("span").text(); // 각 방의 방 번호를 저장

    $.ajax({
        url: "/getChat/test",
        type : "post",
        data : {
            roomId : roomId
        },
        success: function(chats) {
            let text = "";

            /*채팅이 없는 방은 모두 없앰으로 처리*/
            if(chats.length == 0) {
                $("#chat-foreach").html(text);
            }

            /*채팅 내역 출력*/
            chats.forEach(function (chat) {
                let chatDate = new Date(chat.chatDate);
                let chatMonth = chatDate.getMonth()+1;
                let chatDay = chatDate.getDate();
                let readCheck = chat.chatStatus == "UNREAD" ? "1&nbsp&nbsp" : ""; // 메세지가 안 읽음인 경우

                /*내가 보낸 메세지*/
                text += '<div class="chatting_inner" th:object="${chat}">';
                    text += '<div class="chatting_inner_right">';
                        text += '<p class="chatting_date_right"><span class="no_read_chat">' + readCheck + '</span>' + chatMonth + "월 " + chatDay + "일" + '</p>'
                        text += '<ul class="chatting_ul">';
                            text += '<li class="chatting_text">' + chat.chatMessage + '</li>';
                        text += `</ul>`;
                    text += '</div>';
                text += '</div>';

                /*상대방이 보낸 메세지*/
                // text += '<div class="chatting_inner" th:object="${chat}">';
                //     text += '<div class="chatting_inner_left">';
                //         text += '<p class="chatting_date_left">' + chatMonth + "월 " + chatDay + "일" + '<span class="no_read_chat">&nbsp&nbsp1</span></p>'
                //         text += '<ul class="chatting_ul">';
                //             text += '<li class="chatting_text">' + chat.chatMessage + '</li>';
                //         text += `</ul>`;
                //     text += '</div>';
                // text += '</div>';


                $("#chat-foreach").html(text);
            });
        },
        error: function (request, status, error) {
            console.log(request);
            console.log("error : " + error);
            console.log("status : " + status);
            alert("error");
        }
    })


});