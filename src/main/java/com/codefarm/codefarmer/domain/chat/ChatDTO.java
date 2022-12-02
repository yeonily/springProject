package com.codefarm.codefarmer.domain.chat;

import com.codefarm.codefarmer.entity.chat.Chat;
import com.codefarm.codefarmer.entity.chat.ChatRoom;
import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.type.ChatStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

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

    @QueryProjection
    public ChatDTO(Long chatId, ChatRoom chatRoom, String chatMessage, ChatStatus chatStatus, LocalDateTime chatDate) {
        this.chatId = chatId;
        this.chatRoom = chatRoom;
        this.chatMessage = chatMessage;
        this.chatStatus = chatStatus;
        this.chatDate = chatDate;
    }

    public Chat toEntity(){
        return Chat.builder()
                .chatMessage(chatMessage)
                .chatStatus(chatStatus)
                .build();
    }
}
