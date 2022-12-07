package com.codefarm.codefarmer.service.member;

import com.codefarm.codefarmer.domain.member.MemberDTO;
import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

        private final MemberRepository memberRepository;


        //    회원가입
        public void join(MemberDTO memberDTO){
                Member member = memberDTO.toEntity();
                memberRepository.save(member);
        }
}
