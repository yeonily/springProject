package com.codefarm.codefarmer.entity;

import com.codefarm.codefarmer.domain.BoardDTO;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "TBL_BOARD")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends Period{
    @Id @GeneratedValue
    private Long boardId;
    @NotNull
    private String boardTitle;
    @NotNull
    private String boardContent;
    @ColumnDefault("0")
    private int boardViewCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "board", cascade = CascadeType.REMOVE)
    private List<Reply> replies;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "board", cascade = CascadeType.REMOVE)
    private List<BoardFile> boardFiles;

    public void update(BoardDTO boardDTO){
        this.boardTitle = boardDTO.getBoardTitle();
        this.boardContent = boardDTO.getBoardContent();
    }

    public void changeMember(Member member){
        this.member = member;
    }

    @Builder
    public Board(String boardTitle, String boardContent, int boardViewCount) {
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.boardViewCount = boardViewCount;
    }
}





















