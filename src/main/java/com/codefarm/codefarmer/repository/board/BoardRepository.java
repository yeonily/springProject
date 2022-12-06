package com.codefarm.codefarmer.repository.board;

import com.codefarm.codefarmer.domain.board.BoardDTO;
import com.codefarm.codefarmer.entity.board.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

//    사용자가 작상한 게시글 총 개수
    public Long countByMemberMemberId(@Param("boardId") Long boardId);

//  보드 목록
    public Page<BoardDTO> findAllByBoardId(Long boardId, Pageable pageable);


}
