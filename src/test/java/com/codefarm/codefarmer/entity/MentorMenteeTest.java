package com.codefarm.codefarmer.entity;

import com.codefarm.codefarmer.domain.MentorMenteeDTO;
import com.codefarm.codefarmer.repository.FarmerRepository;
import com.codefarm.codefarmer.repository.MentorMenteeRepository;
import com.codefarm.codefarmer.repository.UserRepository;
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
public class MentorMenteeTest {

    @Autowired
    private MentorMenteeRepository mentorMenteeRepository;
    @Autowired
    private FarmerRepository farmerRepository;
    @Autowired
    private UserRepository userRepository;

//    한줄 소개 작성하기
    @Test
    public void applyMenteeTest(){
        MentorMenteeDTO mentorMenteeDTO = new MentorMenteeDTO();
        Optional<Farmer> findMentor = farmerRepository.findById(1L);
        Optional<User> findMentee = userRepository.findById(15L);

        mentorMenteeDTO.setMenteeComment("당신한테 배우고 싶습니다. 멘티 신청합니다!");
        mentorMenteeDTO.setMentorId(findMentor.get());
        mentorMenteeDTO.setMenteeId(findMentee.get());

        MentorMentee mentorMentee = mentorMenteeDTO.toEntity();
        mentorMentee.changeMentor(mentorMenteeDTO.getMentorId());
        mentorMentee.changeMentee(mentorMenteeDTO.getMenteeId());
        mentorMenteeRepository.save(mentorMentee);
    }



}
