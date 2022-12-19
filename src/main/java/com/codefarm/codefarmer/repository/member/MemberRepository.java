package com.codefarm.codefarmer.repository.member;

import com.codefarm.codefarmer.domain.alba.MemberAlbaDTO;
import com.codefarm.codefarmer.domain.mentor.MentorDTO;
import com.codefarm.codefarmer.domain.program.MemberProgramDTO;
import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.entity.mentor.Mentor;
import com.codefarm.codefarmer.entity.program.MemberProgram;
import com.codefarm.codefarmer.type.MemberType;
import com.codefarm.codefarmer.type.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberCustomRepository {

    // member Email 찾기
    @Query("select count(m.memberEmail) from Member m where m.memberEmail = :email")
    public int findByEmail(@Param("email") String email);

    // member Email 찾기
    @Query("select m.memberId from Member m where m.memberEmail = :email")
    public Long findByMemberEmail(@Param("email") String email);

    //oauthid로 memberid찾기 > 회원가입,로그인 때 사용
    @Query("select m.memberId from Member m where m.memberOauthId = :OauthId")
    public Long selectMemberId(@Param("OauthId") String OauthId);

    //oauthid로 memberType찾기 > 회원가입,로그인 때 사용
    @Query("select m.memberType from Member m where m.memberOauthId = :OauthId")
    public String selectMemberType(@Param("OauthId") String OauthId);

    //    프로그램 등록글 개수
    @Query("select count(p) from Program p")
    public int countByProgram();

    //    프로그램 신청 글 개수
    @Query("select count(mp) from MemberProgram mp")
    public int countByMemberProgram();

    //    알바수락상태변경
    @Transactional
    @Modifying
    @Query("update MemberAlba a set a.memberStatus = :status where a.albaApplyId in :albaApplyId")
    public void updateAlbaStatues(@Param("albaApplyId")Long albaApplyId, @Param("status")Status status);

    //    내가 쓴 글의 댓글 개수
    @Query("select count(r) from Reply r where r.board.boardId = :boardId and r.board.member.memberId = :memberId")
    public int selectCountOfReply(@Param("boardId")Long boardId, @Param("memberId")Long memberId);

    //    닉네임변경
    @Transactional
    @Modifying
    @Query("update Member m set m.memberNickname = :memberNickname where m.memberId in :memberId")
    public void updateNick(@Param("memberId")Long memberId, @Param("memberNickname")String memberNickname);

    //정보변경
    @Transactional
    @Modifying
    @Query("update Member m set m.memberPhone = :phone, m.memberLocation = :address where m.memberId in :memberId")
    void updateInfo(@Param("memberId")Long memberId, @Param("phone")String phone, @Param("address")String address);

    //멘토 정보 select
    @Query("select mm from Mentor mm where mm.member.memberId = :memberId")
    public Mentor selectMentorInfo(@Param("memberId")Long memberId);

    //멘토 정보 수정
    @Transactional
    @Modifying
    @Query("update Mentor m set m.mentorCrop = :crop, m.mentorYear = :year where m.member.memberId in :memberId")
    void updateMentor(@Param("memberId")Long memberId, @Param("crop")String crop, @Param("year")String year);

    //멘토 타입 변경
    @Transactional
    @Modifying
    @Query("update Member m set m.memberType = 'MENTOR' where m.memberId in :memberId")
    void updateMentorType(@Param("memberId")Long memberId);

    //    검색(닉네임) + 페이징
    public Page<Member> findMemberByMemberNicknameContaining(Pageable pageable, String memberNickname);
    //    검색(이름) + 페이징
    public Page<Member> findMemberByMemberNameContaining(Pageable pageable, String memberName);
    //    검색(연락처) + 페이징
    public Page<Member> findMemberByMemberPhoneContaining(Pageable pageable, String memberPhone);

    //    멤버 총 명수
    @Query("select count(m) from Member m")
    public int countByMember();

    //프로그램 신청 상태 변경
    @Transactional
    @Modifying
    @Query("update MemberProgram m set m.programStatus = 'PAY_CANCELED' where m.programApplyId in :programApplyId")
    void updateMemberStatus(@Param("programApplyId")Long programApplyId);

    //프로그램 신청 상태 변경 - 수락
    @Transactional
    @Modifying
    @Query("update MemberAlba m set m.memberStatus = 'CONFIRM' where m.albaApplyId in :albaApplyId")
    void updateMemberAlbaConfirm(@Param("albaApplyId")Long albaApplyId);

    //프로그램 신청 상태 변경 - 거절
    @Transactional
    @Modifying
    @Query("update MemberAlba m set m.memberStatus = 'REJECT' where m.albaApplyId in :albaApplyId")
    void updateMemberAlbaReject(@Param("albaApplyId")Long albaApplyId);

    //알바 수락 시 +1
    @Transactional
    @Modifying
    @Query("update Alba a set a.albaApplyCount = a.albaApplyCount+1 where a.albaId in :albaId")
    void updateAlbaCount(@Param("albaId")Long albaId);

    //멘티 타입 변경
    @Transactional
    @Modifying
    @Query("update Member m set m.memberType = 'MENTEE' where m.memberId in :memberId")
    void updateMenteeType(@Param("memberId")Long memberId);

}
