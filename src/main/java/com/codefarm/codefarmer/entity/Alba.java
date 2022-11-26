package com.codefarm.codefarmer.entity;


import com.codefarm.codefarmer.domain.AlbaDTO;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TBL_ALBA")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@RequiredArgsConstructor
public class Alba extends Period{
    @Id @GeneratedValue
    private Long albaId;
    @Column(nullable = false)
    private String albaTitle;
    @Column(nullable = false)
    private String albaImage;
    @Column(nullable = false)
    private String albaTitleOne;
    private LocalDateTime albaApplyStartDate;
    private LocalDateTime albaApplyEndDate;
    private LocalDateTime albaWorkDate;
    @ColumnDefault("0")
    private int albaApplyCount;
    @Column(nullable = false)
    private int albaApplyTotalCount;
    @Column(nullable = false)
    private String albaAddress;
    @Column(nullable = false)
    private int albaPrice;
    @Column(nullable = false)
    private String albaMainTitle;
    @Column(nullable = false)
    private String albaMainContent;
    @Column(nullable = false)
    private String albaStrongTitle1;
    @Column(nullable = false)
    private String albaStrongContent1;
    @Column(nullable = false)
    private String albaStrongTitle2;
    @Column(nullable = false)
    private String albaStrongContent2;
    @Column(nullable = false)
    private String albaStrongTitle3;
    @Column(nullable = false)
    private String albaStrongContent3;
    @Column(nullable = false)
    private String albaBannerTitle;
    @Column(nullable = false)
    private String albaBannerOne;
    @Column(nullable = false)
    private String albaTextTitle;
    @Column(nullable = false)
    private String albaText;
    @Column(nullable = false)
    private String albaProfileTitle1;
    @Column(nullable = false)
    private String albaProfileContent1;
    @Column(nullable = false)
    private String albaProfileTitle2;
    @Column(nullable = false)
    private String albaProfileContent2;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    public void update(AlbaDTO albaDTO){
        this.albaTitle = albaTitle;
        this.albaImage = albaImage;
        this.albaTitleOne = albaTitleOne;
        this.albaApplyStartDate = albaApplyStartDate;
        this.albaApplyEndDate = albaApplyEndDate;
        this.albaWorkDate = albaWorkDate;
        this.albaApplyCount = albaApplyCount;
        this.albaApplyTotalCount = albaApplyTotalCount;
        this.albaAddress = albaAddress;
        this.albaPrice = albaPrice;
        this.albaMainTitle = albaMainTitle;
        this.albaMainContent = albaMainContent;
        this.albaStrongTitle1 = albaStrongTitle1;
        this.albaStrongContent1 = albaStrongContent1;
        this.albaStrongTitle2 = albaStrongTitle2;
        this.albaStrongContent2 = albaStrongContent2;
        this.albaStrongTitle3 = albaStrongTitle3;
        this.albaStrongContent3 = albaStrongContent3;
        this.albaBannerTitle = albaBannerTitle;
        this.albaBannerOne = albaBannerOne;
        this.albaTextTitle = albaTextTitle;
        this.albaText = albaText;
        this.albaProfileTitle1 = albaProfileTitle1;
        this.albaProfileContent1 = albaProfileContent1;
        this.albaProfileTitle2 = albaProfileTitle2;
        this.albaProfileContent2 = albaProfileContent2;
    }

    @Builder
    public Alba(String albaTitle, String albaImage, String albaTitleOne, LocalDateTime albaApplyStartDate, LocalDateTime albaApplyEndDate, LocalDateTime albaWorkDate, int albaApplyCount, int albaApplyTotalCount, String albaAddress, int albaPrice, String albaMainTitle, String albaMainContent, String albaStrongTitle1, String albaStrongContent1, String albaStrongTitle2, String albaStrongContent2, String albaStrongTitle3, String albaStrongContent3, String albaBannerTitle, String albaBannerOne, String albaTextTitle, String albaText, String albaProfileTitle1, String albaProfileContent1, String albaProfileTitle2, String albaProfileContent2) {
        this.albaTitle = albaTitle;
        this.albaImage = albaImage;
        this.albaTitleOne = albaTitleOne;
        this.albaApplyStartDate = albaApplyStartDate;
        this.albaApplyEndDate = albaApplyEndDate;
        this.albaWorkDate = albaWorkDate;
        this.albaApplyCount = albaApplyCount;
        this.albaApplyTotalCount = albaApplyTotalCount;
        this.albaAddress = albaAddress;
        this.albaPrice = albaPrice;
        this.albaMainTitle = albaMainTitle;
        this.albaMainContent = albaMainContent;
        this.albaStrongTitle1 = albaStrongTitle1;
        this.albaStrongContent1 = albaStrongContent1;
        this.albaStrongTitle2 = albaStrongTitle2;
        this.albaStrongContent2 = albaStrongContent2;
        this.albaStrongTitle3 = albaStrongTitle3;
        this.albaStrongContent3 = albaStrongContent3;
        this.albaBannerTitle = albaBannerTitle;
        this.albaBannerOne = albaBannerOne;
        this.albaTextTitle = albaTextTitle;
        this.albaText = albaText;
        this.albaProfileTitle1 = albaProfileTitle1;
        this.albaProfileContent1 = albaProfileContent1;
        this.albaProfileTitle2 = albaProfileTitle2;
        this.albaProfileContent2 = albaProfileContent2;
    }
}
