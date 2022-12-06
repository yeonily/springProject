package com.codefarm.codefarmer.entity.member;

import com.codefarm.codefarmer.domain.member.UserDTO;
import com.codefarm.codefarmer.entity.alba.Alba;
import com.codefarm.codefarmer.entity.alba.QAlba;
import com.codefarm.codefarmer.entity.alba.QMemberAlba;
import com.codefarm.codefarmer.entity.board.Board;
import com.codefarm.codefarmer.entity.inquire.Inquire;
import com.codefarm.codefarmer.entity.inquire.QInquire;
import com.codefarm.codefarmer.entity.member.User;
import com.codefarm.codefarmer.entity.program.MemberProgram;
import com.codefarm.codefarmer.entity.program.QMemberProgram;
import com.codefarm.codefarmer.entity.program.QProgram;
import com.codefarm.codefarmer.repository.member.UserRepository;
import com.codefarm.codefarmer.type.FarmerType;
import com.codefarm.codefarmer.type.UserType;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.codefarm.codefarmer.entity.alba.QAlba.alba;
import static com.codefarm.codefarmer.entity.alba.QMemberAlba.*;
import static com.codefarm.codefarmer.entity.board.QBoard.board;
import static com.codefarm.codefarmer.entity.inquire.QInquire.*;
import static com.codefarm.codefarmer.entity.member.QFarmer.farmer;
import static com.codefarm.codefarmer.entity.member.QUser.*;
import static com.codefarm.codefarmer.entity.program.QMemberProgram.*;
import static com.codefarm.codefarmer.type.Oauth.KAKAO;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class UserTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired private JPAQueryFactory jpaQueryFactory;

    @Test
    public void saveTest(){

        UserDTO UserDTO = new UserDTO();
        UserDTO.setUserType(UserType.USER);
        UserDTO.setMemberBirth("1994-10-21");
        UserDTO.setMemberEmail("aaa@naver.com");
        UserDTO.setMemberLocation("서울");
        UserDTO.setMemberName("김지연");
        UserDTO.setMemberNickname("rr222");
        UserDTO.setMemberPhone("010-1114-1111");
        UserDTO.setMemberOauth(KAKAO);

        User user = UserDTO.toEntity();

        userRepository.save(user);

    }

    @Test
    public void findTest(){
        Optional<User> findUser = userRepository.findById(2l);
        if(findUser.isPresent()){
            assertThat(findUser.get().getMemberName()).isEqualTo("김지연");

            log.info("user name : " + findUser.get().getMemberName());
        }

    }


    @Test
    public void updateTest(){
        User user = userRepository.findById(1l).get();
        UserDTO UserDTO = new UserDTO();
        UserDTO.setMemberNickname("김지김지");

        user.update(UserDTO);
    }

    //    타입변경 update
    @Test
    public void typeChangeTest(){
        long execute = jpaQueryFactory
                .update(user)
                .set(user.userType, UserType.USER)
                .where(user.memberId.eq(2l))
                .execute();

        assertThat(userRepository.findById(2l).get().getUserType()).isEqualTo("USER");
    }

    @Test
    public void deleteTest(){
        userRepository.deleteById(1l);
    }


    //내가 신청한 알바 select
//    @Test
//    public void findMyAlbaTest(){
//        jpaQueryFactory.select(memberAlba)
//                .from(memberAlba).join(memberAlba.memberId)
//                .where(memberAlba.memberId.eq(1l))
//                .fetchJoin().fetch().stream().map(Alba::toString).forEach(log::info);
//    }

    //내가 신청한 프로그램 select
    @Test
    public void findMyAlbaTest(){
        List<MemberProgram> user = jpaQueryFactory.select(memberProgram)
                .from(memberProgram).join(memberProgram.member)
                .where(memberProgram.member.memberId.eq(2l))
                .fetchJoin().fetch();

//        Assertions.assertThat(user.get().getMember().getMemberName()).isEqualTo("김지연");
        user.stream().map(MemberProgram::getMember).forEach(member -> log.info(member.getMemberName()));
    }

    //내가 쓴 글 select
    @Test
    public void findMyBoardTest(){
        jpaQueryFactory.select(board)
                .from(board).join(board.member)
                .where(board.member.memberId.eq(15l))
                .fetchJoin().fetch().stream().map(Board::getBoardTitle).forEach(log::info);
    }

    //닉네임 중복검사
    @Test
    public void checkUserNickTest(){
        Assertions.assertThat(userRepository.checkUserNick("러너")).isEqualTo(1);
    }

    //내가 쓴 문의글 select
    @Test
    public void findMyInquireTest(){
        jpaQueryFactory.select(inquire)
                .from(inquire).join(inquire.member)
                .where(inquire.member.memberId.eq(1l))
                .fetchJoin().fetch().stream().map(Inquire::getInquireQTitle).forEach(log::info);
    }

    @Test
    public void findMyProgramApplyTest(){
    }

}
