package com.codefarm.codefarmer.service.program;

import com.codefarm.codefarmer.domain.program.ProgramDTO;
import com.codefarm.codefarmer.entity.program.Program;
import com.codefarm.codefarmer.repository.program.ProgramFileRepository;
import com.codefarm.codefarmer.repository.program.ProgramRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProgramRegisterService {

    private final ProgramRepository programRepository;
    private final ProgramFileRepository programFileRepository;

    public void saveAll(ProgramDTO programDTO){

//        programDTO.setProgramId();
        Program program = programDTO.toEntity();
        program.changeFiles(programDTO.getFiles());

        programRepository.save(program);

        program.getProgramFiles().stream().map(t -> programFileRepository.save(t)).forEach(t -> log.info("파일첨부 값은: " + t.toString()));
    }

}
