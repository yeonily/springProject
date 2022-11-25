package com.codefarm.codefarmer.repository;

import com.codefarm.codefarmer.entity.Farmer;
import com.codefarm.codefarmer.entity.MemberProgram;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberProgramRepository extends JpaRepository<MemberProgram, Long> {
}
