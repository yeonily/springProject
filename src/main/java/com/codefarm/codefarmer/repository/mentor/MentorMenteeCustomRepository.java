package com.codefarm.codefarmer.repository.mentor;

import com.codefarm.codefarmer.domain.mentor.MentorMenteeDTO;
import com.codefarm.codefarmer.entity.mentor.MentorMentee;
import com.codefarm.codefarmer.type.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MentorMenteeCustomRepository {
    //멘티아이디로 멘토정보 찾기
    public List<MentorMenteeDTO> selectByMenteeId(Long menteeId);
    //멘토아이디로 멘티정보 찾기
    public List<MentorMenteeDTO> selectByMentorId(Long mentorId, Status status);

    //관리자
    public List<MentorMenteeDTO> findByAdminMentee(Long mentorId);
}
