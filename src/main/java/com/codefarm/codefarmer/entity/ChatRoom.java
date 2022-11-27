package com.codefarm.codefarmer.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_CHAT_ROOM")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoom extends ChatPeriod {
    @Id @GeneratedValue
    private Long chatRoomId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "MEMBER_ID", name="MENTOR_ID", nullable = false)
    private Member mentor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "MEMBER_ID", name="MENTEE_ID", nullable = false)
    private Member mentee;
}
