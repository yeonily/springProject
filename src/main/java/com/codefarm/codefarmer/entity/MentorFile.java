package com.codefarm.codefarmer.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_MENTOR_FILE")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@RequiredArgsConstructor
public class MentorFile extends Period{
    @Id @GeneratedValue
    private Long fileId;
    @Column(nullable = false)
    private String fileRealname;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MENTOR_BOARD_ID")
    private MentorBoard mentorBoard;

    @Builder
    public MentorFile(String fileRealname) {
        this.fileRealname = fileRealname;
    }
}
