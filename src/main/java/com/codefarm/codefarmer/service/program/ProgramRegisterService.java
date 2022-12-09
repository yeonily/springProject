package com.codefarm.codefarmer.service.program;

import com.codefarm.codefarmer.domain.program.ProgramDTO;
import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.entity.program.Program;
import com.codefarm.codefarmer.repository.member.MemberRepository;
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
    private final MemberRepository memberRepository;

    public void saveAll(ProgramDTO programDTO){
        Program program = programDTO.toEntity();
        program.changeFiles(programDTO.getFiles());
        program.changeMember(memberRepository.findById(programDTO.getMemberId()).get());
        programRepository.save(program);
        program.getProgramFiles().stream().map(t -> programFileRepository.save(t));
    }

}
