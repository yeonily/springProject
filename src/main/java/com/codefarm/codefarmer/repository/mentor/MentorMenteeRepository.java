package com.codefarm.codefarmer.repository.mentor;

import com.codefarm.codefarmer.entity.mentor.MentorMentee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MentorMenteeRepository extends JpaRepository<MentorMentee, Long>, MentorMenteeCustomRepository {


}
