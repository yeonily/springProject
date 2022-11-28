package com.codefarm.codefarmer.entity;

import com.codefarm.codefarmer.domain.BoardDTO;
import com.codefarm.codefarmer.repository.BoardRepository;
import com.codefarm.codefarmer.repository.FarmerRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

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
        boardDTO.setBoardTitle("board제목");
        boardDTO.setBoardContent("안녕하신가");
        boardDTO.setMemberId(farmerRepository.findById(1l).get());
        log.info("여기 : " + farmerRepository.findById(1l).get());

        Board board = boardDTO.toEntity();
        boardRepository.save(board);
    }
}
