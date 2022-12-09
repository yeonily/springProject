/*-----------------------------------------------------------*/
                        /*채팅방 선택 시*/
/*-----------------------------------------------------------*/
var sessionId = Number($("#sessionId").text()); // 현재 로그인된 세션 아이디

$(".left_lists").on("click", function (){
    const roomId = $(this).find("span").text(); // 각 방의 방 번호를 저장
    const nickName = $(this).find(".list_name_p1").text(); // 닉네임
    let sockJs = new SockJS("/chatting");
    let stomp = Stomp.over(sockJs);

    $(".right_text .list_text_p1").text(nickName); // 닉네임을 선택한 채팅방의 닉네임으로 변경함

    /*실행 순서 첫 번째*/
    /*stomp 소켓 정상적으로 연결되었을 때*/
    stomp.connect({}, function() {
        console.log("연결됨!");

        /*실행 순서 두 번째*/
        /*선택한 채팅방 번호에 따른 채팅 내역을 모두 불러옴*/
        stomp.subscribe("/sub/mento/chatting" + roomId, function (chat) {
            let content = JSON.parse(chat.body); // JSON 키값을 통해 리턴받은 값에 접근 가능
            let message = content.chatMessage; // 메세지 내용
            let sendDate = content.chatDate; // 메세지 날짜
            let writer = content.memberId; // 채팅을 작성한 사람
            let chatStatus = "READ" ? true : false; // 채팅 읽음 표시(읽었을 때는 true)

            // console.log("채팅 : " + chatStatus);

            const item = new LiModel(message, sendDate, writer, chatStatus);
            item.makeLi();

            /*실행 순서 네 번째*/
            function LiModel(message, sendDate, writer, chatStatus) {
                this.message = message;
                this.sendDate = sendDate;
                this.writer = writer;
                this.chatStatus = chatStatus;

                this.makeLi = () => {
                    const month = new Date(this.sendDate).getMonth()+1;
                    const days = new Date(this.sendDate).getDate();
                    let text = "";
                    const check =  this.chatStatus == true ? true : false; // 채팅을 읽은 상태일 경우 true


                    /*현재 로그인 한 세션이 보낸 채팅의 경우*/
                    if(sessionId == this.writer) {
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
                }
            }
        })
        /*실행 순서 세 번째*/
        stomp.send('/pub/chatting/enter', {}, JSON.stringify({roomId: roomId}))
    });

    /*만약 메세지를 입력하는 input에서 엔터를 눌렀을 때 채팅 전송 처리*/
    $("#message").on("keyup", function(e) {
        if(e.keyCode === 13) {
            messageSend();
        }
    })

    /*send 즉, 전송 버튼 클릭 시*/
    $("#send").on("click", function(e) {
        messageSend();
    })


    function messageSend() {
        let inputMessage = $("#message").val(); // 사용자가 입력한 텍스트를 저장
        stomp.send("/pub/chatting/message", {}, JSON.stringify({roomId : roomId, chatMessage : inputMessage, chatStatus: "UNREAD", memberId: sessionId}));
        $("#message").val('');
    }
});