package com.codefarm.codefarmer.domain;

import com.codefarm.codefarmer.entity.Alba;
import com.codefarm.codefarmer.entity.Banner;
import com.codefarm.codefarmer.type.BannerStatus;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
}
