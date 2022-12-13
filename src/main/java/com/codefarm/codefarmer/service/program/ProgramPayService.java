package com.codefarm.codefarmer.service.program;

import com.codefarm.codefarmer.domain.program.MemberProgramDTO;
import com.codefarm.codefarmer.entity.program.MemberProgram;
import com.codefarm.codefarmer.repository.member.MemberRepository;
import com.codefarm.codefarmer.repository.program.MemberProgramRepository;
import com.codefarm.codefarmer.repository.program.ProgramRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProgramPayService {

    private final MemberRepository memberRepository;
    private final ProgramRepository programRepository;
    private final MemberProgramRepository memberProgramRepository;

    public void applyProgram(MemberProgramDTO memberProgramDTO,Long programId,Long memberId){
        MemberProgram memberProgram = memberProgramDTO.toEntity();

        memberProgram.changeMember(memberRepository.findById(memberId).get());
        memberProgram.changeProgram(programRepository.findById(programId).get());
        memberProgramRepository.save(memberProgram);
    }
}
