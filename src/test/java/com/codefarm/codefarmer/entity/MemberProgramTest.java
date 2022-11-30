package com.codefarm.codefarmer.entity;

import com.codefarm.codefarmer.domain.MemberProgramDTO;
import com.codefarm.codefarmer.repository.MemberProgramRepository;
import com.codefarm.codefarmer.repository.ProgramRepository;
import com.codefarm.codefarmer.repository.UserRepository;
import com.codefarm.codefarmer.type.ProgramStatus;
import com.codefarm.codefarmer.type.ProgramType;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.codefarm.codefarmer.entity.QMemberProgram.memberProgram;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class MemberProgramTest {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Autowired
    MemberProgramRepository memberProgramRepository;

    @Autowired
    ProgramRepository programRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    public void saveTest(){
        MemberProgramDTO memberProgramDTO = new MemberProgramDTO();
        LocalDateTime localDateTime = LocalDateTime.now();
        Optional<User> findUser = userRepository.findById(2L);
        Optional<Program> findProgram = programRepository.findById(3L);
        memberProgramDTO.setProgramApplyCount(10);
        memberProgramDTO.setProgramPayment(10000);
        memberProgramDTO.setProgramStatus(ProgramStatus.PAY_SUCCEED);
        memberProgramDTO.setMember(findUser.get());
        memberProgramDTO.setProgram(findProgram.get());
        memberProgramDTO.setProgramApplyBirth(localDateTime);
        memberProgramDTO.setProgramApplyName("정호석");
        memberProgramDTO.setProgramApplyPhoneNum("01012345678");
        memberProgramDTO.setProgramApplyEmail("wjdghtjr@naver.com");
        memberProgramDTO.setProgramApplyLocation("동탄");
        MemberProgram memberProgram =  memberProgramDTO.toEntity();
        memberProgram.changeMember(memberProgramDTO.getMember());
        memberProgram.changeProgram(memberProgramDTO.getProgram());
        memberProgramRepository.save(memberProgram);
    }

    @Test
    public void findAllTest(){
        jpaQueryFactory.selectFrom(memberProgram)
                .fetch()
                .stream().map(memberProgram -> memberProgram.toString()).forEach(log::info);
    }

    @Test
    public void update(){
        List<MemberProgram> memberPrograms = memberProgramRepository.findAll();
//        MemberProgramDTO List<memberProgramDTO> =  memberProgramRepository.findAll();
    }

}
