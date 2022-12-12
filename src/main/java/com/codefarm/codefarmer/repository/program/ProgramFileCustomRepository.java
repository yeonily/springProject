package com.codefarm.codefarmer.repository.program;

import com.codefarm.codefarmer.entity.board.Reply;
import com.codefarm.codefarmer.entity.program.ProgramFile;

import java.util.List;

public interface ProgramFileCustomRepository {
    public List<ProgramFile> findByProgramId(Long programId);
}
