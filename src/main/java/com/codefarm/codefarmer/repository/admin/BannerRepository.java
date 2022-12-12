package com.codefarm.codefarmer.repository.admin;

import com.codefarm.codefarmer.entity.admin.Banner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BannerRepository extends JpaRepository<Banner, Long> {
    //    (배너명 + 배너 텍스트) + 페이징
    public Page<Banner> findByBannerTitleContainingOrBannerInfoContaining(String bannerTitle, String bannerContent, Pageable pageable);
    //    검색(배너명) + 페이징
    public Page<Banner> findByBannerTitleContaining(String bannerTitle, Pageable pageable);
    //    검색(배너 텍스트) + 페이징
    public Page<Banner> findByBannerInfoContaining(String bannerContent, Pageable pageable);

    //    배너 글 개수
    @Query("select count(b) from Banner b")
    public int countByBanner();

}
