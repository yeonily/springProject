package com.codefarm.codefarmer.domain.chat;

import com.codefarm.codefarmer.entity.chat.Chat;
import com.codefarm.codefarmer.entity.chat.ChatRoom;
import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.type.ChatStatus;
import com.codefarm.codefarmer.type.MessageType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Component
@NoArgsConstructor
@Data
public class ChatDTO {
    private Long chatId;
    private ChatRoom chatRoom;
    private String chatMessage;
    private ChatStatus chatStatus;
    private LocalDateTime chatDate;
    private Member memberId;
    private Set<WebSocketSession> sessions = new HashSet<>();
    private MessageType type;

    @QueryProjection
    public ChatDTO(Long chatId, ChatRoom chatRoom, String chatMessage, ChatStatus chatStatus, LocalDateTime chatDate, MessageType type) {
        this.chatId = chatId;
        this.chatRoom = chatRoom;
        this.chatMessage = chatMessage;
        this.chatStatus = chatStatus;
        this.chatDate = chatDate;
        this.type = type;
    }

    public Chat toEntity(){
        return Chat.builder()
                .chatMessage(chatMessage)
                .chatStatus(chatStatus)
                .build();
    }


    /*채팅 환영*/
    public void handleMessage(WebSocketSession session, Chat chat, ObjectMapper objectMapper) throws IOException {
        /*사용자가 채팅방에 입장하였을 때, 세션 입장*/
        if(getType() == MessageType.ENTER) {
            sessions.add(session);
            chat.changeChatMessage(chat.getMember().getMemberNickname() + "님이 입장하셨습니다.");
        }
        /*사용자가 채팅방에 나갔을 때, 세션 삭제*/
        else if(getType() == MessageType.LEAVE) {
            sessions.remove(session);
            chat.changeChatMessage(chat.getMember().getMemberNickname() + "님이 퇴장하셨습니다.");
        }
        /*사용자가 전송 버튼을 눌렀을 때, 메세지 전송*/
        else {
            chat.changeChatMessage(chat.getMember().getMemberNickname() + " : " + chat.getChatMessage()); // "민혁 : 안녕하세요"와 같이 출력
        }
        send(chat, objectMapper); // 최종적으로 3가지의 상황에 따라 send하여 메세지로 출력
    }


    /*메세지를 보내는 메소드*/
    public void send(Chat chat, ObjectMapper objectMapper) throws IOException {
        TextMessage textMessage = new TextMessage(objectMapper.writeValueAsString(chat.getChatMessage()));
        for(WebSocketSession sess : sessions) {
            sess.sendMessage(textMessage);
        }
    }
}



















