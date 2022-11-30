package com.codefarm.codefarmer.entity;

import com.codefarm.codefarmer.type.ChatStatus;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_CHAT")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Chat extends ChatPeriod{
    @Id @GeneratedValue
    private Long chatId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CHAT_ROOM_ID")
    private ChatRoom chatRoom;

    @NotNull
    private String chatMessage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ChatStatus chatStatus;

    @Builder
    public Chat(String chatMessage, ChatStatus chatStatus) {
        this.chatMessage = chatMessage;
        this.chatStatus = chatStatus;
    }

    public void changeMember(Member member){
        this.member = member;
    }
    public void changeChatRoom(ChatRoom chatRoom) {this.chatRoom = chatRoom;}
    public void changeChatStatus(ChatStatus chatStatus) {this.chatStatus = chatStatus;}
}

