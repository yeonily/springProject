package com.codefarm.codefarmer.entity;

import com.codefarm.codefarmer.domain.FarmerDTO;
import com.codefarm.codefarmer.domain.MemberProgramDTO;
import com.codefarm.codefarmer.domain.ProgramDTO;
import com.codefarm.codefarmer.domain.UserDTO;
import com.codefarm.codefarmer.repository.*;
import com.codefarm.codefarmer.type.*;
import lombok.extern.slf4j.Slf4j;
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
public class AdminTest {
    @Autowired
    private FarmerRepository farmerRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ProgramRepository programRepository;
    @Autowired
    private MemberProgramRepository memberProgramRepository;

//    농장주 유저 등록
    @Test
    public void farmerSaveTest(){
        FarmerDTO farmerDTO = new FarmerDTO();

        farmerDTO.setMemberBirth("2000-03-10");
        farmerDTO.setMemberEmail("spring@spring.java");
        farmerDTO.setFarmerType(FarmerType.FARMER);
        farmerDTO.setMemberLocation("서울시 송파구");
        farmerDTO.setMemberName("박수현");
        farmerDTO.setMemberNickname("주어머거");
        farmerDTO.setMemberOauth(Oauth.NAVER);
        farmerDTO.setMemberPhone("010-0000-0000");

        Farmer farmer = farmerDTO.toEntity();
        farmerRepository.save(farmer);
    }

//    일반 유저 등록
    @Test
    public void userSaveTest(){
        UserDTO userDTO = new UserDTO();

        userDTO.setMemberBirth("2000-03-13");
        userDTO.setMemberEmail("spring@spring.java");
        userDTO.setUserType(UserType.USER);
        userDTO.setMemberLocation("서울시 강남구");
        userDTO.setMemberName("홍길동");
        userDTO.setMemberNickname("홍홍홍22");
        userDTO.setMemberOauth(Oauth.GOOGLE);
        userDTO.setMemberPhone("010-1111-1111");

        User user = userDTO.toEntity();
        userRepository.save(user);
    }

//    유저 삭제
    @Test
    public void memberDeleteTest(){
        memberRepository.delete(memberRepository.findById(13L).get());
    }

//    모든 유저 출력
    @Test
    public void memberSelectAllTest(){
        memberRepository.findAll().stream().map(member -> member.toString()).forEach(log::info);
    }

//    총 유저 인원 수
    @Test
    public void memberCountTest(){
        log.info("member count : " + memberRepository.count());
    }

//    ========================================================================================================

//    프로그램 글 등록
    @Test
    public void programSaveTest(){
        LocalDateTime localDateTime = LocalDateTime.now();

        ProgramDTO programDTO = new ProgramDTO();
        Optional<Farmer> findFarmer = farmerRepository.findById(10L);

        programDTO.setMember(findFarmer.get());
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
        programDTO.setProgramInquire(findFarmer.get().getMemberEmail());

        Program program = programDTO.toEntity();
        program.changeMember(programDTO.getMember());
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
        programRepository.findAll().stream().map(program -> program.toString()).forEach(log::info);
    }

//    ========================================================================================================

//    프로그램 신청
    @Test
    public void memberProgramSaveTest(){
        MemberProgramDTO memberProgramDTO = new MemberProgramDTO();
        Optional<User> findUser = userRepository.findById(11L);
        Optional<Program> findProgram = programRepository.findById(16L);

        memberProgramDTO.setMember(findUser.get());
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

//    프로그램 신청 취소
    public void memberProgramCancelTest(){
        Optional<MemberProgram> memberProgram = memberProgramRepository.findById(26L);

    }


//    프로그램 지원자 목록
    @Test
    public void memberProgramSelectAll(){
        memberProgramRepository.findAll().stream().map(memberProgram -> memberProgram.toString()).forEach(log::info);
    }




}
