package com.codefarm.codefarmer.repository;

import com.codefarm.codefarmer.entity.Farmer;
import com.codefarm.codefarmer.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
}
