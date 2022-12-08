package com.codefarm.codefarmer.domain.board;

import com.codefarm.codefarmer.domain.member.MemberDTO;
import com.codefarm.codefarmer.entity.board.Board;
import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.entity.board.Reply;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@NoArgsConstructor
@Data
public class ReplyDTO {
    private Long replyId;
    private Long boardId;
    private Long memberId;
    private String memberNickName;
    private String replyContent;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;

    @QueryProjection
    public ReplyDTO(Long replyId, Long boardId, Long memberId, String memberNickName, String replyContent, LocalDateTime createdDate, LocalDateTime updateDate) {
        this.replyId = replyId;
        this.boardId = boardId;
        this.memberId = memberId;
        this.memberNickName = memberNickName;
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