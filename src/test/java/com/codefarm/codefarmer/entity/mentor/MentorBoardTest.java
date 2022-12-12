package com.codefarm.codefarmer.entity.mentor;

import com.codefarm.codefarmer.domain.mentor.MentorBoardDTO;
import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.entity.mentor.MentorBoard;
import com.codefarm.codefarmer.repository.member.MemberRepository;
import com.codefarm.codefarmer.repository.mentor.MentorBoardRepository;
import com.codefarm.codefarmer.repository.mentor.MentorRepository;
import com.codefarm.codefarmer.type.MemberType;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.codefarm.codefarmer.entity.mentor.QMentorBoard.mentorBoard;


@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class MentorBoardTest {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    @Autowired
    private MentorRepository mentorRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MentorBoardRepository mentorBoardRepository;

    //    멘토 글 쓰기
    @Test
    public void mentorWriteTest(){
        MentorBoardDTO mentorBoardDTO = new MentorBoardDTO();
        Optional<Member> findMember = memberRepository.findById(112L);
        Optional<Mentor> findMentor = mentorRepository.findById(293L);

            mentorBoardDTO.setMentorCareer("고구마 농사 멘토 활동중");
            mentorBoardDTO.setMentorExCareer("20회 이상 멘티 컨설팅 25년 다양한 작물 재배");
            mentorBoardDTO.setMentorStrongTitle1("20회 이상 멘티 가르침으로 생긴 노하우 전수");
            mentorBoardDTO.setMentorStrongContent1("2019년 44명 지정된 농업 마스터");
            mentorBoardDTO.setMentorStrongTitle2("내 강점 2");
            mentorBoardDTO.setMentorStrongContent2("내 강점 내용 2");
            mentorBoardDTO.setMentorStrongTitle3("내 강점 3");
            mentorBoardDTO.setMentorStrongContent3("내 강점 내용 3");
            mentorBoardDTO.setMentorTitle("크리스마스 파뤼!");
            mentorBoardDTO.setMentorTitleSub("서브 배너 문구입니다.");
            mentorBoardDTO.setMentorTextTitle("텍스트 제목");
            mentorBoardDTO.setMentorTextContent("텍스트 내용");
            mentorBoardDTO.setMemberId(findMember.get().getMemberId());
            mentorBoardDTO.setMentorId(findMentor.get().getMentorId());

        MentorBoard mentorBoard = mentorBoardDTO.toEntity();
        mentorBoard.changeMember(findMember.get());
        mentorBoard.changeMentor(findMentor.get());
        mentorBoardRepository.save(mentorBoard);
    }

// 멘토 글 상세보기
    @Test
    public void mentorDetailTest(){
        Optional<MentorBoard> findMentorDetail = mentorBoardRepository.findById(294L);

        log.info("경력 : " + findMentorDetail.get().getMentorCareer());
        log.info("이전 경력 : " + findMentorDetail.get().getMentorExCareer());
        log.info("강점 1 : " + findMentorDetail.get().getMentorStrongTitle1());
        log.info("강점 내용 1 : " + findMentorDetail.get().getMentorStrongContent1());
        log.info("강점 2 : " + findMentorDetail.get().getMentorStrongTitle2());
        log.info("강점 내용 2 : " + findMentorDetail.get().getMentorStrongContent2());
        log.info("강점 3 : " + findMentorDetail.get().getMentorStrongTitle3());
        log.info("강점 내용 3 : " + findMentorDetail.get().getMentorStrongContent3());
        log.info("서브배너 제목문구 : " + findMentorDetail.get().getMentorTitle());
        log.info("서브배너 문구 : " + findMentorDetail.get().getMentorTitleSub());
        log.info("내용 제목 : " + findMentorDetail.get().getMentorTextTitle());
        log.info("내용 : " + findMentorDetail.get().getMentorTextContent());
    }

//    목록 들고오기(나중에 이미지 추가해야함)
    @Test
    public void findMentorListTestDESC(){
        jpaQueryFactory.select(mentorBoard.mentorTitle)
                .from(mentorBoard)
                .orderBy(mentorBoard.createdDate.desc())
                .fetch()
                .forEach(log::info);
    }


//    멘토 목록에 닉네임 갖고오기
@Test
public void findNicknameTest(){
    Optional<MentorBoard> mentorBoard = mentorBoardRepository.findById(6L);
    log.info("mentorNickName : " + mentorBoard.get().getMember().getMemberNickname());
}


    //   멘토 목록에 제목 갖고오기
    @Test
    public void findTitleTest(){
        Optional<MentorBoard> mentorBoard = mentorBoardRepository.findById(6L);
        log.info("mentorTitle : " + mentorBoard.get().getMentorTitle());
    }

//    멘토 글 삭제하기
    @Test
    public void mentorWriteDeleteTest(){
        mentorBoardRepository.deleteById(5L);
    }
}
