package com.codefarm.codefarmer.service.program;

import com.codefarm.codefarmer.domain.member.MemberDTO;
import com.codefarm.codefarmer.domain.program.MemberProgramDTO;
import com.codefarm.codefarmer.domain.program.ProgramDTO;
import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.entity.member.QMember;
import com.codefarm.codefarmer.entity.program.MemberProgram;
import com.codefarm.codefarmer.entity.program.Program;
import com.codefarm.codefarmer.entity.program.QProgram;
import com.codefarm.codefarmer.repository.member.MemberRepository;
import com.codefarm.codefarmer.repository.program.MemberProgramRepository;
import com.codefarm.codefarmer.repository.program.ProgramRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

import static com.codefarm.codefarmer.entity.member.QMember.member;
import static com.codefarm.codefarmer.entity.program.QProgram.program;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProgramPayService {

    private final JPAQueryFactory jpaQueryFactory;
    private final ProgramDetailService programDetailService;
    private final MemberRepository memberRepository;
    private final ProgramRepository programRepository;
    private final MemberProgramRepository memberProgramRepository;

    public int findApplyCount(Long programId){
        return jpaQueryFactory.select(program.programApplyCount).from(program)
                .where(program.programId.eq(programId))
                .fetchOne();
    }
//    public MemberDTO findMemberByMemberId(Long memberId){
//        jpaQueryFactory.select(new QMember(
//
//        ))
//    }

    public void applyProgram(MemberProgramDTO memberProgramDTO, Long programId, Long memberId){
        int programApplyCount = findApplyCount(programId);
        int programNewApplyCount = memberProgramDTO.getProgramApplyCount();
        int programResultApplyCount = programApplyCount + programNewApplyCount;
        ProgramDTO programDTO = programDetailService.showByProgramId(programId);
        programDTO.setProgramApplyCount(programResultApplyCount);
        Program program = programDTO.toEntity();

        MemberProgram memberProgram = memberProgramDTO.toEntity();
        Long programMemberId = programDTO.getMemberId();
        program.changeMember(memberRepository.findById(programMemberId).get());
        memberProgram.changeMember(memberRepository.findById(memberId).get());
        memberProgram.changeProgram(programRepository.findById(programId).get());
        memberProgramRepository.save(memberProgram);
        programRepository.save(program);
    }
}
