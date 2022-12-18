package com.codefarm.codefarmer.service.program;

import com.codefarm.codefarmer.domain.alba.AlbaDTO;
import com.codefarm.codefarmer.domain.program.ProgramDTO;
import com.codefarm.codefarmer.domain.program.ProgramFileDTO;
import com.codefarm.codefarmer.domain.program.QProgramDTO;
import com.codefarm.codefarmer.domain.program.QProgramFileDTO;
import com.codefarm.codefarmer.entity.alba.Alba;
import com.codefarm.codefarmer.entity.program.QProgram;
import com.codefarm.codefarmer.entity.program.QProgramFile;
import com.codefarm.codefarmer.type.ProgramType;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.codefarm.codefarmer.entity.program.QProgram.program;
import static com.codefarm.codefarmer.entity.program.QProgramFile.programFile;


@Service
@RequiredArgsConstructor
@Slf4j
public class ProgramListService {

    private final JPAQueryFactory jpaQueryFactory;
    //    프로그램 목록 전체 출력
    public List<ProgramDTO> showAll(){
        LocalDateTime localDateTime = LocalDateTime.now();
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

        )).from(program).
          where(program.programApplyEndDate.gt(localDateTime)).fetch();
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

//    진행중 정렬
    public List<ProgramDTO> showListByContinue() throws Exception{

        LocalDateTime localDateTime = LocalDateTime.now();

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

        )).from(program)
         .where(program.programApplyStartDate.before(localDateTime).and(program.programApplyEndDate.after(localDateTime)))
         .orderBy(program.programApplyStartDate.desc())
         .fetch();
    }

// 최근 등록일 순 정렬
public List<ProgramDTO> showListByRecentApplyDate(){

    LocalDateTime localDateTime = LocalDateTime.now();

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

    )).from(program)
            .orderBy(program.programApplyStartDate.desc())
            .where(program.programApplyEndDate.gt(localDateTime)).fetch();
}

// 최근 마감일 순 정렬
public List<ProgramDTO> showListByRecentEndDate(){

    LocalDateTime localDateTime = LocalDateTime.now();

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

    )).from(program)
            .orderBy(program.programApplyEndDate.desc())
            .where(program.programApplyEndDate.gt(localDateTime)).fetch();
}

// 멘티 전용만 보이게 정렬
public List<ProgramDTO> showListByOnlyMentee(){

    LocalDateTime localDateTime = LocalDateTime.now();

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

    )).from(program)
            .where(program.programType.eq(ProgramType.ONLY_MENTEE).and(program.programApplyEndDate.gt(localDateTime)))
            .fetch();
}

// 일반인용만 보이게 정렬
public List<ProgramDTO> showListByOnlyUser(){

    LocalDateTime localDateTime = LocalDateTime.now();

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

    )).from(program)
            .where(program.programType.eq(ProgramType.ALL_USER).and(program.programApplyEndDate.gt(localDateTime)))
            .fetch();
}

// 유료프로그램만 보이게 정렬
public List<ProgramDTO> showListByUsePrice(){

    LocalDateTime localDateTime = LocalDateTime.now();

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

    )).from(program)
            .where(program.programPrice.gt(0).and(program.programApplyEndDate.gt(localDateTime)))
            .fetch();
}


// 무료만 보이게 정렬
public List<ProgramDTO> showListByFreePrice(){

    LocalDateTime localDateTime = LocalDateTime.now();

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

    )).from(program)
            .where(program.programPrice.eq(0).and(program.programApplyEndDate.gt(localDateTime)))
            .fetch();
}

    public List<ProgramDTO> findTop8ByOrderByProgramApplyEndDateDesc(){

        LocalDateTime localDateTime = LocalDateTime.now();

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

        )).from(program)
                .where(program.programApplyEndDate.gt(localDateTime))
                .orderBy(program.programApplyEndDate.desc())
                .limit(8)
                .fetch();

    }
}
