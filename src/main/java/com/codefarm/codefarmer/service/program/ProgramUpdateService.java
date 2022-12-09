package com.codefarm.codefarmer.service.program;

import com.codefarm.codefarmer.domain.program.ProgramDTO;
import com.codefarm.codefarmer.domain.program.QProgramDTO;
import com.codefarm.codefarmer.entity.program.Program;
import com.codefarm.codefarmer.entity.program.QProgram;
import com.codefarm.codefarmer.repository.member.MemberRepository;
import com.codefarm.codefarmer.repository.program.ProgramFileRepository;
import com.codefarm.codefarmer.repository.program.ProgramRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.stereotype.Service;

import static com.codefarm.codefarmer.entity.program.QProgram.program;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProgramUpdateService {

    private final JPAQueryFactory jpaQueryFactory;
    private final ProgramRepository programRepository;
    private final ProgramFileRepository programFileRepository;
    private final MemberRepository memberRepository;


//    프로그램Id로 수정 페이지 내용 출력
    public ProgramDTO showUpdate(Long programId){
        return jpaQueryFactory.select(new QProgramDTO(
                program.programId,
                program.programCrop,
                program.programType,
                program.programTarget1,
                program.programTarget2,
                program.programTarget3,
                program.programTarget4,
                program.programTitle,
                program.programTitleSub,
                program.programLevel,
                program.programResult1,
                program.programResult2,
                program.programResult3,
                program.programResult4,
                program.programFarmerInfo,
                program.programInfoTitle1,
                program.programInfoContent1,
                program.programInfoTitle2,
                program.programInfoContent2,
                program.programInfoTitle3,
                program.programInfoContent3,
                program.programInfoTitle4,
                program.programInfoContent4,
                program.programWorkDate,
                program.programWorkStartTime,
                program.programWorkEndTime,
                program.programApplyStartDate,
                program.programApplyEndDate,
                program.programApplyCount,
                program.programApplyTotalCount,
                program.programPrice,
                program.programLocation,
                program.programInquire,
                program.member.memberId
        )).from(program)
           .where(program.programId.eq(programId))
           .fetchOne();
    }

    public void update(ProgramDTO programDTO){

        Program program = programDTO.toEntity();
        program.changeFiles(programDTO.getFiles());
//        program.changeMember(memberRepository.findById(programDTO.getMemberId()).get());
        program.getProgramFiles().stream().map(t -> programFileRepository.save(t));
        program.update(programDTO);
        programRepository.deleteById(program.getProgramId());
        programRepository.save(program);

    }

}
