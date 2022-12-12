package com.codefarm.codefarmer.domain.chat;

import com.codefarm.codefarmer.entity.chat.Chat;
import com.codefarm.codefarmer.entity.chat.ChatRoom;
import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.type.ChatStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@NoArgsConstructor
@Data
public class ChatDTO {
    private Long chatId;
    private Long roomId;
    @JsonIgnore
    private ChatRoom chatRoom;
    private String chatMessage;
    private ChatStatus chatStatus;
    private LocalDateTime chatDate;
    @JsonIgnore
    private Member member;
    private Long memberId;
    private String nickName;

    @QueryProjection
    public ChatDTO(Long chatId, ChatRoom chatRoom, String chatMessage, ChatStatus chatStatus, LocalDateTime chatDate, Member member, Long memberId, String nickName, Long roomId) {
        this.chatId = chatId;
        this.chatRoom = chatRoom;
        this.chatMessage = chatMessage;
        this.chatStatus = chatStatus;
        this.chatDate = chatDate;
        this.member = member;
        this.memberId = memberId;
        this.nickName = nickName;
        this.roomId = roomId;
    }



    public Chat toEntity(){
        return Chat.builder()
                .chatId(chatId)
                .chatMessage(chatMessage)
                .chatStatus(chatStatus)
                .chatRoom(chatRoom)
                .member(member)
                .build();
    }


}



















