package com.codefarm.codefarmer.repository.mentor;

import com.codefarm.codefarmer.domain.mentor.MentorBoardDTO;
import com.codefarm.codefarmer.entity.mentor.Mentor;
import com.codefarm.codefarmer.entity.mentor.MentorBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MentorBoardRepository extends JpaRepository<MentorBoard, Long> {

    public Page<MentorBoardDTO> findAllByMentorBoardId(Long mentorBoardId, Pageable pageable);
}
