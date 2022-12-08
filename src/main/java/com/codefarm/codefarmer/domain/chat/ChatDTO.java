package com.codefarm.codefarmer.domain.chat;

import com.codefarm.codefarmer.entity.chat.Chat;
import com.codefarm.codefarmer.entity.chat.ChatRoom;
import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.type.ChatStatus;
import com.codefarm.codefarmer.type.MessageType;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
    @JsonIgnore
    private ChatRoom chatRoom;
    private String chatMessage;
    private ChatStatus chatStatus;
    private LocalDateTime chatDate;
    @JsonIgnore
    private Member member;
    private MessageType type;
    private Long memberId;

    @QueryProjection
    public ChatDTO(Long chatId, ChatRoom chatRoom, String chatMessage, ChatStatus chatStatus, LocalDateTime chatDate, MessageType type, Member member, Long memberId) {
        this.chatId = chatId;
        this.chatRoom = chatRoom;
        this.chatMessage = chatMessage;
        this.chatStatus = chatStatus;
        this.chatDate = chatDate;
        this.type = type;
        this.member = member;
        this.memberId = memberId;
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



















