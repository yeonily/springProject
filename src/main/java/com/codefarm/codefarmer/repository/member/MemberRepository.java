package com.codefarm.codefarmer.repository.member;

import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.type.MemberType;
import com.codefarm.codefarmer.type.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberCustomRepository {

    //oauthid로 memberid찾기 > 회원가입,로그인 때 사용
    @Query("select m.memberId from Member m where m.memberOauthId = :OauthId")
    public Long findMemberId(@Param("OauthId") String OauthId);

    //oauthid로 memberType찾기 > 회원가입,로그인 때 사용
    @Query("select m.memberType from Member m where m.memberOauthId = :OauthId")
    public String findMemberType(@Param("OauthId") String OauthId);

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
    public void updateAlbaStatues(Long albaApplyId, Status status);



}
