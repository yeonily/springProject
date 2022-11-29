package com.codefarm.codefarmer.entity;

import com.codefarm.codefarmer.domain.InquireAnswerDTO;
import com.codefarm.codefarmer.repository.InquireAnswerRepository;
import com.codefarm.codefarmer.repository.InquireRepository;
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
public class InquireAnswerTest {
    @Autowired
    private InquireAnswerRepository inquireAnswerRepository;
    @Autowired
    private InquireRepository inquireRepository;

    @Test
    public void inquireAnswerSaveTest(){
        InquireAnswerDTO inquireAnswerDTO = new InquireAnswerDTO();
        Optional<Inquire> inquire = inquireRepository.findById(2L);

        inquireAnswerDTO.setInquireAnswer("답변임. 누가 뭐래도 답변임.");
        inquireAnswerDTO.setInquire(inquire.get());

        InquireAnswer inquireAnswer = inquireAnswerDTO.toEntity();
        inquireAnswer.changeInquire(inquireAnswerDTO.getInquire());

        inquireAnswerRepository.save(inquireAnswer);

    }

}
