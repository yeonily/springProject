package com.codefarm.codefarmer.entity.inquire;

import com.codefarm.codefarmer.domain.inquire.InquireDTO;
import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.repository.inquire.InquireAnswerRepository;
import com.codefarm.codefarmer.repository.inquire.InquireRepository;
import com.codefarm.codefarmer.repository.member.MemberRepository;
import com.codefarm.codefarmer.type.Status;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.codefarm.codefarmer.entity.inquire.QInquire.inquire;


@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class InquireTest {
    @Autowired
    private InquireRepository inquireRepository;
    @Autowired
    private InquireAnswerRepository inquireAnswerRepository;
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    @Autowired
    private MemberRepository memberRepository;

//    문의 글 등록
    @Test
    public void inquireSaveTest(){
        InquireDTO inquireDTO = new InquireDTO();

        Optional<Member> findMember = memberRepository.findById(1L);
        inquireDTO.setMember(findMember.get());
        inquireDTO.setInquireQTitle("문의 제목");
        inquireDTO.setInquireQContent("문의 TEST  내용22");

        Inquire inquire  = inquireDTO.toEntity();
        inquire.changeMember(inquireDTO.getMember());
        inquireRepository.save(inquire);
    }

//    답변이 등록되고 문의 상태 수정할 때
    @Test
    public void inquireUpdateTest(){
        InquireDTO inquireDTO = new InquireDTO();
        Inquire inquire = inquireRepository.findById(7L).get();

        inquireDTO.setInquireId(inquire.getInquireId());
        inquireDTO.setInquireStatus(Status.CONFIRM);

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

//    모든 문의 글 목록
    @Test
    public void inquireSelectAllTest(){
//        inquireRepository.findAll().stream().map(inquire -> inquire.toString()).forEach(log::info);
        jpaQueryFactory.select(inquire).from(inquire)
                .fetch()
                .stream().map(Inquire -> Inquire.toString()).forEach(log::info);
    }

//    특정 문의 글 제목, 닉네임, 내용, 날짜 출력
    @Test
    public void inquireSelectOneTest(){
        jpaQueryFactory.select(inquire.inquireQTitle, inquire.member.memberNickname, inquire.inquireQContent, inquire.createdDate)
                .from(inquire)
                .where(inquire.inquireId.eq(7L))
                .fetch()
                .stream().map(Inquire -> Inquire.toString()).forEach(log::info);
    }
}
