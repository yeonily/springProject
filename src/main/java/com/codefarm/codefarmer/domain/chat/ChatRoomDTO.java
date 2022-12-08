package com.codefarm.codefarmer.domain.chat;

import com.codefarm.codefarmer.entity.chat.Chat;
import com.codefarm.codefarmer.entity.chat.ChatRoom;
import com.codefarm.codefarmer.entity.member.Member;
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
public class ChatRoomDTO {
    private Long chatRoomId;
    private Member mentor;
    private Member mentee;
    private LocalDateTime chatDate;
    private Set<WebSocketSession> sessions = new HashSet<>();

    @QueryProjection
    public ChatRoomDTO(Long chatRoomId, Member mentor, Member mentee, LocalDateTime chatDate) {
        this.chatRoomId = chatRoomId;
        this.mentor = mentor;
        this.mentee = mentee;
        this.chatDate = chatDate;
    }

    public ChatRoom toEntity(){
        return ChatRoom.builder()
                .mentor(mentor)
                .mentee(mentee)
                .build();
    }




    /*채팅 환영*/
    public void handleMessage(WebSocketSession session, ChatDTO chat, ObjectMapper objectMapper) throws IOException {
        /*사용자가 채팅방에 입장하였을 때, 세션 입장*/
        if(chat.getType() == MessageType.ENTER) {
            sessions.add(session);
            chat.setChatMessage("김민혁 님이 입장하셨습니다.");
        }
        /*사용자가 채팅방에 나갔을 때, 세션 삭제*/
        else if(chat.getType() == MessageType.LEAVE) {
            sessions.remove(session);
            chat.setChatMessage("김민혁 님이 퇴장하셨습니다.");
        }
        /*사용자가 전송 버튼을 눌렀을 때, 메세지 전송*/
        else {
            chat.setChatMessage(chat.getMemberId() + " : " + chat.getChatMessage()); // "민혁 : 안녕하세요"와 같이 출력
        }
        send(chat, objectMapper); // 최종적으로 3가지의 상황에 따라 send하여 메세지로 출력
    }


    /*메세지를 보내는 메소드*/
    public void send(ChatDTO chat, ObjectMapper objectMapper) throws IOException {
        TextMessage textMessage = new TextMessage(objectMapper.writeValueAsString(chat.getChatMessage()));
        for(WebSocketSession sess : sessions) {
            sess.sendMessage(textMessage);
        }
    }
}


















