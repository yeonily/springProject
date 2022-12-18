package com.codefarm.codefarmer.service.Mentor;

import com.codefarm.codefarmer.domain.mentor.MentorMenteeDTO;
import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.entity.mentor.MentorMentee;
import com.codefarm.codefarmer.repository.member.MemberRepository;
import com.codefarm.codefarmer.repository.mentor.MentorCustomRepository;
import com.codefarm.codefarmer.repository.mentor.MentorMenteeRepository;
import com.codefarm.codefarmer.service.mentor.MentorMenteeApplyService;
import com.codefarm.codefarmer.service.mentor.MentorMenteeService;
import com.codefarm.codefarmer.type.Status;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.Optional;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class MentorMenteeServiceTest {

    @Autowired
    private MentorMenteeService mentorMenteeService;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MentorMenteeApplyService mentorMenteeApplyService;

    @Test
    public void removeByIdTest(){
        Long mentorMenteeId = 1l;
        mentorMenteeService.removeById(mentorMenteeId);
    }

    @Test
    public void menteeApplyTest(){
        MentorMenteeDTO mentorMenteeDTO =  new MentorMenteeDTO();
        Optional<Member> findMentee = memberRepository.findById(30L);
        Optional<Member> findMentor = memberRepository.findById(1L);

        mentorMenteeDTO.setMenteeComment("나 너의 멘티가 될래");
//        mentorMenteeDTO.setMenteeStatus(Status.WAITING);
        mentorMenteeDTO.setMenteeId(findMentee.get().getMemberId());
        mentorMenteeDTO.setMentorId(findMentor.get().getMemberId());
        mentorMenteeApplyService.saveMenteeApply(mentorMenteeDTO);
    }


}
