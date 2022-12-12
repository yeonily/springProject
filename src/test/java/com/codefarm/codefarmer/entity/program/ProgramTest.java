package com.codefarm.codefarmer.entity.program;

import com.codefarm.codefarmer.domain.program.ProgramDTO;
import com.codefarm.codefarmer.entity.alba.Alba;
import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.entity.program.Program;
import com.codefarm.codefarmer.repository.member.MemberRepository;
import com.codefarm.codefarmer.repository.program.ProgramRepository;
import com.codefarm.codefarmer.type.ProgramLevel;
import com.codefarm.codefarmer.type.ProgramType;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.codefarm.codefarmer.entity.program.QProgram.program;


@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class ProgramTest {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Autowired
    ProgramRepository programRepository;

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void saveTest(){
        LocalDateTime localDateTime = LocalDateTime.now();

        Optional<Member> findMember = memberRepository.findById(27L);
        log.info("findFarmer : " + findMember.toString());
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
        programDTO.setProgramWorkStartTime( localDateTime);
        programDTO.setProgramApplyStartDate(localDateTime);
        programDTO.setProgramApplyEndDate(localDateTime);
        programDTO.setProgramWorkEndTime(localDateTime);
        programDTO.setProgramApplyTotalCount(10);
        programDTO.setProgramPrice(0);
        programDTO.setProgramLocation("충남 영동");
        programDTO.setProgramInquire("wjdghtjr5345@gmail.com");
        programDTO.setMemberId(27L);
        Program program = programDTO.toEntity();
        program.changeMember(findMember.get());
        programRepository.save(program);
    }


    @Test
    public void findProgramIdByProgramCropTest(){
//        log.info("전체 조회: " + programRepository.findAll());

        /*jpaQueryFactory.select(program.programId).from(program)
                .where(program.programCrop.eq("감자"))
                .orderBy(program.programId.desc())
                .limit(2)
                .fetch()
                .stream().map(Program -> Program.toString()).forEach(log::info);*/

        jpaQueryFactory.selectFrom(program)
                .fetch()
                .stream().map(p -> p.toString()).forEach(log::info);

    }

    @Test
    public void findProgramListByProgramId(){
        jpaQueryFactory.selectFrom(program)
                .fetch()
                .stream().map(p -> p.toString()).forEach(log::info);
    }

    @Test
    public void updateTest(){
        LocalDateTime localDateTime = LocalDateTime.now();

        Optional<Program> findMember = programRepository.findById(1L);
        log.info("findFarmer : " + findMember.toString());
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
//        programDTO.setMemberId(findMember.get().getMemberId());

//        Program program = programDTO.toEntity();
//        program.changeMember(programDTO.getMember());
        Program program = programRepository.findById(2L).get();
        program.update(programDTO);
    }

    @Test
    public void delete(){
        programRepository.deleteById(2L);

    }

//    프로그램 목록 정보들 가져오기
    @Test

    public void findListTest(){
        jpaQueryFactory.select(program.programLocation,program.programType,program.programTitle,program.programWorkStartTime,program.programPrice,program.programId)
                .from(program)
                .where(program.programId.eq(3L))
                .fetch()
                .stream().map(Program -> Program.toString()).forEach(log::info);

    }


    /*
    * 프로그램 목록 기능 테스트
    * */



//    진행중 정렬
    @Test
    public void findProceedingListTest(){
        LocalDateTime localDateTime = LocalDateTime.now();
        jpaQueryFactory.select(program.programLocation,program.programType,program.programTitle,program.programWorkStartTime,program.programPrice,program.programId)
                .from(program)
                .where(program.programWorkStartTime.before(localDateTime).and(program.programWorkEndTime.after(localDateTime)))
                .orderBy(program.programApplyStartDate.desc())
                .fetch()
                .stream().map(Program -> Program.toString()).forEach(log::info);
    }
    
//    최근등록일순 정렬
    @Test
    public void findRecentListTest(){
        jpaQueryFactory.select(program.programLocation,program.programType,program.programTitle,program.programWorkStartTime,program.programPrice,program.programId)
                .from(program)
                .orderBy(program.programApplyStartDate.desc())
                .fetch()
                .stream().map(Program -> Program.toString()).forEach(log::info);
    }

//    최근 마감일순 정렬
    @Test
    public void findOldRecenttListTest(){
        jpaQueryFactory.select(program.programLocation,program.programType,program.programTitle,program.programWorkStartTime,program.programPrice,program.programId)
                .from(program)
                .orderBy(program.programApplyStartDate.asc())
                .fetch()
                .stream().map(Program -> Program.toString()).forEach(log::info);
    }

//    멘티 전용 정렬
    @Test
    public void findProgramTypeListByOnlyMenteeTest(){
        jpaQueryFactory.select(program.programLocation,program.programType,program.programTitle,program.programWorkStartTime,program.programPrice,program.programId)
                .from(program)
                .where(program.programType.eq(ProgramType.ONLY_MENTEE))
                .fetch()
                .stream().map(Program -> Program.toString()).forEach(log::info);
    }

//    일반인용 정렬
    @Test
    public void findProgramTypeListByAllUserTest(){
        jpaQueryFactory.select(program.programLocation,program.programType,program.programTitle,program.programWorkStartTime,program.programPrice,program.programId)
                .from(program)
                .where(program.programType.eq(ProgramType.ALL_USER))
                .fetch()
                .stream().map(Program -> Program.toString()).forEach(log::info);
    }

//    유료 프로그램 정렬
    @Test
    public void findProgramListByUsePayProgramTest(){
        jpaQueryFactory.select(program.programLocation,program.programType,program.programTitle,program.programWorkStartTime,program.programPrice,program.programId)
                .from(program)
                .where(program.programPrice.gt(0))
                .fetch()
                .stream().map(Program -> Program.toString()).forEach(log::info);
    }

//    무료 프로그램 정렬
    @Test
    public void findProgramListByFreePayProgramTest(){
        jpaQueryFactory.select(program.programLocation,program.programType,program.programTitle,program.programWorkStartTime,program.programPrice,program.programId)
                .from(program)
                .where(program.programPrice.eq(0))
                .fetch()
                .stream().map(Program -> Program.toString()).forEach(log::info);
    }


    //    곧마감돼요 8개 뽑기
    @Test
    public void findTop8ByOOrderByAlbaApplyEndDateDescTest(){
        programRepository.findTop8ByOrderByProgramApplyEndDateDesc().stream().map(Program::getProgramId).forEach(a -> log.info("pg 번호 : " + a));
    }



}



