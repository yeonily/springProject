package com.codefarm.codefarmer.repository;

import com.codefarm.codefarmer.entity.Inquire;
import com.codefarm.codefarmer.entity.ProgramFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramFileRepository extends JpaRepository<ProgramFile, Long> {
}
