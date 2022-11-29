package com.codefarm.codefarmer.entity;

import com.codefarm.codefarmer.domain.BoardDTO;
import com.codefarm.codefarmer.domain.QBoardDTO;
import com.codefarm.codefarmer.repository.BoardRepository;
import com.codefarm.codefarmer.repository.FarmerRepository;
import com.mysema.commons.lang.Assert;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AssertionsKt;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.mysema.commons.lang.Assert.assertThat;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class BoardTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private FarmerRepository farmerRepository;

//    게시판 제목, 내용 작성
    @Test
    public void boardSaveTest(){
        BoardDTO boardDTO = new BoardDTO();
        Optional<Farmer> findFarmer = farmerRepository.findById(4L);
        boardDTO.setBoardTitle("나는야 멋쟁이");
        boardDTO.setBoardContent("I am SeoSeungWoo");
        boardDTO.setMemberId(findFarmer.get());

        Board board = boardDTO.toEntity();
        board.changeMember(boardDTO.getMemberId());
        boardRepository.save(board);
    }

//    게시판 제목, 내용 수정
    @Test
    public void boardUpdateTest(){
        BoardDTO boardDTO = new BoardDTO();
        Optional<Farmer> findFarmer = farmerRepository.findById(1L);
        Board board = boardRepository.findById(5L).get();

        boardDTO.setBoardTitle("수정된 제목");
        boardDTO.setBoardContent("수정된 내용");
        boardDTO.setMemberId(findFarmer.get());
        boardDTO.setBoardId(board.getBoardId());
//        boardDTO.setMemberId(findFarmer.get());
//
//        board.changeMember(boardDTO.getMemberId());

        board.update(boardDTO);
    }

//   Detail 게시글 작성한 사람 닉네임 갖고오기
    @Test
    public void findGetBoardUser(){
        Optional<Board> findBoardUser = boardRepository.findById(5L);
            log.info("board : " + findBoardUser.get().getBoardId());
            log.info("nickName : " + findBoardUser.get().getMember().getMemberNickname());
            log.info("nickName : " + findBoardUser.get().getBoardFiles());
    }



//    게시글 제목, 내용 select
    @Test
    public void findDetailTest(){
        Optional<Board> findTitleContent = boardRepository.findById(5L);

        if(findTitleContent.isPresent()){
            log.info("boardTitle : " + findTitleContent.get().getBoardTitle());
            log.info("boardContent : " + findTitleContent.get().getBoardContent());
        }
    }



//    게시판 지우기
    @Test
    public void boardDeleteTest(){
        boardRepository.deleteById(3L);
    }
}
