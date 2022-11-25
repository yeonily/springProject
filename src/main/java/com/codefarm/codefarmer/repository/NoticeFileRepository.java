package com.codefarm.codefarmer.repository;

import com.codefarm.codefarmer.entity.MentorFile;
import com.codefarm.codefarmer.entity.NoticeFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeFileRepository extends JpaRepository<NoticeFile, Long> {
}
