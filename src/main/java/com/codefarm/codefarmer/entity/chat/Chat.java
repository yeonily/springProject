package com.codefarm.codefarmer.entity.chat;

import com.codefarm.codefarmer.domain.chat.ChatDTO;
import com.codefarm.codefarmer.entity.period.ChatPeriod;
import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.type.ChatStatus;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "TBL_CHAT")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Chat extends ChatPeriod {
    @Id @GeneratedValue
    private Long chatId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CHAT_ROOM_ID")
    private ChatRoom chatRoom;

    @NotNull
    private String chatMessage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member member;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ChatStatus chatStatus;
    

    @Builder
    public Chat(Long chatId, ChatRoom chatRoom, String chatMessage, ChatStatus chatStatus, Member member, Long memberId) {
        this.chatId = chatId;
        this.chatMessage = chatMessage;
        this.chatStatus = chatStatus;
        this.member = member;
        this.chatRoom = chatRoom;
    }

    public void update(ChatDTO chatDTO) {
        this.chatId = chatDTO.getChatId();
        this.chatRoom = chatDTO.getChatRoom();
        this.chatMessage = chatDTO.getChatMessage();
        this.member = chatDTO.getMember();
        this.chatStatus = chatDTO.getChatStatus();
    }

    public void changeMember(Member member){
        this.member = member;
    }
    public void changeChatRoom(ChatRoom chatRoom) {this.chatRoom = chatRoom;}
    public void changeChatMessage(String chatMessage) {this.chatMessage = chatMessage;}
    public void changeChatStatus(ChatStatus chatStatus) {this.chatStatus = chatStatus;}
}

