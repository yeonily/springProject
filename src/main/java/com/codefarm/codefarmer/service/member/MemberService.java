package com.codefarm.codefarmer.service.member;

import com.codefarm.codefarmer.domain.member.MemberDTO;
import com.codefarm.codefarmer.entity.alba.Alba;
import com.codefarm.codefarmer.entity.board.Board;
import com.codefarm.codefarmer.entity.inquire.Inquire;
import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.entity.program.Program;
import com.codefarm.codefarmer.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

        private final MemberRepository memberRepository;

        //    회원가입
        public void join(MemberDTO memberDTO){
                Member member = memberDTO.toEntity();
                memberRepository.save(member);
        }

        //정보 조회
        public Member select(Long memberId){
                Optional<Member> findmember = memberRepository.findById(memberId);
                Member member = findmember.get();
                return member;
        }

        //탈퇴
        public void delete(Long memberId){
                memberRepository.deleteById(memberId);
        }

        //내가 등록한 알바 목록
        public List<Alba> registerMyAlba(Long memberId){return memberRepository.findMyAlba(memberId);}
        //내가 등록한 프로그램 목록
        public List<Program> registerMyProgram(Long memberId){return memberRepository.findMyProgram(memberId);}
        //내가 등록한 게시글 목록
        public List<Board> registerMyBoard(Long memberId){return memberRepository.findMyBoard(memberId);}
        //나의 문의글 목록
        public List<Inquire> registerMyInquire(Long memberId){return memberRepository.findMyInquire(memberId);}
}
