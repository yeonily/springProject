package com.codefarm.codefarmer.service.member;

import com.codefarm.codefarmer.domain.alba.AlbaDTO;
import com.codefarm.codefarmer.domain.alba.MemberAlbaDTO;
import com.codefarm.codefarmer.domain.board.BoardDTO;
import com.codefarm.codefarmer.domain.inquire.InquireDTO;
import com.codefarm.codefarmer.domain.member.MemberDTO;
import com.codefarm.codefarmer.domain.mentor.MentorBoardDTO;
import com.codefarm.codefarmer.domain.mentor.MentorDTO;
import com.codefarm.codefarmer.domain.program.MemberProgramDTO;
import com.codefarm.codefarmer.domain.program.ProgramDTO;
import com.codefarm.codefarmer.entity.alba.Alba;
import com.codefarm.codefarmer.entity.alba.MemberAlba;
import com.codefarm.codefarmer.entity.board.Board;
import com.codefarm.codefarmer.entity.inquire.Inquire;
import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.entity.mentor.Mentor;
import com.codefarm.codefarmer.entity.mentor.MentorBoard;
import com.codefarm.codefarmer.entity.program.MemberProgram;
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
        public Member find(Long memberId){
                Optional<Member> findmember = memberRepository.findById(memberId);
                Member member = findmember.get();
                return member;
        }

        //탈퇴
        public void secession(Long memberId){
                memberRepository.deleteById(memberId);
        }

        //내가 등록한 알바 목록
        public List<AlbaDTO> findMyAlbaRegister(Long memberId){return memberRepository.selectMyAlba(memberId);}
        //내가 등록한 프로그램 목록
        public List<ProgramDTO> findMyProgramRegister(Long memberId){return memberRepository.selectMyProgram(memberId);}
        //내가 등록한 게시글 목록
        public List<BoardDTO> findMyBoard(Long memberId){return memberRepository.selectMyBoard(memberId);}
        //나의 문의글 목록
        public List<InquireDTO> findMyInquire(Long memberId){return memberRepository.selectMyInquire(memberId);}
        //닉네임 중복체크
        public Integer checkUserNick(String nickname) {return memberRepository.duplicateNick(nickname);}
        //닉네임수정
        @Transactional
        public void changeNick(Long memberId, String memberNickname){
                memberRepository.updateNick(memberId, memberNickname);
        }
//        //개인정보수정
        @Transactional
        public void changeInfo(Long memberId, String phone, String address) {
                memberRepository.updateInfo(memberId, phone, address);
        }

//        멘토 등록
        public void saveMentor(MentorDTO mentorDTO){
                Mentor mentor = mentorDTO.toEntity();
                mentor.changeMember(memberRepository.findById(mentorDTO.getMemberId()).get());
                mentorRepository.save(mentor);
        }

        //내 글의 댓글 개수
        public int getCountOfReply(Long programId, Long memberId){
                return memberRepository.selectCountOfReply(programId, memberId);
        }

        //멘토 정보 select
        public Mentor getMentorInfo(Long memberId){
                return memberRepository.selectMentorInfo(memberId);
        }

        //멘토 정보 수정
        @Transactional
        public void changeMentorInfo(Long memberId, String crop, String year) {
                memberRepository.updateMentor(memberId, crop, year);
        }

//        멘토 타입 변경
        @Transactional
        public String changeMentorType(Long memberId){
                memberRepository.updateMentorType(memberId);
                return "MENTOR";
        }

        //내가 등록한 프로그램 글의 신청자 목록
        public List<MemberProgramDTO> findMyProgramApplyers(Long memberId, Long programId){
                return memberRepository.selectMyProgramApplyers(memberId, programId);
        }
        //내가 등록한 알바 글의 신청자 목록
        public List<MemberAlbaDTO> findMyAlbaApplyers(Long memberId, Long albaId){
                return memberRepository.selectMyAlbaApplyers(memberId, albaId);
        }

        //내가 신청한 알바 목록
        public List<MemberAlbaDTO> findMyAlbaApply(Long memberId){
                return memberRepository.selectMyAlbaApply(memberId);
        }
        //내가 신청한 프로그램 목록
        public List<ProgramDTO> findMyProgramApply(Long memberId){
               return memberRepository.selectMyProgramApply(memberId);
        }

        //결제내역 목록
        public List<MemberProgramDTO> findMyPay(Long memberId){
                return memberRepository.selectMyPay(memberId);
        }

}
