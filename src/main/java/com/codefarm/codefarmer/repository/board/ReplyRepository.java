package com.codefarm.codefarmer.repository.board;

import com.codefarm.codefarmer.domain.board.ReplyDTO;
import com.codefarm.codefarmer.entity.board.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
//    public Long countByReplyId(@Param("memberId") Long memberId);
   //사용자가 입력했던 총 댓글 수
    public Long countByMemberMemberId(@Param("replyId") Long memberId);
    // 해당 보드에 달려있는 총 댓글 수
    public Long countByBoard_BoardId(@Param("replyId") Long replyId);

    public Long countAllByBoardBoardId(@Param("boardId") Long boardId);

    // 게시판 번호로 댓글 카운트
    @Query("select count(r.replyId) from Reply r where r.board.boardId = :boardId")
    public int countReplyByBoardId(Long boardId);

    // 문의 글 개수
    @Query("select count(r) from Reply r")
    public int countByReply();

    // 검색(내용) + 페이징
    public Page<Reply> findByReplyContentContaining(String replyContent, Pageable pageable);

}
