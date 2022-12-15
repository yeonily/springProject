package com.codefarm.codefarmer.repository.mentor;

import com.codefarm.codefarmer.entity.mentor.MentorFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MentorFileRepository extends JpaRepository<MentorFile, Long> {
    public List<MentorFile> findByMentorBoard_MentorBoardId(Long mentorBoardId);
}
