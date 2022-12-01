package com.codefarm.codefarmer.service;

import com.codefarm.codefarmer.domain.BannerDTO;
import com.codefarm.codefarmer.domain.BoardDTO;
import com.codefarm.codefarmer.domain.QBoardDTO;
import com.codefarm.codefarmer.entity.Board;
import com.codefarm.codefarmer.entity.QBoard;
import com.codefarm.codefarmer.entity.Reply;
import com.codefarm.codefarmer.entity.Review;
import com.codefarm.codefarmer.repository.BoardRepository;
import com.codefarm.codefarmer.repository.ReplyRepository;
import com.codefarm.codefarmer.repository.ReviewRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.codefarm.codefarmer.entity.QBoard.board;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final JPAQueryFactory jpaQueryFactory;
    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;

//    게시글 제목, 내용 작성
    public void boardAdd(BoardDTO boardDTO){
        Board board = boardDTO.toEntity();
        board.changeMember(boardDTO.getMember());
        boardRepository.save(board);
    }

//    게시글 제목, 내용 수정
    public void boardUpdate(BoardDTO boardDTO){
        Board board = boardRepository.findById(boardDTO.getBoardId()).get();
        board.update(boardDTO);
    }

//    게시글 제목, 내용 상세페이지에서 보기
    public BoardDTO boardShowDetail(Long boardNumber){
        Board board = boardRepository.findById(boardNumber).get();

        BoardDTO boardDTO = new BoardDTO();


        boardDTO.setBoardTitle(board.getBoardTitle());
        boardDTO.setBoardContent(board.getBoardContent());
//        boardDTO.setMemberNickName(board.getMember().getMemberNickname());

        return boardDTO;
    }

//    내가 게시한 게시글 총 개수
    public Long showBoardCountMine(Long memberId){
        return boardRepository.countByMemberMemberId(memberId);
    }

//  해당보드 viewCount 갖고오기
    public int showViewCount(Long boardId){
         Board board = boardRepository.findById(boardId).get();

         return board.getBoardViewCount();
    }

//  해당 보드 댓글 총 수 갖고오기
    public Long showBoardReplyCount(Long boardId){
        return replyRepository.countByBoard_BoardId(boardId);
    }

//    보드 목록 갖고오기
//    public List<BoardDTO> showBoardList(){
//         return jpaQueryFactory.select(new QBoardDTO(
//                 board.boardId,
//                 board.boardTitle,
//                 board.boardContent,
//                 board.boardViewCount,
//                 board.createdDate,
//                 board.updatedDate
//                 )).from(board)
//                 .orderBy(board.createdDate.desc())
//                 .fetch();
//    }


//    게시판 지우기
    public void removeBoard(Long boardId){
        boardRepository.deleteById(boardId);
    }

}
