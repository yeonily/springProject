package com.codefarm.codefarmer.entity.admin;

import com.codefarm.codefarmer.domain.alba.AlbaDTO;
import com.codefarm.codefarmer.domain.mentor.MentorDTO;
import com.codefarm.codefarmer.domain.program.MemberProgramDTO;
import com.codefarm.codefarmer.domain.program.ProgramDTO;
import com.codefarm.codefarmer.entity.alba.Alba;
import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.entity.program.MemberProgram;
import com.codefarm.codefarmer.entity.program.Program;
import com.codefarm.codefarmer.repository.alba.AlbaRepository;
import com.codefarm.codefarmer.repository.alba.MemberAlbaRepository;
import com.codefarm.codefarmer.repository.member.MemberRepository;
import com.codefarm.codefarmer.repository.mentor.MentorCustomRepository;
import com.codefarm.codefarmer.repository.mentor.MentorMenteeRepository;
import com.codefarm.codefarmer.repository.mentor.MentorRepository;
import com.codefarm.codefarmer.repository.program.MemberProgramRepository;
import com.codefarm.codefarmer.repository.program.ProgramRepository;
import com.codefarm.codefarmer.type.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class AdminTest {
    @Autowired
    private JPAQueryFactory jpaQueryFacto;
    @Autowired
    private MemberRepository memberRepository;


    @Autowired
    private ProgramRepository programRepository;
    @Autowired
    private MemberProgramRepository memberProgramRepository;

    @Autowired
    private AlbaRepository albaRepository;
    @Autowired
    private MemberAlbaRepository memberAlbaRepository;
    @Autowired
    private MentorRepository mentorRepository;
    private MentorCustomRepository mentorCustomRepository;
    private MentorMenteeRepository mentorMenteeRepository;

//    유저 등록


//    유저 삭제
    @Test
    public void memberDeleteTest(){

    }

//    모든 유저 출력
    @Test
    public void memberSelectAllTest(){

    }

//    총 유저 인원 수

//    ========================================================================================================

//    프로그램 글 등록
    @Test
    public void programSaveTest(){
        LocalDateTime localDateTime = LocalDateTime.now();

        ProgramDTO programDTO = new ProgramDTO();
        Optional<Member> findMember = memberRepository.findById(10L);

        programDTO.setMemberId(findMember.get().getMemberId());
        programDTO.setProgramCrop("블루베리");
        programDTO.setProgramType(ProgramType.ALL_USER);
        programDTO.setProgramTarget1("대상1");
        programDTO.setProgramTarget2("대상2");
        programDTO.setProgramTarget3("대상3");
        programDTO.setProgramTarget4("대상4");
        programDTO.setProgramTitle("초보자도 쉽게 할 수 있는 블루베리 농사");
        programDTO.setProgramTitleSub("유기농 블루베리 키워서 먹어보자!");
        programDTO.setProgramLevel(ProgramLevel.BASIC);
        programDTO.setProgramResult1("프로그램 후 결과1");
        programDTO.setProgramResult2("프로그램 후 결과2");
        programDTO.setProgramResult3("프로그램 후 결과3");
        programDTO.setProgramResult4("프로그램 후 결과4");
        programDTO.setProgramFarmerInfo("농장주 소개임ㅋㅋ");
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
        programDTO.setProgramLocation("경기도 성남시");
        programDTO.setProgramInquire(findMember.get().getMemberEmail());

        Program program = programDTO.toEntity();
//        program.changeMember(programDTO.getMemberId().);
        programRepository.save(program);
    }

//    프로그램 삭제
    @Test
    public void programDeleteTest() {
        programRepository.delete(programRepository.findById(17L).get());
    }

//    프로그램 목록
    @Test
    public void programSelectAllTest(){
//        programRepository.findAll().stream().map(program -> program.toString()).forEach(log::info);
    }

//    ========================================================================================================

//    프로그램 신청
    @Test
    public void memberProgramSaveTest(){
        MemberProgramDTO memberProgramDTO = new MemberProgramDTO();
        Optional<Member> findMember = memberRepository.findById(11L);
        Optional<Program> findProgram = programRepository.findById(16L);

        memberProgramDTO.setMember(findMember.get());
        memberProgramDTO.setProgram(findProgram.get());
        memberProgramDTO.setProgramApplyCount(2);
        int totalCount = findProgram.get().getProgramPrice() * memberProgramDTO.getProgramApplyCount();
        memberProgramDTO.setProgramPayment(totalCount);
        memberProgramDTO.setProgramStatus(ProgramStatus.PAY_SUCCEED);

        MemberProgram memberProgram = memberProgramDTO.toEntity();
        memberProgram.changeMember(memberProgramDTO.getMember());
        memberProgram.changeProgram(memberProgramDTO.getProgram());

        memberProgramRepository.save(memberProgram);
    }

//    프로그램 신청 취소 - ***미완료
    public void memberProgramCancelTest(){
        Optional<MemberProgram> memberProgram = memberProgramRepository.findById(26L);
    }

//    프로그램 지원자 목록
    @Test
    public void memberProgramSelectAllTest(){
//        memberProgramRepository.findAll().stream().map(memberProgram -> memberProgram.toString()).forEach(log::info);

    }

//    ========================================================================================================

//    아르바이트 등록
    @Test
    public void albaSaveTest(){
        LocalDateTime localDateTime = LocalDateTime.now();

        AlbaDTO albaDTO = new AlbaDTO();
        Optional<Member> findMember = memberRepository.findById(10L);

        albaDTO.setAlbaAddress("서울시 관악구");
        albaDTO.setAlbaApplyCount(123);
        albaDTO.setAlbaApplyEndDate(LocalDateTime.of(2010,3,24,0,0));
        albaDTO.setAlbaApplyStartDate(LocalDateTime.of(2011,3,24,0,0));
        albaDTO.setAlbaApplyTotalCount(1234);
        albaDTO.setAlbaBannerOne("연태관 배너");
        albaDTO.setAlbaBannerTitle("연태관 배너 제목");
        albaDTO.setAlbaImage("연태관 이미지");
        albaDTO.setAlbaMainContent("연태관 메인컨텐트");
        albaDTO.setAlbaMainTitle("연태관 메인제목123");
        albaDTO.setAlbaPrice(10_200);
        albaDTO.setAlbaProfileContent1("연태관 알바프로필내용1");
        albaDTO.setAlbaProfileContent2("연태관 알바프로필내용2");
        albaDTO.setAlbaProfileTitle1("연태관 알바프로필제목1");
        albaDTO.setAlbaProfileTitle2("연태관 알바프로필제목2");
        albaDTO.setAlbaStrongContent1("연태관 알바소개내용1");
        albaDTO.setAlbaStrongContent2("연태관 알바소개내용2");
        albaDTO.setAlbaStrongContent3("연태관 알바소개내용3");
        albaDTO.setAlbaStrongTitle1("연태관 알바소개제목1");
        albaDTO.setAlbaStrongTitle2("연태관 알바소개제목1");
        albaDTO.setAlbaStrongTitle3("연태관 알바소개제목1");
        albaDTO.setAlbaText("연태관 알바문자");
        albaDTO.setAlbaTextTitle("연태관 알바문자제목");
        albaDTO.setAlbaTitle("연태관 알바제목");
        albaDTO.setAlbaTitleOne("연태관 알바제목원");
        albaDTO.setAlbaWorkDate(localDateTime);
        albaDTO.setMember(findMember.get());

        Alba alba = albaDTO.toEntity();
        alba.changeMember(albaDTO.getMember());
        albaRepository.save(alba);
    }

//    아르바이트 목록
    @Test
    public void albaSelectAllTest(){
        albaRepository.findAll().stream().map(Alba -> Alba.toString()).forEach(log::info);

    }

//    아르바이트 삭제
    @Test
    public void albaDeleteTest(){
        albaRepository.delete(albaRepository.findById(31L).get());
    }


// 멘토
    @Test
    public void mentorTest() {
        String keyword = "nn";
        String searchText = "";
//        List<MentorDTO> mentors = mentorCustomRepository.ShowAllMentor(keyword, searchText);
//
//        mentors.stream().map(mentorDTO -> mentorDTO.getMemberNickname()).forEach(m -> log.info("" + m));

    }

    @Test
    public void menteeTest(){
        mentorMenteeRepository.findByAdminMentee(21L).stream().forEach(m -> log.info("멘티 → " + m));

    }

}
