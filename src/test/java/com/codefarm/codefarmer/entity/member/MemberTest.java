package com.codefarm.codefarmer.entity.member;

import com.codefarm.codefarmer.domain.alba.AlbaDTO;
import com.codefarm.codefarmer.domain.board.BoardDTO;
import com.codefarm.codefarmer.domain.member.MemberDTO;
import com.codefarm.codefarmer.domain.mentor.MentorBoardDTO;
import com.codefarm.codefarmer.domain.mentor.MentorDTO;
import com.codefarm.codefarmer.domain.program.MemberProgramDTO;
import com.codefarm.codefarmer.domain.program.ProgramDTO;
import com.codefarm.codefarmer.entity.alba.Alba;
import com.codefarm.codefarmer.entity.alba.QAlba;
import com.codefarm.codefarmer.entity.board.Board;
import com.codefarm.codefarmer.entity.board.QBoard;
import com.codefarm.codefarmer.entity.inquire.Inquire;
import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.entity.mentor.Mentor;
import com.codefarm.codefarmer.entity.program.*;
import com.codefarm.codefarmer.repository.inquire.InquireAnswerRepository;
import com.codefarm.codefarmer.repository.member.MemberRepository;
import com.codefarm.codefarmer.repository.mentor.MentorRepository;
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
import static com.codefarm.codefarmer.entity.program.QProgramFile.programFile;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class MemberTest {
        @Autowired
        private MemberRepository memberRepository;
        @Autowired
        private MentorRepository mentorRepository;
        @Autowired
        private JPAQueryFactory jpaQueryFactory;
        @Autowired
        private InquireAnswerRepository inquireAnswerRepository;

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
//            memberRepository.selectMyAlba(1l).stream().map(AlbaDTO::getAlbaTitle).forEach(log::info);
    }


    //내가 등록한 프로그램 select
    @Test
    public void findMyProgramTest(){
            memberRepository.selectMyProgram(1l).stream().map(ProgramDTO::toString).forEach(log::info);
    }

    //내가 쓴 글 select
    @Test
    public void findMyBoardTest(){
//        memberRepository.selectMyBoard(1l).stream().map(BoardDTO::getBoardTitle).forEach(log::info);
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
        Assertions.assertThat(memberRepository.duplicateNick("러너")).isEqualTo(0);
    }

    //내가 쓴 문의글 select
    @Test
    public void findMyInquireTest(){
            Long memberId = 1l;
//            log.info("결과 : "+memberRepository.selectMyInquire(1l).size());
    }


    @Test
    public void findMyProgramApplyersTest(){
//        Long memberId = 1l;
//        memberRepository.findMyProgramApplyers(memberId).stream().map(memberProgram->memberProgram.getProgramApplyName()).forEach(log::info);
    }

    @Test
    public void updateAlbaStatusTest(){
        Long albaApplyId = 1l;
        Status status = Status.CONFIRM;
        memberRepository.updateAlbaStatues(albaApplyId, status);

    }

    @Test
    public void saveMentorTest(){
//        Optional<Member> findMember = memberRepository.findById(1L);
//        MentorDTO mentorDTO = new MentorDTO();
//        mentorDTO.setMemberId(findMember.get());
//        mentorDTO.setMentorCrop("옥수수");
//        mentorDTO.setMentorYear("3~5년차");
//        Mentor mentor = mentorDTO.toEntity();
//        mentor.changeMember(mentorDTO.getMemberId());
//        mentorRepository.save(mentor);

    }

    @Test
    public void updateNickTest(){
            memberRepository.updateNick(1l, "나");
    }

    @Test
    public void updateInfoTest(){
            memberRepository.updateInfo(1l, "010-0000-0000", "서울시");
    }

    @Test
    public void selectMyPayTest(){
//            memberRepository.selectMyPay(11l).stream().map(memberProgramDTO -> memberProgramDTO.toString()).forEach(log::info);
    }

    @Test
    public void selectMyProgramApplyTest() {
        Long memberId = 11l;
//        List<Program> programs = jpaQueryFactory.select(program).from(memberProgram, program, programFile).where(program.programId.eq(memberProgram.program.programId).and(program.programId.eq(programFile.program.programId))).fetch();
//        programs.stream().forEach(v->{
//            log.info("파일개수"+v.getProgramFiles().size());
//        });
//        programs.stream().forEach(v->{
//            log.info("멤버프로그램"+v.getMemberPrograms().get(2).getProgramApplyName());
//        });


//        List<Program> programs1 = jpaQueryFactory.select(program).from(memberProgram, program, programFile).where(program.programId.eq(memberProgram.program.programId).and(program.programId.eq(programFile.program.programId))
//                    .and(memberProgram.member.memberId.eq(memberId))).fetch();
//        programs1.stream().forEach(v->{
//            log.info("왜?! " + v.getMemberPrograms().size());
//        });

//            jpaQueryFactory.selectFrom(QProgram.program).leftJoin(programFile).fetchJoin().leftJoin(memberProgram).fetchJoin().distinct().fetch();


        memberRepository.selectMyProgramApply(memberId);
    }

    @Test
    public void selectApplyInfoTest(){
            Long programApplyId = 1l;
            Long memberId = 1l;
            log.info("?"+memberRepository.selectApplyInfo(programApplyId, memberId));

    }

    @Test
    public void updateAlbaCountTest(){
            Long albaId = 2l;
            memberRepository.updateAlbaCount(albaId);
    }

    @Test
    public void findByInquireIdTest(){
        log.info(inquireAnswerRepository.findByInquireId(99l).toString());
    }

}
