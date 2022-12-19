package com.codefarm.codefarmer.entity.alba;


import com.codefarm.codefarmer.domain.alba.AlbaDTO;
import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.entity.period.Period;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TBL_ALBA")
@Getter @Setter
@NoArgsConstructor
public class Alba extends Period {
    @Id @GeneratedValue
    private Long albaId;
    @NotNull
    private String albaTitle;
    @NotNull
    private String albaImage;
    @NotNull
    private String albaTitleOne;
    @NotNull
    private LocalDateTime albaApplyStartDate;
    @NotNull
    private LocalDateTime albaApplyEndDate;
    @NotNull
    private LocalDateTime albaWorkDate;
    @ColumnDefault("0")
    private int albaApplyCount;
    @NotNull
    private int albaApplyTotalCount;
    @NotNull
    private String albaAddress;
    @NotNull
    private int albaPrice;
    @NotNull
    private String albaMainTitle;
    @NotNull @Column(length = 1000)
    private String albaMainContent;
    @NotNull
    private String albaStrongTitle1;
    @NotNull
    private String albaStrongContent1;
    @NotNull
    private String albaStrongTitle2;
    @NotNull
    private String albaStrongContent2;
    @NotNull
    private String albaStrongTitle3;
    @NotNull
    private String albaStrongContent3;
    @NotNull
    private String albaBannerTitle;
    @NotNull
    private String albaBannerOne;
    @NotNull
    private String albaTextTitle;
    @NotNull @Column(length = 1000)
    private String albaText;
    @NotNull
    private String albaProfileTitle1;
    @NotNull
    private String albaProfileContent1;
    @NotNull
    private String albaProfileTitle2;
    @NotNull
    private String albaProfileContent2;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member member;

    public void update(AlbaDTO albaDTO){
        this.albaTitle = albaDTO.getAlbaTitle();
        this.albaImage = albaDTO.getAlbaImage();
        this.albaTitleOne = albaDTO.getAlbaTitleOne();
        this.albaApplyStartDate = albaDTO.getAlbaApplyStartDate();
        this.albaApplyEndDate = albaDTO.getAlbaApplyEndDate();
        this.albaWorkDate = albaDTO.getAlbaWorkDate();
        this.albaApplyCount = albaDTO.getAlbaApplyCount();
        this.albaApplyTotalCount = albaDTO.getAlbaApplyTotalCount();
        this.albaAddress = albaDTO.getAlbaAddress();
        this.albaPrice = albaDTO.getAlbaPrice();
        this.albaMainTitle = albaDTO.getAlbaMainTitle();
        this.albaMainContent = albaDTO.getAlbaMainContent();
        this.albaStrongTitle1 = albaDTO.getAlbaStrongTitle1();
        this.albaStrongContent1 = albaDTO.getAlbaStrongContent1();
        this.albaStrongTitle2 = albaDTO.getAlbaStrongTitle2();
        this.albaStrongContent2 = albaDTO.getAlbaStrongContent2();
        this.albaStrongTitle3 = albaDTO.getAlbaStrongTitle3();
        this.albaStrongContent3 = albaDTO.getAlbaStrongContent3();
        this.albaBannerTitle = albaDTO.getAlbaBannerTitle();
        this.albaBannerOne = albaDTO.getAlbaBannerOne();
        this.albaTextTitle = albaDTO.getAlbaTextTitle();
        this.albaText = albaDTO.getAlbaText();
        this.albaProfileTitle1 = albaDTO.getAlbaProfileTitle1();
        this.albaProfileContent1 = albaDTO.getAlbaProfileContent1();
        this.albaProfileTitle2 = albaDTO.getAlbaProfileTitle2();
        this.albaProfileContent2 = albaDTO.getAlbaProfileContent2();
    }

    public void changeMember(Member member){
        this.member = member;
    }

    @Builder
    public Alba(Long albaId, String albaTitle, String albaImage, String albaTitleOne, LocalDateTime albaApplyStartDate, LocalDateTime albaApplyEndDate, LocalDateTime albaWorkDate, int albaApplyCount, int albaApplyTotalCount, String albaAddress, int albaPrice, String albaMainTitle, String albaMainContent, String albaStrongTitle1, String albaStrongContent1, String albaStrongTitle2, String albaStrongContent2, String albaStrongTitle3, String albaStrongContent3, String albaBannerTitle, String albaBannerOne, String albaTextTitle, String albaText, String albaProfileTitle1, String albaProfileContent1, String albaProfileTitle2, String albaProfileContent2, Member member) {
        this.albaId = albaId;
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
        this.member = member;
    }


}
