package com.codefarm.codefarmer.entity;

import com.codefarm.codefarmer.domain.BoardDTO;
import com.codefarm.codefarmer.repository.BoardRepository;
import com.codefarm.codefarmer.repository.FarmerRepository;
import com.mysema.commons.lang.Assert;
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
public class BoardTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private FarmerRepository farmerRepository;

    @Test
    public void boardSaveTest(){
        BoardDTO boardDTO = new BoardDTO();
        Optional<Farmer> findFarmer = farmerRepository.findById(1L);
        boardDTO.setBoardTitle("board제목1");
        boardDTO.setBoardContent("안녕하신가1");
        boardDTO.setMemberId(findFarmer.get());

        Board board = boardDTO.toEntity();
        board.changeMember(boardDTO.getMemberId());
        boardRepository.save(board);
    }

    @Test
    public void boardDeleteTest(){
        boardRepository.deleteAll();
    }
}
