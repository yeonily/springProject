package com.codefarm.codefarmer.service;


import com.codefarm.codefarmer.domain.BoardDTO;
import com.codefarm.codefarmer.entity.Board;
import com.codefarm.codefarmer.entity.Farmer;
import com.codefarm.codefarmer.repository.BoardRepository;
import com.codefarm.codefarmer.repository.FarmerRepository;
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
        boardDTO.setMemberId(findFarmer.get());

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
        boardDTO.setMemberId(findFarmer.get());
        boardDTO.setBoardId(board.getBoardId());

        boardService.boardUpdate(boardDTO);
    }

//    게시판 제목, 내용 상세페이지에서 확인하기
    @Test
    public void showDetailTest(){
        boardService.boardShowDetail(42L);
        log.info(""+boardService.boardShowDetail(42L));
    }






}
