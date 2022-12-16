package com.codefarm.codefarmer.repository.program;

import com.codefarm.codefarmer.entity.program.MemberProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberProgramRepository extends JpaRepository<MemberProgram, Long>, MemberProgramCustomRepository {
    @Query("select count(mp) from MemberProgram mp")
    public int countByMemberProgram();
}
