package com.codefarm.codefarmer.entity.alba;

import com.codefarm.codefarmer.domain.alba.MemberAlbaDTO;
import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.repository.alba.AlbaRepository;
import com.codefarm.codefarmer.repository.alba.MemberAlbaRepository;
import com.codefarm.codefarmer.repository.member.MemberRepository;
import com.codefarm.codefarmer.type.Status;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.codefarm.codefarmer.entity.alba.QMemberAlba.memberAlba;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class MemberAlbaTest {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Autowired
    MemberAlbaRepository memberAlbaRepository;

    @Autowired
    AlbaRepository albaRepository;

    @Autowired
    MemberRepository memberRepository;

//    @Test
//    public void saveTest(){
//        MemberAlbaDTO memberAlbaDTO = new MemberAlbaDTO();
//        Optional<Member> findUser = memberRepository.findById(131L);
//        Optional<Alba> findAlba = albaRepository.findById(132L);
//        memberAlbaDTO.setMemberStatus(Status.CONFIRM);
//        memberAlbaDTO.setMemberId(findUser.get());
//        memberAlbaDTO.setAlbaId(findAlba.get());
//        MemberAlba memberAlba = memberAlbaDTO.toEntity();
//        memberAlba.changeMember(memberAlbaDTO.getMemberId());
//        memberAlba.changeAlba(memberAlbaDTO.getAlbaId());
//        memberAlbaRepository.save(memberAlba);
//    }

    @Test
    public void findAllTest(){
        jpaQueryFactory.selectFrom(memberAlba)
                .fetch()
                .stream().map(memberAlba -> memberAlba.toString()).forEach(log::info);
    }

    @Test
    public void deleteTest(){
        memberAlbaRepository.deleteById(132L);
    }
}
