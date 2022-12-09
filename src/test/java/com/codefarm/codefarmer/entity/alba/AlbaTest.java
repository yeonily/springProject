package com.codefarm.codefarmer.entity.alba;

import com.codefarm.codefarmer.domain.alba.AlbaDTO;
import com.codefarm.codefarmer.entity.alba.Alba;
import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.repository.alba.AlbaRepository;
import com.codefarm.codefarmer.repository.member.MemberRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.codefarm.codefarmer.entity.alba.QAlba.alba;


@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class AlbaTest {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Autowired
    private AlbaRepository albaRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void saveTest(){
        LocalDateTime localDateTime = LocalDateTime.now();

        Optional<Member> findMember = memberRepository.findById(1L);
        log.info("findMember : " + findMember.toString());

        AlbaDTO albaDTO1 = new AlbaDTO();

        albaDTO1.setAlbaAddress("연태관");
        albaDTO1.setAlbaApplyCount(1);
        albaDTO1.setAlbaApplyEndDate(LocalDateTime.of(2022,12,18,0,0));
        albaDTO1.setAlbaApplyStartDate(LocalDateTime.of(2022,12,6,0,0));
        albaDTO1.setAlbaApplyTotalCount(5);
        albaDTO1.setAlbaBannerOne("연태관");
        albaDTO1.setAlbaBannerTitle("연태관");
        albaDTO1.setAlbaImage("연태관");
        albaDTO1.setAlbaMainContent("연태관");
        albaDTO1.setAlbaMainTitle("연태관");
        albaDTO1.setAlbaPrice(20_200);
        albaDTO1.setAlbaProfileContent1("연태관");
        albaDTO1.setAlbaProfileContent2("연태관");
        albaDTO1.setAlbaProfileTitle1("연태관");
        albaDTO1.setAlbaProfileTitle2("연태관");
        albaDTO1.setAlbaStrongContent1("연태관");
        albaDTO1.setAlbaStrongContent2("연태관");
        albaDTO1.setAlbaStrongContent3("연태관");
        albaDTO1.setAlbaStrongTitle1("연태관");
        albaDTO1.setAlbaStrongTitle2("연태관");
        albaDTO1.setAlbaStrongTitle3("연태관");
        albaDTO1.setAlbaText("연태관");
        albaDTO1.setAlbaTextTitle("연태관");
        albaDTO1.setAlbaTitle("연태관");
        albaDTO1.setAlbaTitleOne("연태관");
        albaDTO1.setAlbaWorkDate(localDateTime);
        albaDTO1.setMember(findMember.get());

        AlbaDTO albaDTO2 = new AlbaDTO();

        albaDTO2.setAlbaAddress("학원");
        albaDTO2.setAlbaApplyCount(1);
        albaDTO2.setAlbaApplyEndDate(LocalDateTime.of(2023,12,31,0,0));
        albaDTO2.setAlbaApplyStartDate(LocalDateTime.of(2021,11,20,0,0));
        albaDTO2.setAlbaApplyTotalCount(5);
        albaDTO2.setAlbaBannerOne("학원");
        albaDTO2.setAlbaBannerTitle("학원 제목");
        albaDTO2.setAlbaImage("학원");
        albaDTO2.setAlbaMainContent("학원");
        albaDTO2.setAlbaMainTitle("학원");
        albaDTO2.setAlbaPrice(13_200);
        albaDTO2.setAlbaProfileContent1("학원");
        albaDTO2.setAlbaProfileContent2("학원");
        albaDTO2.setAlbaProfileTitle1("학원");
        albaDTO2.setAlbaProfileTitle2("학원");
        albaDTO2.setAlbaStrongContent1("학원");
        albaDTO2.setAlbaStrongContent2("학원");
        albaDTO2.setAlbaStrongContent3("학원");
        albaDTO2.setAlbaStrongTitle1("학원");
        albaDTO2.setAlbaStrongTitle2("학원");
        albaDTO2.setAlbaStrongTitle3("학원");
        albaDTO2.setAlbaText("학원");
        albaDTO2.setAlbaTextTitle("학원");
        albaDTO2.setAlbaTitle("학원");
        albaDTO2.setAlbaTitleOne("학원");
        albaDTO2.setAlbaWorkDate(localDateTime);
        albaDTO2.setMember(findMember.get());

        Alba alba1 = albaDTO1.toEntity();
        Alba alba2 = albaDTO2.toEntity();
        alba1.changeMember(albaDTO1.getMember());
        albaRepository.save(alba1);
        alba2.changeMember(albaDTO2.getMember());
        albaRepository.save(alba2);
    }

    @Test
    public void findAlbaIdByAlbaTitleTest(){
//       albaRepository.findAll().stream().map(Alba::toString).forEach(log::info);

        jpaQueryFactory.selectFrom(alba)
                .from(alba)
                .fetch()
                .forEach(a -> log.info("값은 : " + a.getAlbaMainTitle()));
    }

    @Test
    public void updateTest(){
        LocalDateTime localDateTime = LocalDateTime.now();

        Optional<Member> findFarmer = memberRepository.findById(20L);
        log.info("findFarmer : " + findFarmer.toString());
        AlbaDTO albaDTO = new AlbaDTO();

        albaDTO.setAlbaAddress("봉천동");
        albaDTO.setAlbaApplyCount(1);
        albaDTO.setAlbaApplyEndDate(LocalDateTime.of(2023,12,31,0,0));
        albaDTO.setAlbaApplyStartDate(LocalDateTime.of(2022,12,20,0,0));
        albaDTO.setAlbaApplyTotalCount(5);
        albaDTO.setAlbaBannerOne("배너원");
        albaDTO.setAlbaBannerTitle("배너 수정 제목");
        albaDTO.setAlbaImage("수정 이미지");
        albaDTO.setAlbaMainContent("수정 메인컨텐트");
        albaDTO.setAlbaMainTitle("수정 메인제목");
        albaDTO.setAlbaPrice(1_200);
        albaDTO.setAlbaProfileContent1("알바프로필내용 수정1");
        albaDTO.setAlbaProfileContent2("알바프로필내용 수정2");
        albaDTO.setAlbaProfileTitle1("알바프로필제목 수정1");
        albaDTO.setAlbaProfileTitle2("알바프로필제목 수정2");
        albaDTO.setAlbaStrongContent1("알바소개내용 수정1");
        albaDTO.setAlbaStrongContent2("알바소개내용 수정2");
        albaDTO.setAlbaStrongContent3("알바소개내용 수정3");
        albaDTO.setAlbaStrongTitle1("알바소개제목 수정1");
        albaDTO.setAlbaStrongTitle2("알바소개제목 수정2");
        albaDTO.setAlbaStrongTitle3("알바소개제목 수정3");
        albaDTO.setAlbaText("알바문자 수정");
        albaDTO.setAlbaTextTitle("알바문자 수정제목");
        albaDTO.setAlbaTitle("알바 수정제목");
        albaDTO.setAlbaTitleOne("알바 수정제목원");
        albaDTO.setAlbaWorkDate(localDateTime);

        albaRepository.findById(20L).get().update(albaDTO);

    }

    @Test
    public void delete(){
        albaRepository.deleteAll();
    }

    @Test void deleteById(){
        albaRepository.deleteById(104L);
    }

//    알바 마감순
    @Test
    public void findDeadlineListTest(){
        jpaQueryFactory.select(alba.albaApplyCount,alba.albaApplyTotalCount,alba.albaPrice,alba.albaMainContent)
                .from(alba)
                .where(alba.albaApplyEndDate.after(LocalDateTime.now()))
                .orderBy(alba.albaApplyEndDate.asc())
                .stream().map(Alba -> Alba.toString()).forEach(log::info);
    }

//    알바 게시글 총 개수
    @Test
    public void findCountAllTest(){
        log.info("게시글 갯수 : " + albaRepository.count());
    }

//    알바 채용정보 최신순
    @Test
    public void findRecentListTest(){
        LocalDateTime localDateTime = LocalDateTime.now();
        jpaQueryFactory.select(alba.albaAddress,alba.albaBannerTitle,alba.albaWorkDate,alba.albaPrice,alba.albaApplyStartDate)
                .from(alba)
                .where(alba.albaApplyStartDate.before(localDateTime))
                .orderBy(alba.albaApplyStartDate.desc())
                .fetch()
                .stream().map(Alba -> Alba.toString()).forEach(log::info);
    }

//    알바 채용정보 시급순
    @Test
    public void findHighPayListTest(){
        LocalDateTime localDateTime = LocalDateTime.now();
        jpaQueryFactory.select(alba.albaAddress,alba.albaBannerTitle,alba.albaWorkDate,alba.albaPrice,alba.albaApplyStartDate)
                .from(alba)
                .where(alba.albaApplyStartDate.before(localDateTime))
                .orderBy(alba.albaPrice.desc())
                .fetch()
                .stream().map(Alba -> Alba.toString()).forEach(log::info);
    }

    @Test
    public void findCountTest(){
        LocalDateTime localDateTime = LocalDateTime.now();
        jpaQueryFactory.select(alba.albaId.count())
                .from(alba)
                .where(alba.albaApplyStartDate.before(localDateTime).and(alba.albaApplyEndDate.after(localDateTime)))
                .fetch()
                .stream().map(Alba -> Alba.toString()).forEach(log::info);
    }

//    알바 채용정보 모집중
    @Test
    public void findListByEndDate(){
        LocalDateTime localDateTime = LocalDateTime.now();
        jpaQueryFactory.select(alba.albaAddress,alba.albaBannerTitle,alba.albaWorkDate,alba.albaPrice,alba.albaApplyStartDate)
                .from(alba)
                .where(alba.albaApplyStartDate.before(localDateTime).and(alba.albaApplyEndDate.after(localDateTime)))
                .orderBy(alba.albaApplyStartDate.desc())
                .fetch()
                .stream().map(Alba -> Alba.toString()).forEach(log::info);
    }

//    메인에서 알바리스트 뽑기
    @Test
    public void findByLatestTest(){
        albaRepository.findByLatest().stream().map(Alba::getAlbaId).forEach(a -> log.info("알바 번호:"+ a));
    }
}

