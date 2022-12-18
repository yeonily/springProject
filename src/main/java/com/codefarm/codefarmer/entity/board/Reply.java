package com.codefarm.codefarmer.entity.board;

import com.codefarm.codefarmer.domain.board.ReplyDTO;
import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.entity.period.Period;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "TBL_REPLY")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reply extends Period {
    @Id @GeneratedValue
    private Long replyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOARD_ID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member member;

    @NotNull
    private String replyContent;

    public void update(ReplyDTO replyDTO){
        this.replyContent = replyDTO.getReplyContent();
    }

    public void changeMember(Member member){
        this.member = member;
    }

    public void changeBoard(Board board){
        this.board = board;
    }

    @Builder
    public Reply(String replyContent) {
        this.replyContent = replyContent;
    }
}
