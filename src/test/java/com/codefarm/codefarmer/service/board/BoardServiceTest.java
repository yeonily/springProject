package com.codefarm.codefarmer.service.board;


import com.codefarm.codefarmer.domain.board.BoardDTO;
import com.codefarm.codefarmer.entity.board.Board;
import com.codefarm.codefarmer.entity.member.Farmer;
import com.codefarm.codefarmer.repository.board.BoardRepository;
import com.codefarm.codefarmer.repository.member.FarmerRepository;
import com.codefarm.codefarmer.service.board.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class BoardServiceTest {

    @Autowired
    private BoardService boardService;

    @Autowired
    private FarmerRepository farmerRepository;

    @Autowired
    private BoardRepository boardRepository;

//    게시판 제목, 내용 작성
    @Test
    public void addTest(){
        BoardDTO boardDTO = new BoardDTO();
        Optional<Farmer> findFarmer = farmerRepository.findById(1L);
        boardDTO.setBoardTitle("나는야 멋쟁이");
        boardDTO.setBoardContent("I am SeoSeungWoo");
        boardDTO.setMember(findFarmer.get());

        boardService.boardAdd(boardDTO);
    }

//    게시판 제목, 내용 수정
    @Test
    public void updateTest(){
        BoardDTO boardDTO = new BoardDTO();
        Optional<Farmer> findFarmer = farmerRepository.findById(1L);
        Board board = boardRepository.findById(42L).get();

        boardDTO.setBoardTitle("수정된 제목2");
        boardDTO.setBoardContent("수정된 내용2");
        boardDTO.setMember(findFarmer.get());
        boardDTO.setBoardId(board.getBoardId());

        boardService.boardUpdate(boardDTO);
    }

//    게시판 제목, 내용 상세페이지에서 확인하기
    @Test
    public void showDetailTest(){
        boardService.boardShowDetail(42L);
        log.info(""+boardService.boardShowDetail(42L));
    }

//  내가 게시한 게시글 총 개수 갖고오기
    @Test
    public void showBoardCountMineTest(){
        log.info("해당 회원이 게시한 총 게시글 수 : " +   boardService.showBoardCountMine(1L));
    }

//   해당 보드의 조회 수
    @Test
    public void showViewCountTest(){
        log.info("조회 수 : " + boardService.showViewCount(42L));
    }

//    해당 보드의 댓글 수
    @Test
    public void showBoardReplyCountTest(){
        log.info("보드 댓글 수 : " + boardService.showBoardReplyCount(42L));
    }

//    보드 목록 가져오기
//    @Test
//    public void showBoardListTest(){
//        boardService.showBoardList().forEach(t ->log.info("전체 보드 목록: " + t.toString()));
//    }

//    보드 지우기
    @Test
    public void removeBoardTest(){
        boardService.removeBoard(37L);
    }




}
