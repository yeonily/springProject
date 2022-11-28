package com.codefarm.codefarmer.entity;

import com.codefarm.codefarmer.domain.FarmerDTO;
import com.codefarm.codefarmer.domain.MentorDTO;
import com.codefarm.codefarmer.repository.FarmerRepository;
import com.codefarm.codefarmer.repository.MentorRepository;
import com.codefarm.codefarmer.type.FarmerType;
import lombok.extern.slf4j.Slf4j;
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
    private FarmerRepository farmerRepository;

//    마이페이지에서 농작물과 경력을 입력하면 멘토신청됨(INSERT)
//    멘토를 신청하게 되면 FARMERTYPE이 FARMER에서 MENTOR로 바뀌게 된다(UPDATE).
//    현재 문제점 : Insert는 잘 되는데 MemberId에서 계속 NULL값이 나옴 TBL_MEMBER에 있는
//                MEMBER_ID를 못갖고옴


//    멘토 신청하기(농작물, 경력) 작성
    @Test
    public void MentorSaveTest(){
        MentorDTO mentorDTO = new MentorDTO();
        Member member = farmerRepository.findById(1l).get();
//        mentorDTO.getMember(member);
        mentorDTO.setMentorCrop("땅콩");
        mentorDTO.setMentorYear("1~3년차");

        Mentor mentor = mentorDTO.toEntity();

        mentorRepository.save(mentor);
    }


//    농작물과 경력 수정
    @Test
    public void MentorUpdateTest(){
        Mentor mentor = mentorRepository.findById(4L).get();
        MentorDTO mentorDTO = new MentorDTO();

        mentorDTO.setMentorCrop("딸기");
        mentorDTO.setMentorYear("5~9년차");


        mentor.update(mentorDTO);
    }

//    FARMERTYPE을 MENTOR로 바꿔야함.
    @Test
    public void MentorUpdateTest2(){
        Farmer farmer = farmerRepository.findById(6l).get();
        FarmerDTO farmerDTO = new FarmerDTO();
        farmerDTO.setFarmerType(FarmerType.MENTOR);

        farmer.update(farmerDTO);
    }

        @Test
        public void Test(){
//        log.info("무엇을 갖고오는지 = " + farmerRepository.findAll().set(1, farmerRepository.getReferenceById(6L)));
            Optional<Farmer> findMember = farmerRepository.findById(6L);
            log.info("여기엔 뭐가 있지" + findMember);
        }


//    멘토신청 취소
    @Test
    public void MentorDeleteTest(){
        mentorRepository.deleteById(3L);
    }
}