package com.codefarm.codefarmer.service.program;

import com.codefarm.codefarmer.domain.program.ProgramDTO;
import com.codefarm.codefarmer.domain.program.ProgramFileDTO;
import com.codefarm.codefarmer.domain.program.QProgramDTO;
import com.codefarm.codefarmer.domain.program.QProgramFileDTO;
import com.codefarm.codefarmer.entity.mentor.QMentorMentee;
import com.codefarm.codefarmer.entity.program.MemberProgram;
import com.codefarm.codefarmer.entity.program.QMemberProgram;
import com.codefarm.codefarmer.entity.program.QProgram;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.codefarm.codefarmer.entity.mentor.QMentorMentee.mentorMentee;
import static com.codefarm.codefarmer.entity.program.QMemberProgram.memberProgram;
import static com.codefarm.codefarmer.entity.program.QProgram.program;
import static com.codefarm.codefarmer.entity.program.QProgramFile.programFile;


@Service
@RequiredArgsConstructor
@Slf4j
public class ProgramDetailService {

    private final JPAQueryFactory jpaQueryFactory;

    //    상세페이지 내용 출력
    public List<ProgramDTO> showAll() {

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
                program.programInfoTitle2,
                program.programInfoTitle3,
                program.programInfoTitle4,
                program.programInfoContent1,
                program.programInfoContent2,
                program.programInfoContent3,
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

        )).from(program).fetch();
    }

//    해당 프로그램 내용 상세페이지 출력
    public ProgramDTO showByProgramId(Long programId) {

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

//    선택한 프로그램의 첨부파일 가져오기
    public List<ProgramFileDTO> showFiles(Long prgramId){
        return jpaQueryFactory.select(new QProgramFileDTO(
                programFile.fileId,
                programFile.fileName,
                programFile.fileUploadPath,
                programFile.fileUuid,
                programFile.fileSize,
                programFile.fileImageCheck
        )).from(programFile)
                .where(programFile.program.programId.eq(prgramId)).fetch();
    }

//    신청한 프로그램인지 확인
    public int programApplyCheck(Long memberId, Long programId){
        return jpaQueryFactory.select(memberProgram.programApplyId).from(memberProgram)
                .where(memberProgram.program.programId.eq(programId).and(memberProgram.member.memberId.eq(memberId)))
                .fetch().size();
    }

//    내가 등록한 프로그램인지 확인
    public Long programRegisterCheck(Long memberId, Long programId){
        return jpaQueryFactory.select(program.programId).from(program)
                .where(program.programId.eq(programId).and(program.member.memberId.eq(memberId)))
                .fetchOne();
    }

//   해당 프로그램의 작성자 memberId 찾기
    public Long findMemberIdByProgramId(Long programId){
        return jpaQueryFactory.select(program.member.memberId).from(program)
                .where(program.programId.eq(programId))
                .fetchOne();
    }

//    멘토멘티 관계 확인
    public Long findMentorMenteeTrue(Long memberId, Long mentorId){
        return jpaQueryFactory.select(mentorMentee.mentorMenteeId).from(mentorMentee)
                .where(mentorMentee.mentor.memberId.eq(mentorId).and(mentorMentee.mentee.memberId.eq(memberId)))
                .fetchOne();
    }
}
