package com.codefarm.codefarmer.entity;

import com.codefarm.codefarmer.domain.ProgramDTO;
import com.codefarm.codefarmer.repository.ProgramRepository;
import com.codefarm.codefarmer.type.Oauth;
import com.codefarm.codefarmer.type.ProgramLevel;
import com.codefarm.codefarmer.type.ProgramType;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.dispatcher.JavaDispatcher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class ProgramTest {
    @Autowired
    ProgramRepository programRepository;

    @Test
    public void saveTest(){
        LocalDateTime localDateTime = LocalDateTime.now();

//        Member member = new Member("호석","호시기","010-1234-5678","동탄","10-18","wjdghtjr5345@naver.com",Oauth.GOOGLE);

        ProgramDTO programDTO = new ProgramDTO();
        programDTO.setProgramCrop("감자");
        programDTO.setProgramType(ProgramType.ALL_USER);
        programDTO.setProgramTarget1("프로그램 대상1");
        programDTO.setProgramTarget2("프로그램 대상2");
        programDTO.setProgramTarget3("프로그램 대상3");
        programDTO.setProgramTarget4("프로그램 대상4");
        programDTO.setProgramTitle("감자 농사!!");
        programDTO.setProgramTitleSub("재밌는 감사농사 하기!");
        programDTO.setProgramLevel(ProgramLevel.BASIC);
        programDTO.setProgramResult1("프로그램 후 결과1");
        programDTO.setProgramResult2("프로그램 후 결과2");
        programDTO.setProgramResult3("프로그램 후 결과3");
        programDTO.setProgramResult4("프로그램 후 결과4");
        programDTO.setProgramFarmerInfo("농장주 소개..............");
        programDTO.setProgramInfoTitle1("프로그램 소개 타이틀 1");
        programDTO.setProgramInfoContent1("프로그램 소개 본문 1");
        programDTO.setProgramInfoTitle2("프로그램 소개 타이틀 2");
        programDTO.setProgramInfoContent2("프로그램 소개 본문 2");
        programDTO.setProgramInfoTitle3("프로그램 소개 타이틀 3");
        programDTO.setProgramInfoContent3("프로그램 소개 본문 3");
        programDTO.setProgramInfoTitle4("프로그램 소개 타이틀 4");
        programDTO.setProgramInfoContent4("프로그램 소개 본문 4");
        programDTO.setProgramWorkDate(localDateTime);
        programDTO.setProgramWorkStartTime(localDateTime);
        programDTO.setProgramApplyStartDate(localDateTime);
        programDTO.setProgramApplyEndDate(localDateTime);
        programDTO.setProgramWorkEndTime(localDateTime);
        programDTO.setProgramApplyTotalCount(10);
        programDTO.setProgramPrice(0);
        programDTO.setProgramLocation("충남 영동");
        programDTO.setProgramInquire("wjdghtjr5345@gmail.com");
//        programDTO.setMember();

        Program program = programDTO.toEntity();
        programRepository.save(program);
    }

}
