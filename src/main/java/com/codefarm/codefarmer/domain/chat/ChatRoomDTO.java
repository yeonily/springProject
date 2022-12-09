package com.codefarm.codefarmer.domain.chat;

import com.codefarm.codefarmer.entity.chat.ChatRoom;
import com.codefarm.codefarmer.entity.member.Member;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

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

}


















