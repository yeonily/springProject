package com.codefarm.codefarmer.service.mentor;

import com.codefarm.codefarmer.domain.mentor.MentorMenteeDTO;
import com.codefarm.codefarmer.entity.mentor.MentorMentee;
import com.codefarm.codefarmer.repository.member.MemberRepository;
import com.codefarm.codefarmer.repository.mentor.MentorMenteeRepository;
import com.codefarm.codefarmer.repository.mentor.MentorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MentorMenteeApplyService {

    private final MentorMenteeRepository mentorMenteeRepository;
    private final MemberRepository memberRepository;
    private final MentorRepository mentorRepository;

//    일반유저가 멘토한테 멘티 신청하기
    public void saveMenteeApply(MentorMenteeDTO mentorMenteeDTO){
         MentorMentee mentorMentee = mentorMenteeDTO.toEntity();
         mentorMentee.changeMentee(memberRepository.findById(mentorMenteeDTO.getMenteeId()).get());
         mentorMentee.changeMentor(memberRepository.findById(mentorMenteeDTO.getMentorId()).get());
         mentorMenteeRepository.save(mentorMentee);
    }

}
