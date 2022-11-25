package com.codefarm.codefarmer.entity;

import com.codefarm.codefarmer.type.ChatStatus;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_CHAT")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
public class Chat extends ChatPeriod{
    @Id @GeneratedValue
    private Long chatId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CHAT_ROOM_ID")
    private ChatRoom chatRoom;

    @Column(nullable = false)
    private String chatMessage;
    @Enumerated(EnumType.STRING)
    private ChatStatus chatStatus;

    @Builder
    public Chat(String chatMessage, ChatStatus chatStatus) {
        this.chatMessage = chatMessage;
        this.chatStatus = chatStatus;
    }
}

