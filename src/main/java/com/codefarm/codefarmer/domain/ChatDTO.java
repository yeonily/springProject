package com.codefarm.codefarmer.domain;

import com.codefarm.codefarmer.entity.BoardFile;
import com.codefarm.codefarmer.entity.Chat;
import com.codefarm.codefarmer.entity.ChatRoom;
import com.codefarm.codefarmer.entity.Member;
import com.codefarm.codefarmer.type.ChatStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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
