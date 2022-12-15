package com.codefarm.codefarmer.repository.program;

import com.codefarm.codefarmer.entity.alba.Alba;
import com.codefarm.codefarmer.entity.program.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProgramRepository extends JpaRepository<Program, Long>, ProgramCustomRepository {
    public List<Program> findTop8ByOrderByProgramApplyEndDateDesc();

//    프로그램 글 개수
    @Query("select count(p) from Program p")
    public int countByProgram();
}
