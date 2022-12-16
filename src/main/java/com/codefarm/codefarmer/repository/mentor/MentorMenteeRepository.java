package com.codefarm.codefarmer.repository.mentor;

import com.codefarm.codefarmer.entity.mentor.MentorMentee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface MentorMenteeRepository extends JpaRepository<MentorMentee, Long>, MentorMenteeCustomRepository {

    //거절 상태 변경
    @Transactional
    @Modifying
    @Query("update MentorMentee m set m.menteeStatus = 'REJECT' where m.mentorMenteeId in :mentorMenteeId")
    void updateRejectStatus(Long mentorMenteeId);

    //수락 상태 변경
    @Transactional
    @Modifying
    @Query("update MentorMentee m set m.menteeStatus = 'CONFIRM' where m.mentorMenteeId in :mentorMenteeId")
    void updateConfirmStatus(Long mentorMenteeId);



}
