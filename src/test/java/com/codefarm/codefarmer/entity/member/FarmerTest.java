package com.codefarm.codefarmer.entity.member;

import com.codefarm.codefarmer.domain.member.FarmerDTO;
import com.codefarm.codefarmer.domain.mentor.MentorBoardDTO;
import com.codefarm.codefarmer.domain.mentor.MentorDTO;
import com.codefarm.codefarmer.entity.alba.Alba;
import com.codefarm.codefarmer.entity.alba.QAlba;
import com.codefarm.codefarmer.entity.board.Board;
import com.codefarm.codefarmer.entity.board.QBoard;
import com.codefarm.codefarmer.entity.member.Farmer;
import com.codefarm.codefarmer.entity.program.Program;
import com.codefarm.codefarmer.entity.program.QProgram;
import com.codefarm.codefarmer.repository.member.FarmerRepository;
import com.codefarm.codefarmer.type.FarmerType;
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

import static com.codefarm.codefarmer.entity.alba.QAlba.*;
import static com.codefarm.codefarmer.entity.board.QBoard.*;
import static com.codefarm.codefarmer.entity.member.QFarmer.*;
import static com.codefarm.codefarmer.entity.program.QProgram.*;
import static com.codefarm.codefarmer.entity.program.QProgram.program;
import static com.codefarm.codefarmer.type.Oauth.KAKAO;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class FarmerTest {
        @Autowired
        private FarmerRepository farmerRepository;
        @Autowired
        private JPAQueryFactory jpaQueryFactory;

        @Test
        public void saveTest(){

            FarmerDTO farmerDTO = new FarmerDTO();
            farmerDTO.setFarmerType(FarmerType.FARMER);
            farmerDTO.setMemberBirth("1994-10-21");
            farmerDTO.setMemberEmail("a222@naver.com");
            farmerDTO.setMemberLocation("서울222");
            farmerDTO.setMemberName("김지연");
            farmerDTO.setMemberNickname("r21112");
            farmerDTO.setMemberPhone("010-1112-1611");
            farmerDTO.setMemberOauth(KAKAO);

            Farmer farmer = farmerDTO.toEntity();

            farmerRepository.save(farmer);

        }

        @Test
        public void findByIdTest(){
            assertThat(farmerRepository.findById(1l).get().getMemberName()).isEqualTo("김지연");
        }

    @Test
    public void findTest(){
        Optional<Farmer> findFarmer = farmerRepository.findById(1l);
        if(findFarmer.isPresent()){
            assertThat(findFarmer.get().getMemberName()).isEqualTo("김지연");

            log.info("user name : " + findFarmer.get().getMemberName());
        }

    }


    @Test
    public void updateTest(){
        Farmer farmer = farmerRepository.findById(1l).get();
        FarmerDTO farmerDTO = new FarmerDTO();
        farmerDTO.setFarmerType(FarmerType.FARMER);
        farmerDTO.setMemberBirth("1994-10-22");
        farmerDTO.setMemberEmail("a11@naver.com");
        farmerDTO.setMemberLocation("서울");
        farmerDTO.setMemberName("김지연");
        farmerDTO.setMemberNickname("r2211");
        farmerDTO.setMemberPhone("010-1112-1111");
        farmerDTO.setMemberOauth(KAKAO);

        farmer.update(farmerDTO);
    }

    @Test
    public void typeUpdateTest(){
        Farmer farmer = farmerRepository.findById(1l).get();
        FarmerDTO farmerDTO = new FarmerDTO();
        farmerDTO.setFarmerType(FarmerType.MENTOR);

        farmer.update(farmerDTO);
    }

//    타입변경 update
    @Test
    public void typeChangeTest(){
        long execute = jpaQueryFactory
                .update(farmer)
                .set(farmer.farmerType, FarmerType.MENTOR)
                .where(farmer.memberId.eq(37l))
                .execute();

        assertThat(farmerRepository.findById(37l).get().getFarmerType()).isEqualTo("MENTOR");
    }


    @Test
    public void deleteTest(){
        farmerRepository.deleteById(1l);
    }


    //내가 등록한 알바 select
    @Test
    public void findMyAlbaTest(){
        jpaQueryFactory.select(alba)
                .from(alba).join(alba.member)
                .where(alba.member.memberId.eq(1l))
                .fetchJoin().fetch().stream().map(Alba::toString).forEach(log::info);
    }


    //내가 등록한 프로그램 select
    @Test
    public void findMyProgramTest(){
        jpaQueryFactory.select(program)
                .from(program).join(program.member)
                .where(program.member.memberId.eq(1l))
                .fetchJoin().fetch().stream().map(Program::toString).forEach(log::info);
    }

    //내가 쓴 글 select
    @Test
    public void findMyBoardTest(){
        jpaQueryFactory.select(board)
                .from(board).join(board.member)
                .where(board.member.memberId.eq(15l))
                .fetchJoin().fetch().stream().map(Board::getBoardTitle).forEach(log::info);
    }

    //멘토신청하기 SAVE


}
