package com.codefarm.codefarmer.repository;

import com.codefarm.codefarmer.entity.Alba;
import com.codefarm.codefarmer.entity.Banner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BannerRepository extends JpaRepository<Banner, Long> {
}
