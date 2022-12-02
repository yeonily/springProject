package com.codefarm.codefarmer.domain.admin;

import com.codefarm.codefarmer.entity.admin.Banner;
import com.codefarm.codefarmer.type.BannerStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@NoArgsConstructor
@Data
public class BannerDTO {
    private Long bannerId;
    private String bannerTitle;
    private String bannerInfo;
    private BannerStatus bannerStatus;
    private String bannerRealname;
    private LocalDateTime bannerStartDate;
    private LocalDateTime bannerEndDate;


    public Banner toEntity(){
        return Banner.builder()
                .bannerEndDate(bannerEndDate)
                .bannerInfo(bannerInfo)
                .bannerRealname(bannerRealname)
                .bannerStartDate(bannerStartDate)
                .bannerStatus(bannerStatus)
                .bannerTitle(bannerTitle)
                .build();
    }

    @QueryProjection
    public BannerDTO(Long bannerId, String bannerTitle, String bannerInfo, BannerStatus bannerStatus, String bannerRealname, LocalDateTime bannerStartDate, LocalDateTime bannerEndDate) {
        this.bannerId = bannerId;
        this.bannerTitle = bannerTitle;
        this.bannerInfo = bannerInfo;
        this.bannerStatus = bannerStatus;
        this.bannerRealname = bannerRealname;
        this.bannerStartDate = bannerStartDate;
        this.bannerEndDate = bannerEndDate;
    }
}
