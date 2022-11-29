package com.codefarm.codefarmer.domain;

import com.codefarm.codefarmer.entity.Board;
import com.codefarm.codefarmer.entity.Member;
import com.codefarm.codefarmer.entity.Program;
import com.codefarm.codefarmer.entity.Reply;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Component
@NoArgsConstructor
@Data
public class ReplyDTO {
    private Long replyId;
    private Board board;
    private Member member;
    private String replyContent;
    private Member memberId;
    private Board boardId;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;

    @QueryProjection
    public ReplyDTO(Long replyId, Board board, Member member, String replyContent, LocalDateTime createdDate, LocalDateTime updateDate) {
        this.replyId = replyId;
        this.board = board;
        this.member = member;
        this.replyContent = replyContent;
        this.createdDate = createdDate;
        this.updateDate = updateDate;
    }

    public Reply toEntity(){
        return Reply.builder()
                .replyContent(replyContent)
                .build();
    }
}