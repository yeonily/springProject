package com.codefarm.codefarmer.entity;

import com.codefarm.codefarmer.domain.alba.AlbaDTO;
import com.codefarm.codefarmer.entity.alba.Alba;
import com.codefarm.codefarmer.entity.member.Farmer;
import com.codefarm.codefarmer.repository.alba.AlbaRepository;
import com.codefarm.codefarmer.repository.member.FarmerRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.codefarm.codefarmer.entity.QAlba.alba;


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
    private FarmerRepository farmerRepository;

    @Test
    public void saveTest(){
        LocalDateTime localDateTime = LocalDateTime.now();

        Optional<Farmer> findFarmer = farmerRepository.findById(1L);
        log.info("findFarmer : " + findFarmer.toString());

        AlbaDTO albaDTO = new AlbaDTO();

        albaDTO.setAlbaAddress("서울시 중랄구");
        albaDTO.setAlbaApplyCount(334234);
        albaDTO.setAlbaApplyEndDate(LocalDateTime.of(2013,3,24,0,0));
        albaDTO.setAlbaApplyStartDate(LocalDateTime.of(2012,3,24,0,0));
        albaDTO.setAlbaApplyTotalCount(12345);
        albaDTO.setAlbaBannerOne("연태관 배너");
        albaDTO.setAlbaBannerTitle("연태관 배너 제목");
        albaDTO.setAlbaImage("연태관 이미지");
        albaDTO.setAlbaMainContent("연태관 메인컨텐트");
        albaDTO.setAlbaMainTitle("연태관 메인제목123");
        albaDTO.setAlbaPrice(10_200);
        albaDTO.setAlbaProfileContent1("연태관 알바프로필내용1");
        albaDTO.setAlbaProfileContent2("연태관 알바프로필내용2");
        albaDTO.setAlbaProfileTitle1("연태관 알바프로필제목1");
        albaDTO.setAlbaProfileTitle2("연태관 알바프로필제목2");
        albaDTO.setAlbaStrongContent1("연태관 알바소개내용1");
        albaDTO.setAlbaStrongContent2("연태관 알바소개내용2");
        albaDTO.setAlbaStrongContent3("연태관 알바소개내용3");
        albaDTO.setAlbaStrongTitle1("연태관 알바소개제목1");
        albaDTO.setAlbaStrongTitle2("연태관 알바소개제목1");
        albaDTO.setAlbaStrongTitle3("연태관 알바소개제목1");
        albaDTO.setAlbaText("연태관 알바문자");
        albaDTO.setAlbaTextTitle("연태관 알바문자제목");
        albaDTO.setAlbaTitle("연태관 알바제목");
        albaDTO.setAlbaTitleOne("연태관 알바제목원");
        albaDTO.setAlbaWorkDate(localDateTime);
        albaDTO.setMember(findFarmer.get());

        Alba alba = albaDTO.toEntity();
        alba.changeMember(albaDTO.getMember());
        albaRepository.save(alba);
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

        Optional<Farmer> findFarmer = farmerRepository.findById(20L);
        log.info("findFarmer : " + findFarmer.toString());
        AlbaDTO albaDTO = new AlbaDTO();

        albaDTO.setAlbaAddress("경주");
        albaDTO.setAlbaApplyCount(123);
        albaDTO.setAlbaApplyEndDate(LocalDateTime.of(2022,12,2,0,0));
        albaDTO.setAlbaApplyStartDate(LocalDateTime.of(2022,11,18,0,0));
        albaDTO.setAlbaApplyTotalCount(5678);
        albaDTO.setAlbaBannerOne("배너원");
        albaDTO.setAlbaBannerTitle("배너 수정 제목");
        albaDTO.setAlbaImage("수정 이미지");
        albaDTO.setAlbaMainContent("수정 메인컨텐트");
        albaDTO.setAlbaMainTitle("수정 메인제목");
        albaDTO.setAlbaPrice(14_200);
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
        jpaQueryFactory.select(alba.albaAddress,alba.albaBannerTitle,alba.albaWorkDate,alba.albaPrice,alba.albaApplyStartDate)
                .from(alba)
                .orderBy(alba.albaApplyStartDate.desc())
                .fetch()
                .stream().map(Alba -> Alba.toString()).forEach(log::info);
    }

//    알바 채용정보 시급순
    @Test
    public void findHighPayListTest(){
        jpaQueryFactory.select(alba.albaAddress,alba.albaBannerTitle,alba.albaWorkDate,alba.albaPrice,alba.albaApplyStartDate)
                .from(alba)
                .orderBy(alba.albaPrice.desc())
                .fetch()
                .stream().map(Alba -> Alba.toString()).forEach(log::info);
    }

//    알바 채용정보 모집중
    @Test
    public void findGatheringListTest(){
        LocalDateTime localDateTime = LocalDateTime.now();
        jpaQueryFactory.select(alba.albaAddress,alba.albaBannerTitle,alba.albaWorkDate,alba.albaPrice,alba.albaApplyStartDate)
                .from(alba)
                .where(alba.albaApplyStartDate.before(localDateTime).and(alba.albaApplyEndDate.after(localDateTime)))
                .orderBy(alba.albaApplyStartDate.desc())
                .fetch()
                .stream().map(Alba -> Alba.toString()).forEach(log::info);
    }
}
