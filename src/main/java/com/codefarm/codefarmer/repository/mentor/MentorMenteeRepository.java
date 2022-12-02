package com.codefarm.codefarmer.repository.mentor;

import com.codefarm.codefarmer.entity.mentor.MentorMentee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MentorMenteeRepository extends JpaRepository<MentorMentee, Long> {
}
