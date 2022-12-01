package com.codefarm.codefarmer.repository;

import com.codefarm.codefarmer.entity.Banner;
import com.codefarm.codefarmer.entity.Board;
import com.codefarm.codefarmer.entity.QBoard;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.*;
import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {

//    사용자가 작상한 게시글 총 개수
    public Long countByMemberMemberId(@Param("boardId") Long MemberId);

}
