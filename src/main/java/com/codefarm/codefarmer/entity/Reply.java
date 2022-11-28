package com.codefarm.codefarmer.entity;

import com.codefarm.codefarmer.domain.ReplyDTO;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_REPLY")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reply extends Period{
    @Id @GeneratedValue
    private Long replyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOARD_ID")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @NotNull
    private String replyContent;

    public void update(ReplyDTO replyDTO){
        this.replyContent = replyDTO.getReplyContent();
    }

    @Builder
    public Reply(String replyContent) {
        this.replyContent = replyContent;
    }
}
