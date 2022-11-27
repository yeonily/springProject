package com.codefarm.codefarmer.entity;

import com.codefarm.codefarmer.domain.BannerDTO;
import com.codefarm.codefarmer.type.BannerStatus;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TBL_BANNER")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Banner extends Period{
    @Id @GeneratedValue
    private Long bannerId;
    @NonNull
    private String bannerTitle;
    @NonNull
    private String bannerInfo;
    @NonNull
    @Enumerated(EnumType.STRING)
    private BannerStatus bannerStatus;
    @NonNull
    private String bannerRealname;
    @NonNull
    private LocalDateTime bannerStartDate;
    @NonNull
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
    public Banner(@NonNull String bannerTitle, @NonNull String bannerInfo, BannerStatus bannerStatus, @NonNull String bannerRealname, @NonNull LocalDateTime bannerStartDate, @NonNull LocalDateTime bannerEndDate) {
        this.bannerTitle = bannerTitle;
        this.bannerInfo = bannerInfo;
        this.bannerStatus = bannerStatus;
        this.bannerRealname = bannerRealname;
        this.bannerStartDate = bannerStartDate;
        this.bannerEndDate = bannerEndDate;
    }
}
