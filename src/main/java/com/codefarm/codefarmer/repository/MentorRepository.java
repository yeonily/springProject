package com.codefarm.codefarmer.repository;

import com.codefarm.codefarmer.entity.MemberAlba;
import com.codefarm.codefarmer.entity.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MentorRepository extends JpaRepository<Mentor, Long> {
}
