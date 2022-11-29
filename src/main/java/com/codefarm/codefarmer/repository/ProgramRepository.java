package com.codefarm.codefarmer.repository;

import com.codefarm.codefarmer.entity.MemberAlba;
import com.codefarm.codefarmer.entity.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface ProgramRepository extends JpaRepository<Program, Long> {

}
