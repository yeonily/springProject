package com.codefarm.codefarmer.repository.mentor;

import com.codefarm.codefarmer.entity.mentor.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MentorRepository extends JpaRepository<Mentor, Long> {

//    게시판 글 총 개수
    @Query("select count(m) from Mentor m")
    public int countByMentor();
}
