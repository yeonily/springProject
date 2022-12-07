/*-----------------------------------------------------------*/
                        /*채팅방 선택 시*/
/*-----------------------------------------------------------*/
var sessionId = $("#sessionId").text(); // 현재 로그인된 세션 아이디

$(".left_lists").on("click", function (){
    const roomId = $(this).find("span").text(); // 각 방의 방 번호를 저장
    const nickName = $(this).find("#nickName").text(); // 닉네임

    $(".right_text .list_text_p1").text(nickName); // 닉네임을 선택한 채팅방의 닉네임으로 변경함


    $.ajax({
        url: "/getChat/test",
        type : "post",
        dataType: "json",
        data : {
            roomId : roomId
        },
        success: function(chats) {
            let text = "";

            /*채팅이 없는 방은 대화내역 없는걸로 처리*/
            if(chats.length == 0) {
                $("#chat-foreach").html(text);
            }

            /*채팅 내역 출력*/
            chats.forEach(function (chat) {
                let chatDate = new Date(chat.chatDate);
                let chatMonth = chatDate.getMonth()+1;
                let chatDay = chatDate.getDate();
                let readCheck = chat.chatStatus == "UNREAD"; // 메세지가 안 읽음인 경우

                /*내가 보낸 메세지*/
                if(chat.memberId == sessionId) {
                    readCheck = readCheck ? "1&nbsp&nbsp" : "";
                    text += '<div class="chatting_inner" th:object="${chat}">';
                        text += '<div class="chatting_inner_right">';
                            text += '<p class="chatting_date_right"><span class="no_read_chat">' + readCheck + '</span>' + chatMonth + "월 " + chatDay + "일" + '</p>'
                            text += '<ul class="chatting_ul">';
                                text += '<li class="chatting_text">' + chat.chatMessage + '</li>';
                            text += `</ul>`;
                        text += '</div>';
                    text += '</div>';
                }
                // /*상대방이 보낸 메세지*/
                else {
                    readCheck = readCheck ? "&nbsp&nbsp1" : "";
                    text += '<div class="chatting_inner" th:object="${chat}">';
                        text += '<div class="chatting_inner_left">';
                            text += '<p class="chatting_date_left">' + chatMonth + "월 " + chatDay + "일" + '<span class="no_read_chat">' + readCheck + '</span></p>'
                            text += '<ul class="chatting_ul">';
                                text += '<li class="chatting_text">' + chat.chatMessage + '</li>';
                            text += `</ul>`;
                        text += '</div>';
                    text += '</div>';
                }
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