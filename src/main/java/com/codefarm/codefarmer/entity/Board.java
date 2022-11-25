package com.codefarm.codefarmer.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "TBL_BOARD")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
public class Board extends Period{
    @Id @GeneratedValue
    private Long boardId;
    @Column(nullable = false)
    private String boardTitle;
    @Column(nullable = false)
    private String boardContent;
    @ColumnDefault("0")
    private int boardViewCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "board")
    private List<BoardFile> boardFiles;

    @Builder
    public Board(String boardTitle, String boardContent, int boardViewCount) {
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.boardViewCount = boardViewCount;
    }
}





















