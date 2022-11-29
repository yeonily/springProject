package com.codefarm.codefarmer.entity;

import com.codefarm.codefarmer.domain.InquireDTO;
import com.codefarm.codefarmer.repository.FarmerRepository;
import com.codefarm.codefarmer.repository.InquireAnswerRepository;
import com.codefarm.codefarmer.repository.InquireRepository;
import com.codefarm.codefarmer.type.Status;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.codefarm.codefarmer.entity.QInquire.inquire;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class InquireTest {
    @Autowired
    private InquireRepository inquireRepository;
    @Autowired
    private FarmerRepository farmerRepository;
    @Autowired
    private InquireAnswerRepository inquireAnswerRepository;
    @Autowired
    private JPAQueryFactory jpaQueryFactory;

//    문의 글 등록
    @Test
    public void inquireSaveTest(){
        InquireDTO inquireDTO = new InquireDTO();

        Optional<Farmer> findFarmer = farmerRepository.findById(1L);
        inquireDTO.setMemberId(findFarmer.get());
        inquireDTO.setInquireQTitle("문의사항 질문22");
        inquireDTO.setInquireQContent("TEST  내용22");
        inquireDTO.setInquireStatus(Status.WAITING);

        Inquire inquire  = inquireDTO.toEntity();
        inquire.changeMember(inquireDTO.getMemberId());
        inquireRepository.save(inquire);
    }

//    답변이 등록되고 문의 상태 수정할 때
    @Test
    public void inquireUpdateTest(){
        InquireDTO inquireDTO = new InquireDTO();
        Inquire inquire = inquireRepository.findById(7L).get();

        inquireDTO.setInquireId(inquire.getInquireId());
        inquireDTO.setInquireStatus(Status.WAITING);

        inquire.update(inquireDTO);
    }

//    문의 상태가 대기 상태인 문의 글 검색
    @Test
    public void inquireSelectTest(){
        jpaQueryFactory.select(inquire.inquireId, inquire.inquireStatus).from(inquire)
                .where(inquire.inquireStatus.eq(Status.valueOf("WAITING")))
                .fetch()
                .stream().map(Inquire -> Inquire.toString()).forEach(log::info);
    }

    @Test
    public void inquireSelectAllTest(){
        inquireRepository.findAll().stream().map(inquire -> inquire.toString()).forEach(log::info);
    }

}
