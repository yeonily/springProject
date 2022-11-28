package com.codefarm.codefarmer.entity;

import com.codefarm.codefarmer.repository.InquireAnswerRepository;
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
public class InquireAnswerTest {
    @Autowired
    private InquireAnswerRepository inquireAnswerRepository;

    @Test
    public void inquireAnswerSaveTest(){

    }

}
