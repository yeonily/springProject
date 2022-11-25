package com.codefarm.codefarmer.domain;

import com.codefarm.codefarmer.entity.Alba;
import com.codefarm.codefarmer.entity.Member;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import java.time.LocalDateTime;

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


    public Alba toEntity(){
        return Alba.builder()
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
                .build();
    }



















}
