package com.codefarm.codefarmer.domain.chat;

import com.codefarm.codefarmer.entity.chat.ChatRoom;
import com.codefarm.codefarmer.entity.member.Member;
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

    public ChatRoom toEntity(){
        return ChatRoom.builder()
                .mentor(mentor)
                .mentee(mentee)
                .build();
    }
}
