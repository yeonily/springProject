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
import com.codefarm.codefarmer.repository.program.MemberProgramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

        private final MemberRepository memberRepository;
        private final MemberProgramRepository memberProgramRepository;
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

        //내가 등록한 알바 목록 - 페이징
        public Page<AlbaDTO> showMyAlbaRegister(Long memberId, Pageable pageable){return memberRepository.findMyAlba(memberId, pageable);}
        //내가 등록한 프로그램 목록
        public List<ProgramDTO> showMyProgramRegister(Long memberId){return memberRepository.findMyProgram(memberId);}
        //내가 등록한 프로그램 목록 - 페이징
        public Page<ProgramDTO> showMyProgramRegister(Long memberId, Pageable pageable){return memberRepository.findMyProgram(memberId, pageable);}
        //내가 등록한 게시글 목록
//        public List<BoardDTO> showMyBoard(Long memberId){return memberRepository.findMyBoard(memberId);}
        //내가 등록한 게시글 목록 - 페이징
        public Page<BoardDTO> showMyBoard(Long memberId, Pageable pageable){return memberRepository.findMyBoard(memberId, pageable);}
        //나의 문의글 목록 - 페이징
        public Page<InquireDTO> showMyInquire(Long memberId, Pageable pageable){return memberRepository.findMyInquire(memberId, pageable);}
        //나의 문의글 목록
        public List<InquireDTO> showMyInquire(Long memberId){return memberRepository.findMyInquire(memberId);}
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
                return memberRepository.CountOfReply(programId, memberId);
        }

        //멘토 정보 select
        public Mentor getMentorInfo(Long memberId){
                return memberRepository.findMentorInfo(memberId);
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

//        멘티 타입 변경
        @Transactional
        public String changeMenteeType(Long memberId){
                memberRepository.updateMenteeType(memberId);
                return "MENTEE";
        }

//        프로그램 상태 변경
        @Transactional
        public void changeMemberStatus(Long programApplyId){
                memberRepository.updateMemberStatus(programApplyId);
        }

        //내가 등록한 프로그램 글의 신청자 목록
        public List<MemberProgramDTO> showMyProgramApplyers(Long memberId, Long programId){
                return memberRepository.findMyProgramApplyers(memberId, programId);
        }
        //내가 등록한 알바 글의 신청자 목록
        public List<MemberAlbaDTO> showMyAlbaApplyers(Long memberId, Long albaId){
                return memberRepository.findMyAlbaApplyers(memberId, albaId);
        }

        //내가 신청한 알바 목록 - 페이징
        public Page<MemberAlbaDTO> showMyAlbaApply(Long memberId, Pageable pageable){
                return memberRepository.findMyAlbaApply(memberId,pageable);
        }
        //내가 신청한 프로그램 목록
        public List<ProgramDTO> showMyProgramApply(Long memberId){
               return memberRepository.findMyProgramApply(memberId);
        }
        //내가 신청한 프로그램 목록 - 페이징
        public Page<ProgramDTO> showMyProgramApply(Long memberId, Pageable pageable){
               return memberRepository.findMyProgramApply(memberId, pageable);
        }

        //결제내역 목록 - 페이징
        public Page<MemberProgramDTO> showMyPay(Long memberId, Pageable pageable){
                return memberRepository.findMyPay(memberId, pageable);
        }

        //프로그램 신청 내역 보기
        public MemberProgramDTO showMyApplyInfo(Long programApplyId, Long memberId){
                return memberRepository.findApplyInfo(programApplyId, memberId);
        }

        //        알바 상태 변경 - 수락
        @Transactional
        public void changeMemberAlbaConfirm(Long programApplyId){
                memberRepository.updateMemberAlbaConfirm(programApplyId);
        }

        //        알바 상태 변경 - 거절
        @Transactional
        public void changeMemberAlbaReject(Long programApplyId){
                memberRepository.updateMemberAlbaReject(programApplyId);
        }

        //        알바 count 변경 - 수락시 +1
        @Transactional
        public void plusAlbaCount(Long albaId){
                memberRepository.updateAlbaCount(albaId);
        }



}
