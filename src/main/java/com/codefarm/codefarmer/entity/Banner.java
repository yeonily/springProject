package com.codefarm.codefarmer.entity;

import com.codefarm.codefarmer.domain.BannerDTO;
import com.codefarm.codefarmer.type.BannerStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TBL_BANNER")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@RequiredArgsConstructor
public class Banner extends Period{
    @Id @GeneratedValue
    private Long bannerId;
    @Column(nullable = false)
    private String bannerTitle;
    @Column(nullable = false)
    private String bannerInfo;
    @Enumerated(EnumType.STRING)
    private BannerStatus bannerStatus;
    @Column(nullable = false)
    private String bannerRealname;
    private LocalDateTime bannerStartDate;
    private LocalDateTime bannerEndDate;

    public void update(BannerDTO bannerDTO){
        this.bannerTitle = bannerTitle;
        this.bannerInfo = bannerInfo;
        this.bannerStatus = bannerStatus;
        this.bannerRealname = bannerRealname;
        this.bannerStartDate = bannerStartDate;
        this.bannerEndDate = bannerEndDate;
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
