package com.codefarm.codefarmer.repository.alba;

import com.codefarm.codefarmer.entity.alba.MemberAlba;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberAlbaRepository extends JpaRepository<MemberAlba, Long>, MemberAlbaCustomRepository {
//    아르바이트 글 개수
    @Query("select count(ma) from MemberAlba ma")
    public int countByMemberAlba();
}
