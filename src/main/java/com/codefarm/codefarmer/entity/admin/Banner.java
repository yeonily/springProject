package com.codefarm.codefarmer.entity.admin;

import com.codefarm.codefarmer.entity.period.Period;
import com.codefarm.codefarmer.type.BannerStatus;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "TBL_BANNER")
@Getter @Setter
@NoArgsConstructor
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

    @Builder
    public Banner(String bannerTitle, String bannerInfo, String bannerRealname, LocalDateTime bannerStartDate, LocalDateTime bannerEndDate) {
        this.bannerTitle = bannerTitle;
        this.bannerInfo = bannerInfo;
        this.bannerRealname = bannerRealname;
        this.bannerStartDate = bannerStartDate;
        this.bannerEndDate = bannerEndDate;
    }
}
