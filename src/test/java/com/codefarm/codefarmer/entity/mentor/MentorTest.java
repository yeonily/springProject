package com.codefarm.codefarmer.entity.mentor;

import com.codefarm.codefarmer.domain.member.MemberDTO;
import com.codefarm.codefarmer.domain.mentor.MentorDTO;
import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.entity.mentor.Mentor;
import com.codefarm.codefarmer.repository.member.MemberRepository;
import com.codefarm.codefarmer.repository.mentor.MentorBoardRepository;
import com.codefarm.codefarmer.repository.mentor.MentorRepository;
import com.codefarm.codefarmer.type.MemberType;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.usertype.UserType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class MentorTest {

    @Autowired
    private MentorRepository mentorRepository;
    @Autowired
    private MentorBoardRepository mentorBoardRepository;
    @Autowired
    private MemberRepository memberRepository;


//    마이페이지에서 농작물과 경력을 입력하면 멘토신청됨(INSERT)
//    멘토를 신청하게 되면 FARMERTYPE이 FARMER에서 MENTOR로 바뀌게 된다(UPDATE).
//    현재 문제점 : Insert는 잘 되는데 MemberId에서 계속 NULL값이 나옴 TBL_MEMBER에 있는
//                MEMBER_ID를 못갖고옴

//  일반 유저 추가
@Test
public void saveUserTest(){
    MemberDTO memberDTO = new MemberDTO();
    memberDTO.setMemberType(MemberType.USER);
    memberDTO.setMemberBirth("1997-03-21");
    memberDTO.setMemberEmail("runner123@naver.com");
    memberDTO.setMemberLocation("인천");
    memberDTO.setMemberName("정호떡");
    memberDTO.setMemberNickname("호떡");
    memberDTO.setMemberPhone("010-5454-2315");
    memberDTO.setMemberOauthId("asdf");

    Member member = memberDTO.toEntity();

    memberRepository.save(member);

}

//    농장주 추가
    @Test
    public void saveFarmerTest(){

        MemberDTO farmerDTO = new MemberDTO();
        farmerDTO.setMemberType(MemberType.FARMER);
        farmerDTO.setMemberBirth("1997-04-16");
        farmerDTO.setMemberEmail("gimchi2336@naver.com");
        farmerDTO.setMemberLocation("인천");
        farmerDTO.setMemberName("서승우");
        farmerDTO.setMemberNickname("5팀장");
        farmerDTO.setMemberPhone("010-1239-4321");
        farmerDTO.setMemberOauthId("KAKAO");

        Member farmer = farmerDTO.toEntity();

        memberRepository.save(farmer);

    }


    //    유저타입만 바꾸기(멘티로)
    @Test
    public void typeUserUpdateTest(){
        Member user = memberRepository.findById(1L).get();

        MemberDTO userDTO = new MemberDTO();
        userDTO.setMemberType(MemberType.MENTEE);
        userDTO.setMemberEmail(user.getMemberEmail());
        userDTO.setMemberLocation(user.getMemberLocation());
        userDTO.setMemberNickname(user.getMemberNickname());
        userDTO.setMemberPhone(user.getMemberPhone());

        user.update(userDTO);
    }

    //    농장주타입만 바꾸기
    @Test
    public void typeFarmerUpdateTest(){
        Member farmer = memberRepository.findById(2L).get();

        MemberDTO farmerDTO = new MemberDTO();
        farmerDTO.setMemberType(MemberType.MENTOR);
        farmerDTO.setMemberEmail(farmer.getMemberEmail());
        farmerDTO.setMemberLocation(farmer.getMemberLocation());
        farmerDTO.setMemberNickname(farmer.getMemberNickname());
        farmerDTO.setMemberPhone(farmer.getMemberPhone());

        farmer.update(farmerDTO);
    }



//    멘토 신청하기(농작물, 경력) 작성
    @Test
    public void MentorSaveTest(){

//        MentorDTO mentorDTO = new MentorDTO();
//        Optional<Member> findFarmer = memberRepository.findById(61L);
//
//        mentorDTO.setMentorCrop("오렌지");
//        mentorDTO.setMentorYear("3~5년차");
//        mentorDTO.setMemberId(findFarmer.get());
//
//        Mentor mentor = mentorDTO.toEntity();
//        mentor.changeMember(mentorDTO.getMemberId());
//        mentorRepository.save(mentor);

    }


//    농작물과 경력 수정
    @Test
    public void MentorUpdateTest(){
//        Optional<Farmer> findFarmer = farmerRepository.findById(1l);
        Optional<Mentor> findMentor = mentorRepository.findById(1L);
        MentorDTO mentorDTO = new MentorDTO();

//        findMentor.get().getMentorId();
        mentorDTO.setMentorId(findMentor.get().getMentorId());
        mentorDTO.setMentorCrop("딸기");
        mentorDTO.setMentorYear("5~9년차");


        Mentor mentor = mentorDTO.toEntity();


        mentor.update(mentorDTO);
    }




//        @Test
//        public void Test(){
////        log.info("무엇을 갖고오는지 = " + farmerRepository.findAll().set(1, farmerRepository.getReferenceById(6L)));
//            Optional<Farmer> findMember = farmerRepository.findById(6L);
//            log.info("여기엔 뭐가 있지" + findMember);
//        }


//        멘토 상세보기에서 오른쪽 fix박스 내용 갖고오기(멘토이름, 주요작물, 경력, 지역)
    @Test
    public void findCropTest(){
        Optional<Member> findLocation = memberRepository.findById(1L);
        Optional<Mentor> findMentor = mentorRepository.findById(10L);
         log.info("멘토이름 : " + findMentor.get().getMember().getMemberName());
         log.info("주요작물 : " + findMentor.get().getMentorCrop());
         log.info("경력 : " + findMentor.get().getMentorYear());
         log.info("지역 정보 : " + findLocation.get().getMemberLocation());
    }

    public void findLocationTest(){
        Optional<Member> findLocation = memberRepository.findById(1L);
        log.info("지역 정보 : " + findLocation.get().getMemberLocation());
    }



//    멘토신청 취소
    @Test
    public void MentorDeleteTest(){
        mentorRepository.deleteById(3L);
    }
}