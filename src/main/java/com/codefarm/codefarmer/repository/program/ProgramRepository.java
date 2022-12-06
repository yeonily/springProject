package com.codefarm.codefarmer.repository.program;

import com.codefarm.codefarmer.entity.alba.Alba;
import com.codefarm.codefarmer.entity.program.Program;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProgramRepository extends JpaRepository<Program, Long> {
    public List<Program> findTop8ByOrderByProgramApplyEndDateDesc();
}
