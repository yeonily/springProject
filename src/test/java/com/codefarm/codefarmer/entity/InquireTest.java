package com.codefarm.codefarmer.entity;

import com.codefarm.codefarmer.domain.InquireDTO;
import com.codefarm.codefarmer.repository.InquireRepository;
import com.codefarm.codefarmer.type.Status;
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
public class InquireTest {
    @Autowired
    private InquireRepository inquireRepository;

    @Test
    public void inquireSaveTest(){
        InquireDTO inquireDTO = new InquireDTO();

        inquireDTO.setInquireId(1L);
        inquireDTO.setInquireQTitle("문의사항 질문");
        inquireDTO.setInquireQContent("TEST  내용");
        inquireDTO.setInquireStatus(Status.WAITING);

        Inquire inquire = inquireDTO.toEntity();
        inquireRepository.save(inquire);
    }


}
