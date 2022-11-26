package com.codefarm.codefarmer.entity;

import com.codefarm.codefarmer.domain.ProgramDTO;
import com.codefarm.codefarmer.type.ProgramLevel;
import com.codefarm.codefarmer.type.ProgramType;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "TBL_PROGRAM")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@RequiredArgsConstructor
public class Program extends Period{
    @Id @GeneratedValue
    private Long programId;
    @Column(nullable = false)
    private String programCrop;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProgramType programType;
    @Column(nullable = false)
    private String programTarget1;
    @Column(nullable = false)
    private String programTarget2;
    @Column(nullable = false)
    private String programTarget3;
    @Column(nullable = false)
    private String programTarget4;
    @Column(nullable = false)
    private String programTitle;
    @Column(nullable = false)
    private String programTitleSub;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProgramLevel programLevel;
    @Column(nullable = false)
    private String programResult1;
    @Column(nullable = false)
    private String programResult2;
    @Column(nullable = false)
    private String programResult3;
    @Column(nullable = false)
    private String programResult4;
    @Column(nullable = false)
    private String programFarmerInfo;
    @Column(nullable = false)
    private String programInfoTitle1;
    @Column(nullable = false)
    private String programInfoContent1;
    @Column(nullable = false)
    private String programInfoTitle2;
    @Column(nullable = false)
    private String programInfoContent2;
    @Column(nullable = false)
    private String programInfoTitle3;
    @Column(nullable = false)
    private String programInfoContent3;
    @Column(nullable = false)
    private String programInfoTitle4;
    @Column(nullable = false)
    private String programInfoContent4;
    private LocalDateTime programWorkDate;
    private LocalDateTime programWorkStartTime;
    private LocalDateTime programWorkEndTime;
    private LocalDateTime programApplyStartDate;
    private LocalDateTime programApplyEndDate;
    @Column(nullable = false)
    @ColumnDefault("0")
    private int programApplyCount;
    @Column(nullable = false)
    private int programApplyTotalCount;
    @Column(nullable = false)
    private int programPrice;
    @Column(nullable = false)
    private String programLocation;
    @Column(nullable = false)
    private String programInquire;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "program")
    private List<ProgramFile> programFiles;

    public void update(ProgramDTO programDTO){
        this.programCrop = programCrop;
        this.programType = programType;
        this.programTarget1 = programTarget1;
        this.programTarget2 = programTarget2;
        this.programTarget3 = programTarget3;
        this.programTarget4 = programTarget4;
        this.programTitle = programTitle;
        this.programTitleSub = programTitleSub;
        this.programLevel = programLevel;
        this.programResult1 = programResult1;
        this.programResult2 = programResult2;
        this.programResult3 = programResult3;
        this.programResult4 = programResult4;
        this.programFarmerInfo = programFarmerInfo;
        this.programInfoTitle1 = programInfoTitle1;
        this.programInfoContent1 = programInfoContent1;
        this.programInfoTitle2 = programInfoTitle2;
        this.programInfoContent2 = programInfoContent2;
        this.programInfoTitle3 = programInfoTitle3;
        this.programInfoContent3 = programInfoContent3;
        this.programInfoTitle4 = programInfoTitle4;
        this.programInfoContent4 = programInfoContent4;
        this.programWorkDate = programWorkDate;
        this.programWorkStartTime = programWorkStartTime;
        this.programWorkEndTime = programWorkEndTime;
        this.programApplyStartDate = programApplyStartDate;
        this.programApplyEndDate = programApplyEndDate;
        this.programApplyTotalCount = programApplyTotalCount;
        this.programPrice = programPrice;
        this.programLocation = programLocation;
        this.programInquire = programInquire;
    }

    @Builder
    public Program(String programCrop, ProgramType programType, String programTarget1, String programTarget2, String programTarget3, String programTarget4, String programTitle, String programTitleSub, ProgramLevel programLevel, String programResult1, String programResult2, String programResult3, String programResult4, String programFarmerInfo, String programInfoTitle1, String programInfoContent1, String programInfoTitle2, String programInfoContent2, String programInfoTitle3, String programInfoContent3, String programInfoTitle4, String programInfoContent4, LocalDateTime programWorkDate, LocalDateTime programWorkStartTime, LocalDateTime programWorkEndTime, LocalDateTime programApplyStartDate, LocalDateTime programApplyEndDate, int programApplyCount, int programApplyTotalCount, int programPrice, String programLocation, String programInquire) {
        this.programCrop = programCrop;
        this.programType = programType;
        this.programTarget1 = programTarget1;
        this.programTarget2 = programTarget2;
        this.programTarget3 = programTarget3;
        this.programTarget4 = programTarget4;
        this.programTitle = programTitle;
        this.programTitleSub = programTitleSub;
        this.programLevel = programLevel;
        this.programResult1 = programResult1;
        this.programResult2 = programResult2;
        this.programResult3 = programResult3;
        this.programResult4 = programResult4;
        this.programFarmerInfo = programFarmerInfo;
        this.programInfoTitle1 = programInfoTitle1;
        this.programInfoContent1 = programInfoContent1;
        this.programInfoTitle2 = programInfoTitle2;
        this.programInfoContent2 = programInfoContent2;
        this.programInfoTitle3 = programInfoTitle3;
        this.programInfoContent3 = programInfoContent3;
        this.programInfoTitle4 = programInfoTitle4;
        this.programInfoContent4 = programInfoContent4;
        this.programWorkDate = programWorkDate;
        this.programWorkStartTime = programWorkStartTime;
        this.programWorkEndTime = programWorkEndTime;
        this.programApplyStartDate = programApplyStartDate;
        this.programApplyEndDate = programApplyEndDate;
        this.programApplyCount = programApplyCount;
        this.programApplyTotalCount = programApplyTotalCount;
        this.programPrice = programPrice;
        this.programLocation = programLocation;
        this.programInquire = programInquire;
    }
}






















