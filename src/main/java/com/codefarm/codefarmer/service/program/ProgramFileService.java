package com.codefarm.codefarmer.service.program;

import com.codefarm.codefarmer.domain.program.ProgramFileDTO;
import com.codefarm.codefarmer.entity.program.ProgramFile;
import com.codefarm.codefarmer.repository.program.ProgramFileRepository;
import com.codefarm.codefarmer.repository.program.ProgramRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProgramFileService {
    private final ProgramFileRepository programFileRepository;

    public void saveAll(ProgramFileDTO programFileDTO){
        programFileRepository.save(programFileDTO.toEntity());
    }
}
