package com.codefarm.codefarmer.service.member;

import com.codefarm.codefarmer.domain.alba.AlbaDTO;
import com.codefarm.codefarmer.domain.member.MemberDTO;
import com.codefarm.codefarmer.domain.mentor.MentorBoardDTO;
import com.codefarm.codefarmer.domain.mentor.MentorDTO;
import com.codefarm.codefarmer.domain.program.ProgramDTO;
import com.codefarm.codefarmer.entity.alba.Alba;
import com.codefarm.codefarmer.entity.board.Board;
import com.codefarm.codefarmer.entity.inquire.Inquire;
import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.entity.mentor.Mentor;
import com.codefarm.codefarmer.entity.mentor.MentorBoard;
import com.codefarm.codefarmer.entity.program.Program;
import com.codefarm.codefarmer.repository.member.MemberRepository;
import com.codefarm.codefarmer.repository.mentor.MentorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

        private final MemberRepository memberRepository;
        private final MentorRepository mentorRepository;

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
        public List<AlbaDTO> registerMyAlba(Long memberId){return memberRepository.findMyAlba(memberId);}
        //내가 등록한 프로그램 목록
        public List<ProgramDTO> registerMyProgram(Long memberId){return memberRepository.findMyProgram(memberId);}
        //내가 등록한 게시글 목록
        public List<Board> registerMyBoard(Long memberId){return memberRepository.findMyBoard(memberId);}
        //나의 문의글 목록
        public List<Inquire> registerMyInquire(Long memberId){return memberRepository.findMyInquire(memberId);}
        //닉네임 중복체크
        public Integer checkUserNick(String nickname) {return memberRepository.checkNick(nickname);}
        //닉네임수정
        @Transactional
        public void updateNick(MemberDTO memberDTO){
                Member member= memberRepository.findById(memberDTO.getMemberId()).get();
                member.updateNick(memberDTO);
        }
        //개인정보수정
        @Transactional
        public void updateInfo(MemberDTO memberDTO){
                Member member= memberRepository.findById(memberDTO.getMemberId()).get();
                member.update(memberDTO);
        }

//        멘토 등록
        public void saveMentor(MentorDTO mentorDTO){
                Mentor mentor = mentorDTO.toEntity();
                mentor.changeMember(mentorDTO.getMemberId());
                mentorRepository.save(mentor);
        }
}
