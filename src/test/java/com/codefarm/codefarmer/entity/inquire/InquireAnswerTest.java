package com.codefarm.codefarmer.entity.inquire;

import com.codefarm.codefarmer.domain.inquire.InquireAnswerDTO;
import com.codefarm.codefarmer.entity.inquire.Inquire;
import com.codefarm.codefarmer.entity.inquire.InquireAnswer;
import com.codefarm.codefarmer.repository.inquire.InquireAnswerRepository;
import com.codefarm.codefarmer.repository.inquire.InquireRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.codefarm.codefarmer.entity.inquire.QInquireAnswer.inquireAnswer1;


@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class InquireAnswerTest {
    @Autowired
    private InquireAnswerRepository inquireAnswerRepository;
    @Autowired
    private InquireRepository inquireRepository;
    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    
//    문의 답변 등록
    @Test
    public void inquireAnswerSaveTest(){
        InquireAnswerDTO inquireAnswerDTO = new InquireAnswerDTO();
        Optional<Inquire> inquire = inquireRepository.findById(7L);

        inquireAnswerDTO.setInquireAnswer("답변임. 누가 뭐래도 답변임.");
        inquireAnswerDTO.setInquire(inquire.get());

        InquireAnswer inquireAnswer = inquireAnswerDTO.toEntity();
        inquireAnswer.changeInquire(inquireAnswerDTO.getInquire());

        inquireAnswerRepository.save(inquireAnswer);

    }

//    해당 문의 글에 대한 답변
    @Test
    public void inquireAnswerSelectOneTest(){
        jpaQueryFactory.select(inquireAnswer1.inquire.inquireQTitle, inquireAnswer1.inquireAnswer).from(inquireAnswer1)
                .where(inquireAnswer1.inquire.inquireId.eq(7L))
                .fetch()
                .stream().map(InquireAnswer -> InquireAnswer.toString()).forEach(log::info);
    }

}
