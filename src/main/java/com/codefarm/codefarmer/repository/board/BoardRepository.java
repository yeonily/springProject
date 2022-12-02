package com.codefarm.codefarmer.repository.board;

import com.codefarm.codefarmer.entity.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<Board, Long> {

//    사용자가 작상한 게시글 총 개수
    public Long countByMemberMemberId(@Param("boardId") Long boardId);

}
