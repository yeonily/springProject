package com.codefarm.codefarmer.service.mentor;

import com.codefarm.codefarmer.domain.mentor.MentorMenteeDTO;
import com.codefarm.codefarmer.repository.mentor.MentorFileRepository;
import com.codefarm.codefarmer.repository.mentor.MentorMenteeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MentorMenteeService {
    private final MentorMenteeRepository mentorMenteeRepository;

    //멘티 아이디로 멘토정보 찾기
    public List<MentorMenteeDTO> findByMenteeId(Long menteeId){
        return mentorMenteeRepository.selectByMenteeId(menteeId);
    }

    public void removeById(Long mentorMenteeId){
        mentorMenteeRepository.deleteById(mentorMenteeId);
    }

    //멘토 아이디로 멘티정보 찾기
    public List<MentorMenteeDTO> findByMentorId(Long mentorId){
        return mentorMenteeRepository.selectByMentorId(mentorId);
    }

    //거절 상태
    @Transactional
    public String changeRejectStatus(Long mentorMenteeId) {
        mentorMenteeRepository.updateRejectStatus(mentorMenteeId);
        return "REJECT";
    }

    //수락 상태
    @Transactional
    public String changeConfirmStatus(Long mentorMenteeId) {
        mentorMenteeRepository.updateConfirmStatus(mentorMenteeId);
        return "CONFIRM";
    }
}
