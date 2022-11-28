package com.codefarm.codefarmer.entity;

import com.codefarm.codefarmer.domain.ProgramDTO;
import com.codefarm.codefarmer.repository.FarmerRepository;
import com.codefarm.codefarmer.repository.MemberProgramRepository;
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
import java.util.Optional;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class ProgramTest {
    @Autowired
    ProgramRepository programRepository;

    @Autowired
    MemberProgramRepository memberProgramRepository;

    @Autowired
    FarmerRepository farmerRepository;

    @Test
    public void findAllTest(){
        log.info("전체 조회: " + programRepository.findAll());
    }

    @Test
    public void saveTest(){
        LocalDateTime localDateTime = LocalDateTime.now();

        Optional<Farmer> findFarmer = farmerRepository.findById(1L);
        log.info("findFarmer : " + findFarmer.toString());
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
        programDTO.setMember(findFarmer.get());

        Program program = programDTO.toEntity();
        program.changeMember(programDTO.getMember());
        programRepository.save(program);
    }

    @Test
    public void updateTest(){
        LocalDateTime localDateTime = LocalDateTime.now();

        Optional<Farmer> findFarmer = farmerRepository.findById(1L);
        log.info("findFarmer : " + findFarmer.toString());
        ProgramDTO programDTO = new ProgramDTO();

        programDTO.setProgramCrop("고구마");
        programDTO.setProgramType(ProgramType.ALL_USER);
        programDTO.setProgramTarget1("프로그램 대상5");
        programDTO.setProgramTarget2("프로그램 대상6");
        programDTO.setProgramTarget3("프로그램 대상7");
        programDTO.setProgramTarget4("프로그램 대상8");
        programDTO.setProgramTitle("고구마 농사!!");
        programDTO.setProgramTitleSub("재밌는 고구마농사 하기!");
        programDTO.setProgramLevel(ProgramLevel.BASIC);
        programDTO.setProgramResult1("프로그램 후 결과5");
        programDTO.setProgramResult2("프로그램 후 결과6");
        programDTO.setProgramResult3("프로그램 후 결과7");
        programDTO.setProgramResult4("프로그램 후 결과8");
        programDTO.setProgramFarmerInfo("농장주 소개..............2");
        programDTO.setProgramInfoTitle1("프로그램 소개 타이틀 5");
        programDTO.setProgramInfoContent1("프로그램 소개 본문 5");
        programDTO.setProgramInfoTitle2("프로그램 소개 타이틀 6");
        programDTO.setProgramInfoContent2("프로그램 소개 본문 6");
        programDTO.setProgramInfoTitle3("프로그램 소개 타이틀 7");
        programDTO.setProgramInfoContent3("프로그램 소개 본문 7");
        programDTO.setProgramInfoTitle4("프로그램 소개 타이틀 8");
        programDTO.setProgramInfoContent4("프로그램 소개 본문 9");
        programDTO.setProgramWorkDate(localDateTime);
        programDTO.setProgramWorkStartTime(localDateTime);
        programDTO.setProgramApplyStartDate(localDateTime);
        programDTO.setProgramApplyEndDate(localDateTime);
        programDTO.setProgramWorkEndTime(localDateTime);
        programDTO.setProgramApplyTotalCount(20);
        programDTO.setProgramPrice(0);
        programDTO.setProgramLocation("경기 동탄");
        programDTO.setProgramInquire("wjdghtjr5345@gmail.com2");
        programDTO.setMember(findFarmer.get());

//        Program program = programDTO.toEntity();
//        program.changeMember(programDTO.getMember());
        Program program = programRepository.findById(2L).get();
        program.update(programDTO);
    }

    @Test
    public void delete(){
        programRepository.deleteById(2L);

    }

}
