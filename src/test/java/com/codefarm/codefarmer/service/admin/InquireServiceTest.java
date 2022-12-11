package com.codefarm.codefarmer.service.admin;

import com.codefarm.codefarmer.domain.inquire.InquireDTO;
import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.repository.inquire.InquireCustomRepository;
import com.codefarm.codefarmer.repository.member.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class InquireServiceTest {
    @Autowired
    private InquireService inquireService;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private InquireCustomRepository inquireCustomRepository;

    @Test
    public void inquireAddTest(){
        InquireDTO inquireDTO = new InquireDTO();
        Optional<Member> findMember = memberRepository.findById(11L);

        inquireDTO.setInquireQTitle("나 문희");
        inquireDTO.setInquireQContent("문희는 포도가 먹고찌푼뎅...");
        inquireDTO.setMember(findMember.get());
        inquireService.inquireAdd(inquireDTO);
    }

    @Test
    public void inquireJoinTest(){
//        inquireCustomRepository.findByInquireLikeMemberNickname("r2211").stream().forEach(i -> log.info("문의 글 : " + i));
    }

}
