package com.codefarm.codefarmer.repository;

import com.codefarm.codefarmer.entity.Mentor;
import com.codefarm.codefarmer.entity.MentorBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MentorBoardRepository extends JpaRepository<MentorBoard, Long> {
}
