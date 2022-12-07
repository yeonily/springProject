package com.codefarm.codefarmer.service.program;

import com.codefarm.codefarmer.domain.program.ProgramDTO;
import com.codefarm.codefarmer.repository.program.ProgramRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProgramRegisterService {

    private final ProgramRepository programRepository;

    public void saveAll(ProgramDTO programDTO){

        programRepository.save(programDTO.toEntity());
    }

}
