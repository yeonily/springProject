package com.codefarm.codefarmer.repository;

import com.codefarm.codefarmer.entity.Farmer;
import com.codefarm.codefarmer.entity.MentorMentee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MentorMenteeRepository extends JpaRepository<MentorMentee, Long> {
}
