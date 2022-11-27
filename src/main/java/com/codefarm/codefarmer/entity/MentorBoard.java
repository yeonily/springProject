package com.codefarm.codefarmer.entity;

import com.codefarm.codefarmer.domain.MentorBoardDTO;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TBL_MENTOR_BOARD")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MentorBoard extends Period{
    @Id @GeneratedValue
    private Long mentorBoardId;
    @NotNull
    private String mentorCareer;
    @NotNull
    private String mentorExCareer;
    @NotNull
    private String mentorStrongTitle1;
    @NotNull
    private String mentorStrongContent1;
    @NotNull
    private String mentorStrongTitle2;
    @NotNull
    private String mentorStrongContent2;
    @NotNull
    private String mentorStrongTitle3;
    @NotNull
    private String mentorStrongContent3;
    @NotNull
    private String mentorTitle;
    @NotNull
    private String mentorTitleSub;
    @NotNull
    private String mentorTextTitle;
    @NotNull
    private String mentorTextContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "mentorBoard")
    private List<MentorFile> mentorFiles;

    public void update(MentorBoardDTO mentorBoardDTO){
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



















