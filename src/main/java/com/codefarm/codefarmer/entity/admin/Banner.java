package com.codefarm.codefarmer.entity.admin;

import com.codefarm.codefarmer.domain.admin.BannerDTO;
import com.codefarm.codefarmer.entity.period.Period;
import com.codefarm.codefarmer.type.BannerStatus;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TBL_BANNER")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Banner extends Period {
    @Id @GeneratedValue
    private Long bannerId;
    @NotNull
    private String bannerTitle;
    @NotNull
    private String bannerInfo;
    @NotNull
    @Enumerated(EnumType.STRING)
    private BannerStatus bannerStatus;
    @NotNull
    private String bannerRealname;
    @NotNull
    private LocalDateTime bannerStartDate;
    @NotNull
    private LocalDateTime bannerEndDate;

    public void update(BannerDTO bannerDTO){
        this.bannerTitle = bannerDTO.getBannerTitle();
        this.bannerInfo = bannerDTO.getBannerInfo();
        this.bannerStatus = bannerDTO.getBannerStatus();
        this.bannerRealname = bannerDTO.getBannerRealname();
        this.bannerStartDate = bannerDTO.getBannerStartDate();
        this.bannerEndDate = bannerDTO.getBannerEndDate();
    }
    
    

    @Builder
    public Banner(String bannerTitle, String bannerInfo, BannerStatus bannerStatus, String bannerRealname, LocalDateTime bannerStartDate, LocalDateTime bannerEndDate) {
        this.bannerTitle = bannerTitle;
        this.bannerInfo = bannerInfo;
        this.bannerStatus = bannerStatus;
        this.bannerRealname = bannerRealname;
        this.bannerStartDate = bannerStartDate;
        this.bannerEndDate = bannerEndDate;
    }
}
