package com.codefarm.codefarmer.repository.mentor;

import com.codefarm.codefarmer.domain.mentor.MentorBoardDTO;
import com.codefarm.codefarmer.entity.mentor.Mentor;
import com.codefarm.codefarmer.entity.mentor.MentorBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MentorBoardRepository extends JpaRepository<MentorBoard, Long>, MentorBoardCustomRepository{

    public Page<MentorBoardDTO> findAllByMentorBoardId(Long mentorBoardId, Pageable pageable);

//    멘토 홍보 글 총 개수
    @Query("select count(m) from MentorBoard m")
    public int countByMentorBoard();
}
