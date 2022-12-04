package com.codefarm.codefarmer.service.board;

import com.codefarm.codefarmer.domain.board.BoardDTO;
import com.codefarm.codefarmer.domain.board.QBoardDTO;
import com.codefarm.codefarmer.entity.board.Board;
import com.codefarm.codefarmer.entity.board.QBoard;
import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.repository.board.BoardRepository;
import com.codefarm.codefarmer.repository.board.ReplyRepository;
import com.codefarm.codefarmer.repository.member.FarmerRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.codefarm.codefarmer.entity.board.QBoard.board;


@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {
    private final JPAQueryFactory jpaQueryFactory;
    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;

//    게시글 제목, 내용 작성
    public void boardAdd(BoardDTO boardDTO){
        Board board = boardDTO.toEntity();
        board.changeMember(boardDTO.getMember());
        log.info(board.toString());
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
        boardDTO.setMemberNickName(board.getMember().getMemberNickname());
        boardDTO.setCreatedDate(board.getCreatedDate());
        boardDTO.setUpdateDate(board.getUpdatedDate());

        return boardDTO;
    }

//    내가 작성한 게시글 총 개수
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
    public List<BoardDTO> getBoardList(){
         return jpaQueryFactory.select(new QBoardDTO(
                 board.boardId,
                 board.boardTitle,
                 board.boardContent,
                 board.boardViewCount,
                 board.member.memberNickname,
                 board.createdDate,
                 board.updatedDate
                 )).from(board)
                 .orderBy(board.createdDate.desc())
                 .fetch();
    }





//    게시판 지우기
    public void removeBoard(Long boardId){
        boardRepository.deleteById(boardId);
    }

}
