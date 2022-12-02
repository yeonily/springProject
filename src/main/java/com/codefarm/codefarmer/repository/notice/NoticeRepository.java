package com.codefarm.codefarmer.repository.notice;

import com.codefarm.codefarmer.entity.notice.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
}
