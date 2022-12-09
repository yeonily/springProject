package com.codefarm.codefarmer.entity.member;

import com.codefarm.codefarmer.domain.member.MemberDTO;
import com.codefarm.codefarmer.domain.mentor.MentorBoardDTO;
import com.codefarm.codefarmer.domain.mentor.MentorDTO;
import com.codefarm.codefarmer.entity.alba.Alba;
import com.codefarm.codefarmer.entity.alba.QAlba;
import com.codefarm.codefarmer.entity.board.Board;
import com.codefarm.codefarmer.entity.board.QBoard;
import com.codefarm.codefarmer.entity.inquire.Inquire;
import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.entity.program.MemberProgram;
import com.codefarm.codefarmer.entity.program.Program;
import com.codefarm.codefarmer.entity.program.QMemberProgram;
import com.codefarm.codefarmer.entity.program.QProgram;
import com.codefarm.codefarmer.repository.member.MemberRepository;
import com.codefarm.codefarmer.type.MemberType;
import com.codefarm.codefarmer.type.Status;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.codefarm.codefarmer.entity.alba.QAlba.*;
import static com.codefarm.codefarmer.entity.board.QBoard.*;
import static com.codefarm.codefarmer.entity.inquire.QInquire.inquire;
import static com.codefarm.codefarmer.entity.member.QMember.*;
import static com.codefarm.codefarmer.entity.program.QMemberProgram.*;
import static com.codefarm.codefarmer.entity.program.QMemberProgram.memberProgram;
import static com.codefarm.codefarmer.entity.program.QProgram.*;
import static com.codefarm.codefarmer.entity.program.QProgram.program;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class MemberTest {
        @Autowired
        private MemberRepository memberRepository;
        @Autowired
        private JPAQueryFactory jpaQueryFactory;

        @Test
        public void saveTest(){

            MemberDTO MemberDTO = new MemberDTO();
            MemberDTO.setMemberType(MemberType.USER);
            MemberDTO.setMemberBirth("1994-10-21");
            MemberDTO.setMemberEmail("a222@naver.com");
            MemberDTO.setMemberLocation("서울222");
            MemberDTO.setMemberName("김지연");
            MemberDTO.setMemberNickname("r21112");
            MemberDTO.setMemberPhone("010-1112-1611");
//            MemberDTO.setMemberOauth(KAKAO);

            Member Member = MemberDTO.toEntity();

            memberRepository.save(Member);

        }

        @Test
        public void findByIdTest(){
            assertThat(memberRepository.findById(1l).get().getMemberName()).isEqualTo("김지연");
        }

    @Test
    public void findTest(){
        Optional<Member> findMember = memberRepository.findById(1l);
        if(findMember.isPresent()){
            assertThat(findMember.get().getMemberName()).isEqualTo("김지연");

            log.info("user name : " + findMember.get().getMemberName());
        }

    }


    @Test
    public void updateTest(){
        Member Member = memberRepository.findById(1l).get();
        MemberDTO MemberDTO = new MemberDTO();
        MemberDTO.setMemberType(MemberType.USER);
        MemberDTO.setMemberBirth("1994-10-22");
        MemberDTO.setMemberEmail("a11@naver.com");
        MemberDTO.setMemberLocation("서울");
        MemberDTO.setMemberName("김지연");
        MemberDTO.setMemberNickname("r2211");
        MemberDTO.setMemberPhone("010-1112-1111");
//        MemberDTO.setMemberOauth(KAKAO);

        Member.update(MemberDTO);
    }

    @Test
    public void typeUpdateTest(){
        Member Member = memberRepository.findById(1l).get();
        MemberDTO MemberDTO = new MemberDTO();
        MemberDTO.setMemberType(MemberType.MENTOR);

        Member.update(MemberDTO);
    }

//    타입변경 update
//    @Test
//    public void typeChangeTest(){
//        long execute = jpaQueryFactory
//                .update(member)
//                .set(member.memberType)
//                .where(Member.memberId.eq(37l))
//                .execute();
//
//        assertThat(MemberRepository.findById(37l).get().getMemberType()).isEqualTo("MENTOR");
//    }


    @Test
    public void deleteTest(){
        memberRepository.deleteById(1l);
    }


    //내가 등록한 알바 select
    @Test
    public void findMyAlbaTest(){
            memberRepository.findMyAlba(1l).stream().map(Alba::getAlbaTitle).forEach(log::info);
    }


    //내가 등록한 프로그램 select
    @Test
    public void findMyProgramTest(){
            memberRepository.findMyProgram(1l).stream().map(Program::toString).forEach(log::info);
    }

    //내가 쓴 글 select
    @Test
    public void findMyBoardTest(){
        memberRepository.findMyBoard(1l).stream().map(Board::getBoardTitle).forEach(log::info);
    }


    //내가 신청한 알바 select
//    @Test
//    public void findMyAlbaTest(){
//        jpaQueryFactory.select(memberAlba)
//                .from(memberAlba).join(memberAlba.memberId)
//                .where(memberAlba.memberId.eq(1l))
//                .fetchJoin().fetch().stream().map(Alba::toString).forEach(log::info);
//    }
//
//    //내가 신청한 프로그램 select
//    @Test
//    public void findMyAlbaTest(){
//        List<MemberProgram> user = jpaQueryFactory.select(memberProgram)
//                .from(memberProgram).join(memberProgram.member)
//                .where(memberProgram.member.memberId.eq(2l))
//                .fetchJoin().fetch();
//
////        Assertions.assertThat(user.get().getMember().getMemberName()).isEqualTo("김지연");
//        user.stream().map(MemberProgram::getMember).forEach(member -> log.info(member.getMemberName()));
//    }
//
//    //내가 쓴 글 select
//    @Test
//    public void findMyBoardTest(){
//        jpaQueryFactory.select(board)
//                .from(board).join(board.member)
//                .where(board.member.memberId.eq(15l))
//                .fetchJoin().fetch().stream().map(Board::getBoardTitle).forEach(log::info);
//    }
//
//    //닉네임 중복검사
    @Test
    public void checkUserNickTest(){
        Assertions.assertThat(memberRepository.checkNick("러너")).isEqualTo(0);
    }
//
//    //내가 쓴 문의글 select
//    @Test
//    public void findMyInquireTest(){
//        jpaQueryFactory.select(inquire)
//                .from(inquire).join(inquire.member)
//                .where(inquire.member.memberId.eq(1l))
//                .fetchJoin().fetch().stream().map(Inquire::getInquireQTitle).forEach(log::info);
//    }
//

    @Test
    public void findMyProgramApplyersTest(){
        Long memberId = 1l;
        memberRepository.findMyProgramApplyers(memberId).stream().map(memberProgram->memberProgram.getProgramApplyName()).forEach(log::info);
    }

    @Test
    public void updateAlbaStatusTest(){
        Long albaApplyId = 1l;
        Status status = Status.CONFIRM;
        memberRepository.updateAlbaStatues(albaApplyId, status);

    }

}
