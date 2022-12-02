package com.codefarm.codefarmer.service.program;

import com.codefarm.codefarmer.domain.program.ProgramDTO;
import com.codefarm.codefarmer.domain.program.QProgramDTO;
import com.codefarm.codefarmer.entity.program.QProgram;
import com.codefarm.codefarmer.type.ProgramType;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.codefarm.codefarmer.entity.program.QProgram.program;


@Service
@RequiredArgsConstructor
@Slf4j
public class ProgramListService {

    private final JPAQueryFactory jpaQueryFactory;
    //    프로그램 목록 전체 출력
    public List<ProgramDTO> showAll(){

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
                program.programInquire

        )).from(program).fetch();

    }

//    진행중 정렬
    public List<ProgramDTO> showListByContinue(){

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
                program.programInquire

        )).from(program)
         .where(program.programWorkStartTime.before(localDateTime).and(program.programWorkEndTime.after(localDateTime)))
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
            program.programInquire

    )).from(program)
            .orderBy(program.programApplyStartDate.desc())
            .fetch();
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
            program.programInquire

    )).from(program)
            .orderBy(program.programApplyEndDate.desc())
            .fetch();
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
            program.programInquire

    )).from(program)
            .where(program.programType.eq(ProgramType.ONLY_MENTEE))
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
            program.programInquire

    )).from(program)
            .where(program.programType.eq(ProgramType.ALL_USER))
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
            program.programInquire

    )).from(program)
            .where(program.programPrice.gt(0))
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
            program.programInquire

    )).from(program)
            .where(program.programPrice.eq(0))
            .fetch();
}

}
