package com.codefarm.codefarmer.entity.alba;

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
    private FarmerRepository farmerRepository;

    @Test
    public void saveTest(){
        LocalDateTime localDateTime = LocalDateTime.now();

        Optional<Farmer> findFarmer = farmerRepository.findById(1L);
        log.info("findFarmer : " + findFarmer.toString());

        AlbaDTO albaDTO = new AlbaDTO();

        albaDTO.setAlbaAddress("사당");
        albaDTO.setAlbaApplyCount(1);
        albaDTO.setAlbaApplyEndDate(LocalDateTime.of(2020,1,25,0,0));
        albaDTO.setAlbaApplyStartDate(LocalDateTime.of(2018,12,20,0,0));
        albaDTO.setAlbaApplyTotalCount(5);
        albaDTO.setAlbaBannerOne("배너");
        albaDTO.setAlbaBannerTitle("배너 제목");
        albaDTO.setAlbaImage("이미지");
        albaDTO.setAlbaMainContent("메인컨텐트");
        albaDTO.setAlbaMainTitle("메인제목123");
        albaDTO.setAlbaPrice(15_200);
        albaDTO.setAlbaProfileContent1("알바프로필내용1");
        albaDTO.setAlbaProfileContent2("알바프로필내용2");
        albaDTO.setAlbaProfileTitle1("알바프로필제목1");
        albaDTO.setAlbaProfileTitle2("알바프로필제목2");
        albaDTO.setAlbaStrongContent1("알바소개내용1");
        albaDTO.setAlbaStrongContent2("알바소개내용2");
        albaDTO.setAlbaStrongContent3("알바소개내용3");
        albaDTO.setAlbaStrongTitle1("알바소개제목1");
        albaDTO.setAlbaStrongTitle2("알바소개제목1");
        albaDTO.setAlbaStrongTitle3("알바소개제목1");
        albaDTO.setAlbaText("알바문자");
        albaDTO.setAlbaTextTitle("알바문자제목");
        albaDTO.setAlbaTitle("반복 연태관");
        albaDTO.setAlbaTitleOne("알바제목원");
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

    @Test
    public void delete(Long albaId){
        albaRepository.deleteById(49L);
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


//    메인에서 알바리스트 뽑기
    @Test
    public void findByLatestTest(){
        albaRepository.findByLatest().stream().map(Alba::getAlbaId).forEach(a -> log.info("알바 번호:"+ a));
    }

//    곧마감돼요 8개 뽑기
    @Test
    public void findTop8ByOOrderByAlbaApplyEndDateDescTest(){
        albaRepository.findTop8ByOrderByAlbaApplyEndDateDesc().stream().map(Alba::getAlbaId).forEach(a -> log.info("알바 번호 : " + a));
    }



}

