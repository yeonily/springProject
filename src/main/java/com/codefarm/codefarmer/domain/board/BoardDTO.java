package com.codefarm.codefarmer.domain.board;

import com.codefarm.codefarmer.entity.board.Board;
import com.codefarm.codefarmer.entity.member.Member;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@NoArgsConstructor
@Data
public class BoardDTO {
    private Long boardId;
    private String boardTitle;
    private String boardContent;
    private int boardViewCount;
    private Long memberId;
    private Member member;
    private String memberNickName;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private int replyCount;

    private List<ReplyDTO> replies;

    @QueryProjection
    public BoardDTO(Long boardId, String boardTitle, String boardContent, int boardViewCount, String memberNickName, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.boardId = boardId;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.boardViewCount = boardViewCount;
        this.memberNickName = memberNickName;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    @QueryProjection
    public BoardDTO(Long boardId,String boardTitle, String boardContent, int boardViewCount, LocalDateTime createdDate){
        this.boardId = boardId;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.boardViewCount = boardViewCount;
        this.createdDate = createdDate;
    }

    @QueryProjection
    public BoardDTO(Long boardId, String boardTitle, String boardContent, int boardViewCount, String memberNickName, LocalDateTime createdDate, LocalDateTime updatedDate, int replyCount) {
        this.boardId = boardId;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.boardViewCount = boardViewCount;
        this.memberNickName = memberNickName;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.replyCount = replyCount;
    }

    public Board toEntity(){
        return Board.builder()
                .boardContent(boardContent)
                .boardTitle(boardTitle)
                .build();
    }
}
