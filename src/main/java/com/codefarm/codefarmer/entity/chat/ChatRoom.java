package com.codefarm.codefarmer.entity.chat;

import com.codefarm.codefarmer.entity.period.ChatPeriod;
import com.codefarm.codefarmer.entity.member.Member;
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
    @JoinColumn(referencedColumnName = "MEMBER_ID", name="MENTOR_ID")
    @NotNull
    private Member mentor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "MEMBER_ID", name="MENTEE_ID")
    @NotNull
    private Member mentee;

    @Builder
    public ChatRoom(Member mentor, Member mentee) {
        this.mentor = mentor;
        this.mentee = mentee;
    }
}
