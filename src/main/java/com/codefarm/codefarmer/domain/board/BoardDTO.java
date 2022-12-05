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
    private Member member;
    private String memberNickName;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;

    private List<ReplyDTO> replies;


    @QueryProjection
    public BoardDTO(Long boardId, String boardTitle, String boardContent, int boardViewCount, String memberNickName, LocalDateTime createdDate, LocalDateTime updateDate) {
        this.boardId = boardId;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.boardViewCount = boardViewCount;
        this.memberNickName = memberNickName;
        this.createdDate = createdDate;
        this.updateDate = updateDate;
    }




    public Board toEntity(){
        return Board.builder()
                .boardContent(boardContent)
                .boardTitle(boardTitle)
                .boardViewCount(boardViewCount)
                .build();
    }
}
