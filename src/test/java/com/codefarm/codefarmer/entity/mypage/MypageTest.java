package com.codefarm.codefarmer.entity.mypage;

import com.codefarm.codefarmer.entity.member.Farmer;
import com.codefarm.codefarmer.entity.member.QFarmer;
import com.codefarm.codefarmer.entity.mentor.Mentor;
import com.codefarm.codefarmer.repository.member.FarmerRepository;
import com.codefarm.codefarmer.repository.member.UserRepository;
import com.codefarm.codefarmer.repository.mentor.MentorMenteeRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.codefarm.codefarmer.entity.member.QFarmer.*;
import static com.codefarm.codefarmer.entity.notice.QNotice.notice;
import static com.codefarm.codefarmer.entity.program.QProgram.program;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class MypageTest {

    @Autowired
    private MentorMenteeRepository mentorMenteeRepository;
    @Autowired
    private FarmerRepository farmerRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    //내 정보 가져오기
    @Test
    public void findByIdTest(){
        jpaQueryFactory.selectFrom(farmer)
                .where(farmer.memberId.eq(1L))
                .fetchOne();

        assertThat(farmer.memberName).isEqualTo("김지연");


    }


}
