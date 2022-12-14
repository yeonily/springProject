package com.codefarm.codefarmer.service.member;

import com.codefarm.codefarmer.domain.member.MemberDTO;
import com.codefarm.codefarmer.type.MemberType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@Slf4j
public class MemberServiceTest {

    @Autowired MemberService memberService;

    @Test
    public void joinTest(){
        MemberDTO memberDTO = new MemberDTO();

        memberDTO.setMemberType(MemberType.FARMER);
        memberDTO.setMemberBirth("2022-10-22");
        memberDTO.setMemberEmail("aaa@naver.com");
        memberDTO.setMemberLocation("서울");
        memberDTO.setMemberName("김지연11");
        memberDTO.setMemberNickname("oiii");
        memberDTO.setMemberPhone("010-2222-1111");
//        memberDTO.setMemberOauth(KAKAO);

        memberService.join(memberDTO);
    }

    @Test
    public void getCountOfReplyTest(){
        log.info("결과"+memberService.getCountOfReply(5l,1l));

    }
}
