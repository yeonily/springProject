/*-----------------------------------------------------------*/
                        /*채팅방 선택 시*/
/*-----------------------------------------------------------*/
var sessionId = Number($("#sessionId").text()); // 현재 로그인된 세션 아이디
var roomId;
var check = false;
var tempRoomId;
var text = "";
let sockJs;
let stomp;/*실행 순서 첫 번째*/
let isFirst = true;

$(".left_lists").on("click", function () {
    /* if(stomp.connected){
        console.log("---connected---");
        stomp.disconnect();
        isConnected = false;
    }else{
        console.log("---disconnected---");
    } */

    if(isFirst == false) {
        stomp.disconnect();
    }

    sockJs = new SockJS("/stomp/chat");
    stomp = Stomp.over(sockJs);
    isFirst = false


    roomId = $(this).find("#chatId").text(); // 각 방의 방 번호를 저장

    const nickName = $(this).find(".list_name_p1").text(); // 닉네임


    $(".right_text .list_text_p1").text(nickName); // 닉네임을 선택한 채팅방의 닉네임으로 변경함
    $("#chat-foreach").html("");


    $.ajax({
        url: "/getChat/history",
        type: "post",
        data: {"roomId": roomId},
        success: function (chats) {
            let text = "";

            chats.forEach(function (chat) {
                nowDate = new Date();
                console.log(chat);

                let chatStatus = chat.chatStatus == 'READ' ? true : false; // 채팅 읽음 상태 저장
                if (sessionId == chat.memberId) {
                    const status = chatStatus == true ? `` : `1&nbsp&nbsp`;
                    text += `<div class="chatting_inner">`;
                    text += `<div class="chatting_inner_right">`;
                    text += `<p class="chatting_date_right"><span class="no_read_chat">` + status + `</span>` + chat.chatDate + `</p>`;
                    text += `<ul class="chatting_ul">`;
                    text += `<li class="chatting_text">` + chat.chatMessage + `</li>`;
                    text += `</ul>`;
                    text += `</div>`;
                    text += `</div>`;
                } else {
                    const status = chatStatus == true ? `` : `&nbsp&nbsp1`;
                    text += `<div class="chatting_inner">`;
                    text += `<div class="chatting_inner_left">`;
                    text += `<p class="chatting_date_left">` + chat.chatDate + `<span class="no_read_chat">` + status + `</span></p>`;
                    text += `<ul class="chatting_ul">`;
                    text += `<li class="chatting_text">` + chat.chatMessage + `</li>`;
                    text += `</ul>`;
                    text += `</div>`;
                    text += `</div>`;
                }
                $("#chat-foreach").html(text);
            })
        }
    });

    //stomp.disconnect();
    /*실행 순서 두 번째*/
    /*stomp 소켓 정상적으로 연결되었을 때*/
   // console.log(stomp.)
    stomp.connect({}, function () {
        console.log("연결됨!");

        stomp.subscribe("/sub/mento/chatting/" + roomId, function (chat) {
            let content = JSON.parse(chat.body);
            let message = content.chatMessage; // 메세지 내용
            let sendDate = content.chatDate; // 메세지 날짜
            let writer = content.memberId; // 채팅을 작성한 사람
            let chatStatus = content.chatStatus == 'READ' ? true : false;


            const item = new LiModel(message, sendDate, writer, chatStatus);
            item.makeLi();

            function LiModel(message, sendDate, writer, chatStatus) {
                this.message = message;
                this.sendDate = sendDate;
                this.writer = writer;
                this.chatStatus = chatStatus;

                this.makeLi = () => {
                    const month = new Date(this.sendDate).getMonth() + 1;
                    const days = new Date(this.sendDate).getDate();
                    const check = this.chatStatus == true ? true : false; // 채팅을 읽은 상태일 경우 true

                    if (sessionId == this.writer) {
                        this.chatStatus = check == true ? `` : `1&nbsp&nbsp`; // 채팅을 읽지 않은 상태일 경우 1을 표시
                        text += `<div class="chatting_inner">`;
                        text += `<div class="chatting_inner_right">`;
                        text += `<p class="chatting_date_right"><span class="no_read_chat">${this.chatStatus}</span>` + month + "월 " + days + "일" + `</p>`;
                        text += `<ul class="chatting_ul">`;
                        text += `<li class="chatting_text">${this.message}</li>`;
                        text += `</ul>`;
                        text += `</div>`;
                        text += `</div>`;
                    }
                    /*상대방이 보낸 채팅의 경우*/
                    else {
                        this.chatStatus = check == true ? `` : `&nbsp&nbsp1`; // 채팅을 읽지 않은 상태일 경우 1을 표시
                        text += `<div class="chatting_inner">`;
                        text += `<div class="chatting_inner_left">`;
                        text += `<p class="chatting_date_left">` + month + "월 " + days + "일" + `<span class="no_read_chat">${this.chatStatus}</span></p>`;
                        text += `<ul class="chatting_ul">`;
                        text += `<li class="chatting_text">${this.message}</li>`;
                        text += `</ul>`;
                        text += `</div>`;
                        text += `</div>`;
                    }
                    $("#chat-foreach").append(text);
                    text = "";
                }
            }
        })
        stomp.send('/pub/chatting/enter', {}, JSON.stringify({roomId: roomId, memberId: sessionId}));
    });


    /*만약 메세지를 입력하는 input에서 엔터를 눌렀을 때 채팅 전송 처리*/
    $("#message").on("keyup", function (e) {
        if (e.keyCode === 13) {
            messageSend();
        }
    })

    /*send 즉, 전송 버튼 클릭 시*/
    $("#send").on("click", function (e) {
        messageSend();
    })

    /*메세지 전송 함수*/
    function messageSend() {
        let inputMessage = $("#message").val(); // 사용자가 입력한 텍스트를 저장
        if (inputMessage.length == 0) {
            return;
        }
        stomp.send("/pub/chatting/message", {}, JSON.stringify({
            roomId: roomId,
            chatMessage: inputMessage,
            chatStatus: "UNREAD",
            memberId: sessionId
        }));
        $("#message").val('');
    }


});