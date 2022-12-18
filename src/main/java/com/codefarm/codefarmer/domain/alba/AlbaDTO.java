package com.codefarm.codefarmer.domain.alba;

import com.codefarm.codefarmer.entity.alba.Alba;
import com.codefarm.codefarmer.entity.member.Member;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@NoArgsConstructor
@Data
public class AlbaDTO {
    private Long albaId;
    private String albaTitle;
    private String albaImage;
    private String albaTitleOne;
    private LocalDateTime albaApplyStartDate;
    private LocalDateTime albaApplyEndDate;
    private LocalDateTime albaWorkDate;
    private int albaApplyCount;
    private int albaApplyTotalCount;
    private String albaAddress;
    private int albaPrice;
    private String albaMainTitle;
    private String albaMainContent;
    private String albaStrongTitle1;
    private String albaStrongContent1;
    private String albaStrongTitle2;
    private String albaStrongContent2;
    private String albaStrongTitle3;
    private String albaStrongContent3;
    private String albaBannerTitle;
    private String albaBannerOne;
    private String albaTextTitle;
    private String albaText;
    private String albaProfileTitle1;
    private String albaProfileContent1;
    private String albaProfileTitle2;
    private String albaProfileContent2;
    private Member member;
    private Long memberId;
    private int EndPage;
    private String memberName;
    private String memberEmail;

    public Alba toEntity() {
        return Alba.builder()
                .albaId(albaId)
                .albaAddress(albaAddress)
                .albaApplyCount(albaApplyCount)
                .albaApplyEndDate(albaApplyEndDate)
                .albaApplyStartDate(albaApplyStartDate)
                .albaApplyTotalCount(albaApplyTotalCount)
                .albaBannerOne(albaBannerOne)
                .albaBannerTitle(albaBannerTitle)
                .albaImage(albaImage)
                .albaMainContent(albaMainContent)
                .albaMainTitle(albaMainTitle)
                .albaPrice(albaPrice)
                .albaProfileContent1(albaProfileContent1)
                .albaProfileContent2(albaProfileContent2)
                .albaProfileTitle1(albaProfileTitle1)
                .albaProfileTitle2(albaProfileTitle2)
                .albaStrongContent1(albaStrongContent1)
                .albaStrongContent2(albaStrongContent2)
                .albaStrongContent3(albaStrongContent3)
                .albaStrongTitle1(albaStrongTitle1)
                .albaStrongTitle2(albaStrongTitle2)
                .albaStrongTitle3(albaStrongTitle3)
                .albaText(albaText)
                .albaTextTitle(albaTextTitle)
                .albaTitle(albaTitle)
                .albaTitleOne(albaTitleOne)
                .albaWorkDate(albaWorkDate)
                .member(member)
                .build();
    }

    @QueryProjection
    public AlbaDTO( Long albaId, String albaTitle, String albaImage, String albaTitleOne, LocalDateTime albaApplyStartDate, LocalDateTime albaApplyEndDate, LocalDateTime albaWorkDate, int albaApplyCount, int albaApplyTotalCount, String albaAddress, int albaPrice, String albaMainTitle, String albaMainContent, String albaStrongTitle1, String albaStrongContent1, String albaStrongTitle2, String albaStrongContent2, String albaStrongTitle3, String albaStrongContent3, String albaBannerTitle, String albaBannerOne, String albaTextTitle, String albaText, String albaProfileTitle1, String albaProfileContent1, String albaProfileTitle2, String albaProfileContent2, Long memberId, String memberName, String memberEmail) {
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
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberEmail = memberEmail;
    }

    @QueryProjection
    public AlbaDTO( Long albaId, String albaTitle, String albaImage, String albaTitleOne, LocalDateTime albaApplyStartDate, LocalDateTime albaApplyEndDate, LocalDateTime albaWorkDate, int albaApplyCount, int albaApplyTotalCount, String albaAddress, int albaPrice, String albaMainTitle, String albaMainContent, String albaStrongTitle1, String albaStrongContent1, String albaStrongTitle2, String albaStrongContent2, String albaStrongTitle3, String albaStrongContent3, String albaBannerTitle, String albaBannerOne, String albaTextTitle, String albaText, String albaProfileTitle1, String albaProfileContent1, String albaProfileTitle2, String albaProfileContent2, Long memberId) {
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
        this.memberId = memberId;
    }

    public AlbaDTO(Alba entity) {
        this.albaId = entity.getAlbaId();
        this.albaTitle = entity.getAlbaTitle();
        this.albaImage = entity.getAlbaImage();
        this.albaTitleOne = entity.getAlbaTitleOne();
        this.albaApplyStartDate = entity.getAlbaApplyStartDate();
        this.albaApplyEndDate = entity.getAlbaApplyEndDate();
        this.albaWorkDate = entity.getAlbaWorkDate();
        this.albaApplyCount = entity.getAlbaApplyCount();
        this.albaApplyTotalCount = entity.getAlbaApplyTotalCount();
        this.albaAddress = entity.getAlbaAddress();
        this.albaPrice = entity.getAlbaPrice();
        this.albaMainTitle = entity.getAlbaMainTitle();
        this.albaMainContent = entity.getAlbaMainContent();
        this.albaStrongTitle1 = entity.getAlbaStrongTitle1();
        this.albaStrongContent1 = entity.getAlbaStrongContent1();
        this.albaStrongTitle2 = entity.getAlbaStrongTitle2();
        this.albaStrongContent2 = entity.getAlbaStrongContent2();
        this.albaStrongTitle3 = entity.getAlbaStrongTitle3();
        this.albaStrongContent3 = entity.getAlbaStrongContent3();
        this.albaBannerTitle = entity.getAlbaBannerTitle();
        this.albaBannerOne = entity.getAlbaBannerOne();
        this.albaTextTitle = entity.getAlbaTextTitle();
        this.albaText = entity.getAlbaText();
        this.albaProfileTitle1 = entity.getAlbaProfileTitle1();
        this.albaProfileContent1 = entity.getAlbaProfileContent1();
        this.albaProfileTitle2 = entity.getAlbaProfileTitle2();
        this.albaProfileContent2 = entity.getAlbaProfileContent2();
    }
}
