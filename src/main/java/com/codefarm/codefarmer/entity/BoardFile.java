package com.codefarm.codefarmer.entity;

import com.codefarm.codefarmer.domain.BoardFileDTO;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_BOARD_FILE")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@RequiredArgsConstructor
public class BoardFile extends Period{
    @Id @GeneratedValue
    private Long fileId;
    @Column(nullable = false)
    private String fileRealname;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOARD_ID")
    private Board board;


    @Builder
    public BoardFile(String fileRealname) {
        this.fileRealname = fileRealname;
    }
}
