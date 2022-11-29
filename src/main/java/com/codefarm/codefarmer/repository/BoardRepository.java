package com.codefarm.codefarmer.repository;

import com.codefarm.codefarmer.entity.Banner;
import com.codefarm.codefarmer.entity.Board;
import com.codefarm.codefarmer.entity.QBoard;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.*;
import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {


    @Query("select b from Board b join fetch b.member")
    public List<Board> findByBoardIdAfterAndMemberNickname();

    @Query("select b from Board b join fetch b.member")
    public List<Board> findAllByBoardTitleAndBoardContent();

}
