package com.codefarm.codefarmer.domain.chat;

import com.codefarm.codefarmer.entity.chat.ChatRoom;
import com.codefarm.codefarmer.entity.member.Member;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@NoArgsConstructor
@Data
public class ChatRoomDTO {
    private Long chatRoomId;
    private Member mentor;
    private Member mentee;
    private LocalDateTime chatDate;

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
