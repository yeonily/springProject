package com.codefarm.codefarmer.repository;

import com.codefarm.codefarmer.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
