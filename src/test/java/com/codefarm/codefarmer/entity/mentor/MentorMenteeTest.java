package com.codefarm.codefarmer.entity.mentor;

import com.codefarm.codefarmer.domain.mentor.MentorMenteeDTO;
import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.entity.mentor.MentorMentee;
import com.codefarm.codefarmer.repository.member.MemberRepository;
import com.codefarm.codefarmer.repository.mentor.MentorMenteeRepository;
import com.codefarm.codefarmer.type.Status;
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
    private MemberRepository memberRepository;
//    한줄 소개 작성하기
    @Test
    public void applyMenteeTest(){
//        MentorMenteeDTO mentorMenteeDTO = new MentorMenteeDTO();
//        Optional<Member> findMentor = memberRepository.findById(1L);
//
//        mentorMenteeDTO.setMenteeComment("당신한테 배우고 싶습니다. 멘티 신청합니다!");
//        mentorMenteeDTO.setMentorId(findMentor.get());
//
//        MentorMentee mentorMentee = mentorMenteeDTO.toEntity();
//        mentorMentee.changeMentor(mentorMenteeDTO.getMentorId());
//        mentorMentee.changeMentee(mentorMenteeDTO.getMenteeId());
//        mentorMenteeRepository.save(mentorMentee);
    }

    @Test
    public void deleteByIdTest(){
        mentorMenteeRepository.deleteById(1l);
    }

    @Test
    public void selectByMentorIdTest(){
        Long mentorId = 1l;
        //상태가 confirm일 때
//        mentorMenteeRepository.selectByMentorId(mentorId, Status.CONFIRM).stream().map(MentorMenteeDTO::getMenteeId).forEach(v->log.info(v+""));
        //상태가 waiting일 때
        mentorMenteeRepository.selectByMentorId(mentorId, Status.WAITING).stream().map(MentorMenteeDTO::getMenteeId).forEach(v->log.info(v+""));
        //상태가 reject일 때
//        mentorMenteeRepository.selectByMentorId(mentorId, Status.REJECT).stream().map(MentorMenteeDTO::getMenteeId).forEach(v->log.info(v+""));

    }

}
