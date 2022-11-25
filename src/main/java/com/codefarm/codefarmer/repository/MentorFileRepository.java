package com.codefarm.codefarmer.repository;

import com.codefarm.codefarmer.entity.MemberProgram;
import com.codefarm.codefarmer.entity.MentorFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MentorFileRepository extends JpaRepository<MentorFile, Long> {
}
