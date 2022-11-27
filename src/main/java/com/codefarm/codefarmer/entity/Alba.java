package com.codefarm.codefarmer.entity;


import com.codefarm.codefarmer.domain.AlbaDTO;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TBL_ALBA")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Alba extends Period{
    @Id @GeneratedValue
    private Long albaId;
    @NonNull
    private String albaTitle;
    @NonNull
    private String albaImage;
    @NonNull
    private String albaTitleOne;
    @NonNull
    private LocalDateTime albaApplyStartDate;
    @NonNull
    private LocalDateTime albaApplyEndDate;
    @NonNull
    private LocalDateTime albaWorkDate;
    @ColumnDefault("0")
    private int albaApplyCount;
    @NonNull
    private int albaApplyTotalCount;
    @NonNull
    private String albaAddress;
    @NonNull
    private int albaPrice;
    @NonNull
    private String albaMainTitle;
    @NonNull
    private String albaMainContent;
    @NonNull
    private String albaStrongTitle1;
    @NonNull
    private String albaStrongContent1;
    @NonNull
    private String albaStrongTitle2;
    @NonNull
    private String albaStrongContent2;
    @NonNull
    private String albaStrongTitle3;
    @NonNull
    private String albaStrongContent3;
    @NonNull
    private String albaBannerTitle;
    @NonNull
    private String albaBannerOne;
    @NonNull
    private String albaTextTitle;
    @NonNull
    private String albaText;
    @NonNull
    private String albaProfileTitle1;
    @NonNull
    private String albaProfileContent1;
    @NonNull
    private String albaProfileTitle2;
    @NonNull
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

    public Alba(@NonNull String albaTitle, @NonNull String albaImage, @NonNull String albaTitleOne, @NonNull LocalDateTime albaApplyStartDate, @NonNull LocalDateTime albaApplyEndDate, @NonNull LocalDateTime albaWorkDate, int albaApplyCount, @NonNull int albaApplyTotalCount, @NonNull String albaAddress, @NonNull int albaPrice, @NonNull String albaMainTitle, @NonNull String albaMainContent, @NonNull String albaStrongTitle1, @NonNull String albaStrongContent1, @NonNull String albaStrongTitle2, @NonNull String albaStrongContent2, @NonNull String albaStrongTitle3, @NonNull String albaStrongContent3, @NonNull String albaBannerTitle, @NonNull String albaBannerOne, @NonNull String albaTextTitle, @NonNull String albaText, @NonNull String albaProfileTitle1, @NonNull String albaProfileContent1, @NonNull String albaProfileTitle2, @NonNull String albaProfileContent2) {
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
