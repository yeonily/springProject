package com.codefarm.codefarmer.service.program;

import com.codefarm.codefarmer.domain.program.ProgramFileDTO;
import com.codefarm.codefarmer.entity.program.ProgramFile;
import com.codefarm.codefarmer.repository.member.MemberRepository;
import com.codefarm.codefarmer.repository.program.ProgramFileCustomRepositoryImpl;
import com.codefarm.codefarmer.repository.program.ProgramFileRepository;
import com.codefarm.codefarmer.repository.program.ProgramRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProgramDeleteService {
    private final JPAQueryFactory jpaQueryFactory;
    private final ProgramRepository programRepository;
    private final ProgramFileRepository programFileRepository;
    private final MemberRepository memberRepository;
    private final ProgramFileCustomRepositoryImpl programFileCustomRepository;

    public void programDelete(Long programId){
//        List<ProgramFileDTO> programFileS  = programFileRepository.findById(programId);
//        programFileRepository.deleteAllById(programId);
        List<ProgramFile> programFiles = programFileCustomRepository.findByProgramId(programId);

        programFiles.forEach(t -> programFileRepository.delete(t));
        programRepository.deleteById(programId);
    }
}
