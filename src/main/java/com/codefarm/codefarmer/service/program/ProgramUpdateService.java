package com.codefarm.codefarmer.service.program;

import com.codefarm.codefarmer.domain.program.ProgramDTO;
import com.codefarm.codefarmer.domain.program.ProgramFileDTO;
import com.codefarm.codefarmer.domain.program.QProgramDTO;
import com.codefarm.codefarmer.entity.program.Program;
import com.codefarm.codefarmer.entity.program.ProgramFile;
import com.codefarm.codefarmer.entity.program.QProgram;
import com.codefarm.codefarmer.repository.member.MemberRepository;
import com.codefarm.codefarmer.repository.program.ProgramFileCustomRepository;
import com.codefarm.codefarmer.repository.program.ProgramFileRepository;
import com.codefarm.codefarmer.repository.program.ProgramRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.codefarm.codefarmer.entity.program.QProgram.program;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProgramUpdateService {

    private final JPAQueryFactory jpaQueryFactory;
    private final ProgramRepository programRepository;
    private final ProgramFileRepository programFileRepository;
    private final MemberRepository memberRepository;
    private final ProgramFileCustomRepository programFileCustomRepository;


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
        log.info("업데이트 내용: " + programDTO.toString());
        Program program = programDTO.toEntity();

        program.changeFiles(programDTO.getFiles());
        program.changeMember(memberRepository.findById(programDTO.getMemberId()).get());

        program.getProgramFiles().stream().map(t -> programFileRepository.save(t));

        List<ProgramFile> programFiles = programFileCustomRepository.findByProgramId(programDTO.getProgramId());
        programFiles.forEach(t -> programFileRepository.delete(t));

        List<ProgramFileDTO> files = programDTO.getFiles();
        log.info(files.toString());
        files.stream().map(t -> t.toEntity());

//        List<ProgramFile> programFiles = new ArrayList<>();
//        files.stream().map(t -> t.toEntity()).forEach(programFiles::add);

//        programFiles.forEach(t -> log.info("테스트" + t));
//        log.info("programfiles" + programFiles.get(0).toString());
//        log.info("files" + files.get(0).toString());
//        for(int i =0 ; i< programFiles.size(); i++){
//            programFiles.get(i).update(files.get(i));
//        }
        program.update(programDTO);
        programRepository.save(program);

    }

}
