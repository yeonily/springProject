package com.codefarm.codefarmer.service.program;

import com.codefarm.codefarmer.domain.program.ProgramDTO;
import com.codefarm.codefarmer.domain.program.QProgramDTO;
import com.codefarm.codefarmer.entity.program.QProgram;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.codefarm.codefarmer.entity.program.QProgram.program;


@Service
@RequiredArgsConstructor
@Slf4j
public class ProgramDetailService {

    private final JPAQueryFactory jpaQueryFactory;

    //    상세페이지 내용 출력
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
                program.programInquire,
                program.member.memberId

        )).from(program).fetch();

    }
}
