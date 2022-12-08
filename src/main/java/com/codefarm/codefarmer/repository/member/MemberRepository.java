package com.codefarm.codefarmer.repository.member;

import com.codefarm.codefarmer.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberCustomRepository {

    @Query("select m.memberId from Member m where m.memberOauthId = :OauthId")
    public Long findMemberId(@Param("OauthId") String OauthId);

    @Query("select m.memberType from Member m where m.memberOauthId = :OauthId")
    public String findMemberType(@Param("OauthId") String OauthId);


}
