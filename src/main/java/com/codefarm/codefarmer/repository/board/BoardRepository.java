package com.codefarm.codefarmer.repository.board;

import com.codefarm.codefarmer.domain.board.BoardDTO;
import com.codefarm.codefarmer.entity.board.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
//    검색(제목+내용) + 페이징
    public Page<Board> findByBoardTitleContainingOrBoardContentContaining(String boardTitle, String boardContent, Pageable pageable);
//    검색(제목) + 페이징
    public Page<Board> findByBoardTitleContaining(String boardTitle, Pageable pageable);
//    검색(내용) + 페이징
    public Page<Board> findByBoardContentContaining(String boardContent, Pageable pageable);

//    게시판 글 총 개수
    @Query("select count(b) from Board b")
    public int countByBoard();

//    사용자가 작상한 게시글 총 개수
    public Long countByMemberMemberId(@Param("boardId") Long boardId);

//  보드 목록
    public Page<BoardDTO> findAllByBoardId(Long boardId, Pageable pageable);

//   목록에서 보드 클릭 시 viewCount + 1
    @Transactional
    @Modifying
    @Query("update Board b set b.boardViewCount = b.boardViewCount+1 where b.boardId in :boardId")
    public void updateViewCount(@Param("boardId") Long boardId);

}
