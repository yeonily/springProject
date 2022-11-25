package com.codefarm.codefarmer.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TBL_MENTOR_BOARD")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
public class MentorBoard extends Period{
    @Id @GeneratedValue
    private Long mentorBoardId;
    @Column(nullable = false)
    private String mentorCareer;
    @Column(nullable = false)
    private String mentorExCareer;
    @Column(nullable = false)
    private String mentorStrongTitle1;
    @Column(nullable = false)
    private String mentorStrongContent1;
    @Column(nullable = false)
    private String mentorStrongTitle2;
    @Column(nullable = false)
    private String mentorStrongContent2;
    @Column(nullable = false)
    private String mentorStrongTitle3;
    @Column(nullable = false)
    private String mentorStrongContent3;
    @Column(nullable = false)
    private String mentorTitle;
    @Column(nullable = false)
    private String mentorTitleSub;
    @Column(nullable = false)
    private String mentorTextTitle;
    @Column(nullable = false)
    private String mentorTextContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "mentorBoard")
    private List<MentorFile> mentorFiles;

    @Builder
    public MentorBoard(String mentorCareer, String mentorExCareer, String mentorStrongTitle1, String mentorStrongContent1, String mentorStrongTitle2, String mentorStrongContent2, String mentorStrongTitle3, String mentorStrongContent3, String mentorTitle, String mentorTitleSub, String mentorTextTitle, String mentorTextContent) {
        this.mentorCareer = mentorCareer;
        this.mentorExCareer = mentorExCareer;
        this.mentorStrongTitle1 = mentorStrongTitle1;
        this.mentorStrongContent1 = mentorStrongContent1;
        this.mentorStrongTitle2 = mentorStrongTitle2;
        this.mentorStrongContent2 = mentorStrongContent2;
        this.mentorStrongTitle3 = mentorStrongTitle3;
        this.mentorStrongContent3 = mentorStrongContent3;
        this.mentorTitle = mentorTitle;
        this.mentorTitleSub = mentorTitleSub;
        this.mentorTextTitle = mentorTextTitle;
        this.mentorTextContent = mentorTextContent;
    }
}



















