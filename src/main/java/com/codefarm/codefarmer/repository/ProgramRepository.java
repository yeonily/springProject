package com.codefarm.codefarmer.repository;

import com.codefarm.codefarmer.entity.MemberAlba;
import com.codefarm.codefarmer.entity.Program;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramRepository extends JpaRepository<Program, Long> {
}
