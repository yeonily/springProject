package com.codefarm.codefarmer.domain;

import com.codefarm.codefarmer.entity.Board;
import com.codefarm.codefarmer.entity.BoardFile;
import com.codefarm.codefarmer.entity.Member;
import com.codefarm.codefarmer.type.BannerStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.bytebuddy.asm.Advice;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.stereotype.Component;

import javax.persistence.*;
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
    private Member memberId;
    private String memberNickName;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;

    @QueryProjection
    public BoardDTO(Long boardId, String boardTitle, String boardContent, Long memberId, String memberNickName ,int boardViewCount, LocalDateTime createdDate, LocalDateTime updateDate) {
        this.boardId = boardId;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.boardViewCount = boardViewCount;
//        this.memberId = member;
//        this.memberId = memberId;
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
