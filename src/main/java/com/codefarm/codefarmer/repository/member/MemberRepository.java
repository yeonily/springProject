package com.codefarm.codefarmer.repository.member;

import com.codefarm.codefarmer.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberCustomRepository {

}
