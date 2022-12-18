package com.codefarm.codefarmer.entity.alba;

import com.codefarm.codefarmer.domain.alba.AlbaDTO;
import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.repository.alba.AlbaRepository;
import com.codefarm.codefarmer.repository.member.MemberRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
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

        Optional<Member> findMember = memberRepository.findById(131L);
        log.info("findMember : " + findMember.toString());

        AlbaDTO albaDTO1 = new AlbaDTO();

        albaDTO1.setAlbaAddress("연태관");
        albaDTO1.setAlbaApplyCount(1);
        albaDTO1.setAlbaApplyEndDate(LocalDateTime.of(2022,12,19,0,0));
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

        AlbaDTO albaDTO3 = new AlbaDTO();

        albaDTO3.setAlbaAddress("학원");
        albaDTO3.setAlbaApplyCount(1);
        albaDTO3.setAlbaApplyEndDate(LocalDateTime.of(2023,12,31,0,0));
        albaDTO3.setAlbaApplyStartDate(LocalDateTime.of(2021,11,20,0,0));
        albaDTO3.setAlbaApplyTotalCount(5);
        albaDTO3.setAlbaBannerOne("학원");
        albaDTO3.setAlbaBannerTitle("학원 제목");
        albaDTO3.setAlbaImage("학원");
        albaDTO3.setAlbaMainContent("학원");
        albaDTO3.setAlbaMainTitle("학원");
        albaDTO3.setAlbaPrice(13_200);
        albaDTO3.setAlbaProfileContent1("학원");
        albaDTO3.setAlbaProfileContent2("학원");
        albaDTO3.setAlbaProfileTitle1("학원");
        albaDTO3.setAlbaProfileTitle2("학원");
        albaDTO3.setAlbaStrongContent1("학원");
        albaDTO3.setAlbaStrongContent2("학원");
        albaDTO3.setAlbaStrongContent3("학원");
        albaDTO3.setAlbaStrongTitle1("학원");
        albaDTO3.setAlbaStrongTitle2("학원");
        albaDTO3.setAlbaStrongTitle3("학원");
        albaDTO3.setAlbaText("학원");
        albaDTO3.setAlbaTextTitle("학원");
        albaDTO3.setAlbaTitle("학원");
        albaDTO3.setAlbaTitleOne("학원");
        albaDTO3.setAlbaWorkDate(localDateTime);
        albaDTO3.setMember(findMember.get());

        AlbaDTO albaDTO4 = new AlbaDTO();

        albaDTO4.setAlbaAddress("학원");
        albaDTO4.setAlbaApplyCount(1);
        albaDTO4.setAlbaApplyEndDate(LocalDateTime.of(2023,12,31,0,0));
        albaDTO4.setAlbaApplyStartDate(LocalDateTime.of(2021,11,20,0,0));
        albaDTO4.setAlbaApplyTotalCount(5);
        albaDTO4.setAlbaBannerOne("학원");
        albaDTO4.setAlbaBannerTitle("학원 제목");
        albaDTO4.setAlbaImage("학원");
        albaDTO4.setAlbaMainContent("학원");
        albaDTO4.setAlbaMainTitle("학원");
        albaDTO4.setAlbaPrice(13_200);
        albaDTO4.setAlbaProfileContent1("학원");
        albaDTO4.setAlbaProfileContent2("학원");
        albaDTO4.setAlbaProfileTitle1("학원");
        albaDTO4.setAlbaProfileTitle2("학원");
        albaDTO4.setAlbaStrongContent1("학원");
        albaDTO4.setAlbaStrongContent2("학원");
        albaDTO4.setAlbaStrongContent3("학원");
        albaDTO4.setAlbaStrongTitle1("학원");
        albaDTO4.setAlbaStrongTitle2("학원");
        albaDTO4.setAlbaStrongTitle3("학원");
        albaDTO4.setAlbaText("학원");
        albaDTO4.setAlbaTextTitle("학원");
        albaDTO4.setAlbaTitle("학원");
        albaDTO4.setAlbaTitleOne("학원");
        albaDTO4.setAlbaWorkDate(localDateTime);
        albaDTO4.setMember(findMember.get());

        AlbaDTO albaDTO5 = new AlbaDTO();

        albaDTO5.setAlbaAddress("학원");
        albaDTO5.setAlbaApplyCount(1);
        albaDTO5.setAlbaApplyEndDate(LocalDateTime.of(2023,12,31,0,0));
        albaDTO5.setAlbaApplyStartDate(LocalDateTime.of(2021,11,20,0,0));
        albaDTO5.setAlbaApplyTotalCount(5);
        albaDTO5.setAlbaBannerOne("학원");
        albaDTO5.setAlbaBannerTitle("학원 제목");
        albaDTO5.setAlbaImage("학원");
        albaDTO5.setAlbaMainContent("학원");
        albaDTO5.setAlbaMainTitle("학원");
        albaDTO5.setAlbaPrice(13_200);
        albaDTO5.setAlbaProfileContent1("학원");
        albaDTO5.setAlbaProfileContent2("학원");
        albaDTO5.setAlbaProfileTitle1("학원");
        albaDTO5.setAlbaProfileTitle2("학원");
        albaDTO5.setAlbaStrongContent1("학원");
        albaDTO5.setAlbaStrongContent2("학원");
        albaDTO5.setAlbaStrongContent3("학원");
        albaDTO5.setAlbaStrongTitle1("학원");
        albaDTO5.setAlbaStrongTitle2("학원");
        albaDTO5.setAlbaStrongTitle3("학원");
        albaDTO5.setAlbaText("학원");
        albaDTO5.setAlbaTextTitle("학원");
        albaDTO5.setAlbaTitle("학원");
        albaDTO5.setAlbaTitleOne("학원");
        albaDTO5.setAlbaWorkDate(localDateTime);
        albaDTO5.setMember(findMember.get());

        AlbaDTO albaDTO6 = new AlbaDTO();

        albaDTO6.setAlbaAddress("학원");
        albaDTO6.setAlbaApplyCount(1);
        albaDTO6.setAlbaApplyEndDate(LocalDateTime.of(2023,12,31,0,0));
        albaDTO6.setAlbaApplyStartDate(LocalDateTime.of(2021,11,20,0,0));
        albaDTO6.setAlbaApplyTotalCount(5);
        albaDTO6.setAlbaBannerOne("학원");
        albaDTO6.setAlbaBannerTitle("학원 제목");
        albaDTO6.setAlbaImage("학원");
        albaDTO6.setAlbaMainContent("학원");
        albaDTO6.setAlbaMainTitle("학원");
        albaDTO6.setAlbaPrice(13_200);
        albaDTO6.setAlbaProfileContent1("학원");
        albaDTO6.setAlbaProfileContent2("학원");
        albaDTO6.setAlbaProfileTitle1("학원");
        albaDTO6.setAlbaProfileTitle2("학원");
        albaDTO6.setAlbaStrongContent1("학원");
        albaDTO6.setAlbaStrongContent2("학원");
        albaDTO6.setAlbaStrongContent3("학원");
        albaDTO6.setAlbaStrongTitle1("학원");
        albaDTO6.setAlbaStrongTitle2("학원");
        albaDTO6.setAlbaStrongTitle3("학원");
        albaDTO6.setAlbaText("학원");
        albaDTO6.setAlbaTextTitle("학원");
        albaDTO6.setAlbaTitle("학원");
        albaDTO6.setAlbaTitleOne("학원");
        albaDTO6.setAlbaWorkDate(localDateTime);
        albaDTO6.setMember(findMember.get());

        AlbaDTO albaDTO7 = new AlbaDTO();

        albaDTO7.setAlbaAddress("학원");
        albaDTO7.setAlbaApplyCount(1);
        albaDTO7.setAlbaApplyEndDate(LocalDateTime.of(2023,12,31,0,0));
        albaDTO7.setAlbaApplyStartDate(LocalDateTime.of(2021,11,20,0,0));
        albaDTO7.setAlbaApplyTotalCount(5);
        albaDTO7.setAlbaBannerOne("학원");
        albaDTO7.setAlbaBannerTitle("학원 제목");
        albaDTO7.setAlbaImage("학원");
        albaDTO7.setAlbaMainContent("학원");
        albaDTO7.setAlbaMainTitle("학원");
        albaDTO7.setAlbaPrice(13_200);
        albaDTO7.setAlbaProfileContent1("학원");
        albaDTO7.setAlbaProfileContent2("학원");
        albaDTO7.setAlbaProfileTitle1("학원");
        albaDTO7.setAlbaProfileTitle2("학원");
        albaDTO7.setAlbaStrongContent1("학원");
        albaDTO7.setAlbaStrongContent2("학원");
        albaDTO7.setAlbaStrongContent3("학원");
        albaDTO7.setAlbaStrongTitle1("학원");
        albaDTO7.setAlbaStrongTitle2("학원");
        albaDTO7.setAlbaStrongTitle3("학원");
        albaDTO7.setAlbaText("학원");
        albaDTO7.setAlbaTextTitle("학원");
        albaDTO7.setAlbaTitle("학원");
        albaDTO7.setAlbaTitleOne("학원");
        albaDTO7.setAlbaWorkDate(localDateTime);
        albaDTO7.setMember(findMember.get());

        AlbaDTO albaDTO8 = new AlbaDTO();

        albaDTO8.setAlbaAddress("학원");
        albaDTO8.setAlbaApplyCount(1);
        albaDTO8.setAlbaApplyEndDate(LocalDateTime.of(2023,12,31,0,0));
        albaDTO8.setAlbaApplyStartDate(LocalDateTime.of(2021,11,20,0,0));
        albaDTO8.setAlbaApplyTotalCount(5);
        albaDTO8.setAlbaBannerOne("학원");
        albaDTO8.setAlbaBannerTitle("학원 제목");
        albaDTO8.setAlbaImage("학원");
        albaDTO8.setAlbaMainContent("학원");
        albaDTO8.setAlbaMainTitle("학원");
        albaDTO8.setAlbaPrice(13_200);
        albaDTO8.setAlbaProfileContent1("학원");
        albaDTO8.setAlbaProfileContent2("학원");
        albaDTO8.setAlbaProfileTitle1("학원");
        albaDTO8.setAlbaProfileTitle2("학원");
        albaDTO8.setAlbaStrongContent1("학원");
        albaDTO8.setAlbaStrongContent2("학원");
        albaDTO8.setAlbaStrongContent3("학원");
        albaDTO8.setAlbaStrongTitle1("학원");
        albaDTO8.setAlbaStrongTitle2("학원");
        albaDTO8.setAlbaStrongTitle3("학원");
        albaDTO8.setAlbaText("학원");
        albaDTO8.setAlbaTextTitle("학원");
        albaDTO8.setAlbaTitle("학원");
        albaDTO8.setAlbaTitleOne("학원");
        albaDTO8.setAlbaWorkDate(localDateTime);
        albaDTO8.setMember(findMember.get());

        AlbaDTO albaDTO9 = new AlbaDTO();

        albaDTO9.setAlbaAddress("학원");
        albaDTO9.setAlbaApplyCount(1);
        albaDTO9.setAlbaApplyEndDate(LocalDateTime.of(2023,12,31,0,0));
        albaDTO9.setAlbaApplyStartDate(LocalDateTime.of(2021,11,20,0,0));
        albaDTO9.setAlbaApplyTotalCount(5);
        albaDTO9.setAlbaBannerOne("학원");
        albaDTO9.setAlbaBannerTitle("학원 제목");
        albaDTO9.setAlbaImage("학원");
        albaDTO9.setAlbaMainContent("학원");
        albaDTO9.setAlbaMainTitle("학원");
        albaDTO9.setAlbaPrice(13_200);
        albaDTO9.setAlbaProfileContent1("학원");
        albaDTO9.setAlbaProfileContent2("학원");
        albaDTO9.setAlbaProfileTitle1("학원");
        albaDTO9.setAlbaProfileTitle2("학원");
        albaDTO9.setAlbaStrongContent1("학원");
        albaDTO9.setAlbaStrongContent2("학원");
        albaDTO9.setAlbaStrongContent3("학원");
        albaDTO9.setAlbaStrongTitle1("학원");
        albaDTO9.setAlbaStrongTitle2("학원");
        albaDTO9.setAlbaStrongTitle3("학원");
        albaDTO9.setAlbaText("학원");
        albaDTO9.setAlbaTextTitle("학원");
        albaDTO9.setAlbaTitle("학원");
        albaDTO9.setAlbaTitleOne("학원");
        albaDTO9.setAlbaWorkDate(localDateTime);
        albaDTO9.setMember(findMember.get());

        AlbaDTO albaDTO10 = new AlbaDTO();

        albaDTO10.setAlbaAddress("학원");
        albaDTO10.setAlbaApplyCount(1);
        albaDTO10.setAlbaApplyEndDate(LocalDateTime.of(2023,12,31,0,0));
        albaDTO10.setAlbaApplyStartDate(LocalDateTime.of(2021,11,20,0,0));
        albaDTO10.setAlbaApplyTotalCount(5);
        albaDTO10.setAlbaBannerOne("학원");
        albaDTO10.setAlbaBannerTitle("학원 제목");
        albaDTO10.setAlbaImage("학원");
        albaDTO10.setAlbaMainContent("학원");
        albaDTO10.setAlbaMainTitle("학원");
        albaDTO10.setAlbaPrice(13_200);
        albaDTO10.setAlbaProfileContent1("학원");
        albaDTO10.setAlbaProfileContent2("학원");
        albaDTO10.setAlbaProfileTitle1("학원");
        albaDTO10.setAlbaProfileTitle2("학원");
        albaDTO10.setAlbaStrongContent1("학원");
        albaDTO10.setAlbaStrongContent2("학원");
        albaDTO10.setAlbaStrongContent3("학원");
        albaDTO10.setAlbaStrongTitle1("학원");
        albaDTO10.setAlbaStrongTitle2("학원");
        albaDTO10.setAlbaStrongTitle3("학원");
        albaDTO10.setAlbaText("학원");
        albaDTO10.setAlbaTextTitle("학원");
        albaDTO10.setAlbaTitle("학원");
        albaDTO10.setAlbaTitleOne("학원");
        albaDTO10.setAlbaWorkDate(localDateTime);
        albaDTO10.setMember(findMember.get());

        AlbaDTO albaDTO11 = new AlbaDTO();

        albaDTO11.setAlbaAddress("학원");
        albaDTO11.setAlbaApplyCount(1);
        albaDTO11.setAlbaApplyEndDate(LocalDateTime.of(2023,12,31,0,0));
        albaDTO11.setAlbaApplyStartDate(LocalDateTime.of(2021,11,20,0,0));
        albaDTO11.setAlbaApplyTotalCount(5);
        albaDTO11.setAlbaBannerOne("학원");
        albaDTO11.setAlbaBannerTitle("학원 제목");
        albaDTO11.setAlbaImage("학원");
        albaDTO11.setAlbaMainContent("학원");
        albaDTO11.setAlbaMainTitle("학원");
        albaDTO11.setAlbaPrice(13_200);
        albaDTO11.setAlbaProfileContent1("학원");
        albaDTO11.setAlbaProfileContent2("학원");
        albaDTO11.setAlbaProfileTitle1("학원");
        albaDTO11.setAlbaProfileTitle2("학원");
        albaDTO11.setAlbaStrongContent1("학원");
        albaDTO11.setAlbaStrongContent2("학원");
        albaDTO11.setAlbaStrongContent3("학원");
        albaDTO11.setAlbaStrongTitle1("학원");
        albaDTO11.setAlbaStrongTitle2("학원");
        albaDTO11.setAlbaStrongTitle3("학원");
        albaDTO11.setAlbaText("학원");
        albaDTO11.setAlbaTextTitle("학원");
        albaDTO11.setAlbaTitle("학원");
        albaDTO11.setAlbaTitleOne("학원");
        albaDTO11.setAlbaWorkDate(localDateTime);
        albaDTO11.setMember(findMember.get());

        AlbaDTO albaDTO12 = new AlbaDTO();

        albaDTO12.setAlbaAddress("학원");
        albaDTO12.setAlbaApplyCount(1);
        albaDTO12.setAlbaApplyEndDate(LocalDateTime.of(2023,12,31,0,0));
        albaDTO12.setAlbaApplyStartDate(LocalDateTime.of(2021,11,20,0,0));
        albaDTO12.setAlbaApplyTotalCount(5);
        albaDTO12.setAlbaBannerOne("학원");
        albaDTO12.setAlbaBannerTitle("학원 제목");
        albaDTO12.setAlbaImage("학원");
        albaDTO12.setAlbaMainContent("학원");
        albaDTO12.setAlbaMainTitle("학원");
        albaDTO12.setAlbaPrice(13_200);
        albaDTO12.setAlbaProfileContent1("학원");
        albaDTO12.setAlbaProfileContent2("학원");
        albaDTO12.setAlbaProfileTitle1("학원");
        albaDTO12.setAlbaProfileTitle2("학원");
        albaDTO12.setAlbaStrongContent1("학원");
        albaDTO12.setAlbaStrongContent2("학원");
        albaDTO12.setAlbaStrongContent3("학원");
        albaDTO12.setAlbaStrongTitle1("학원");
        albaDTO12.setAlbaStrongTitle2("학원");
        albaDTO12.setAlbaStrongTitle3("학원");
        albaDTO12.setAlbaText("학원");
        albaDTO12.setAlbaTextTitle("학원");
        albaDTO12.setAlbaTitle("학원");
        albaDTO12.setAlbaTitleOne("학원");
        albaDTO12.setAlbaWorkDate(localDateTime);
        albaDTO12.setMember(findMember.get());

        AlbaDTO albaDTO13 = new AlbaDTO();

        albaDTO13.setAlbaAddress("학원");
        albaDTO13.setAlbaApplyCount(1);
        albaDTO13.setAlbaApplyEndDate(LocalDateTime.of(2023,12,31,0,0));
        albaDTO13.setAlbaApplyStartDate(LocalDateTime.of(2021,11,20,0,0));
        albaDTO13.setAlbaApplyTotalCount(5);
        albaDTO13.setAlbaBannerOne("학원");
        albaDTO13.setAlbaBannerTitle("학원 제목");
        albaDTO13.setAlbaImage("학원");
        albaDTO13.setAlbaMainContent("학원");
        albaDTO13.setAlbaMainTitle("학원");
        albaDTO13.setAlbaPrice(13_200);
        albaDTO13.setAlbaProfileContent1("학원");
        albaDTO13.setAlbaProfileContent2("학원");
        albaDTO13.setAlbaProfileTitle1("학원");
        albaDTO13.setAlbaProfileTitle2("학원");
        albaDTO13.setAlbaStrongContent1("학원");
        albaDTO13.setAlbaStrongContent2("학원");
        albaDTO13.setAlbaStrongContent3("학원");
        albaDTO13.setAlbaStrongTitle1("학원");
        albaDTO13.setAlbaStrongTitle2("학원");
        albaDTO13.setAlbaStrongTitle3("학원");
        albaDTO13.setAlbaText("학원");
        albaDTO13.setAlbaTextTitle("학원");
        albaDTO13.setAlbaTitle("학원");
        albaDTO13.setAlbaTitleOne("학원");
        albaDTO13.setAlbaWorkDate(localDateTime);
        albaDTO13.setMember(findMember.get());

        AlbaDTO albaDTO14 = new AlbaDTO();

        albaDTO14.setAlbaAddress("학원");
        albaDTO14.setAlbaApplyCount(1);
        albaDTO14.setAlbaApplyEndDate(LocalDateTime.of(2023,12,31,0,0));
        albaDTO14.setAlbaApplyStartDate(LocalDateTime.of(2021,11,20,0,0));
        albaDTO14.setAlbaApplyTotalCount(5);
        albaDTO14.setAlbaBannerOne("학원");
        albaDTO14.setAlbaBannerTitle("학원 제목");
        albaDTO14.setAlbaImage("학원");
        albaDTO14.setAlbaMainContent("학원");
        albaDTO14.setAlbaMainTitle("학원");
        albaDTO14.setAlbaPrice(13_200);
        albaDTO14.setAlbaProfileContent1("학원");
        albaDTO14.setAlbaProfileContent2("학원");
        albaDTO14.setAlbaProfileTitle1("학원");
        albaDTO14.setAlbaProfileTitle2("학원");
        albaDTO14.setAlbaStrongContent1("학원");
        albaDTO14.setAlbaStrongContent2("학원");
        albaDTO14.setAlbaStrongContent3("학원");
        albaDTO14.setAlbaStrongTitle1("학원");
        albaDTO14.setAlbaStrongTitle2("학원");
        albaDTO14.setAlbaStrongTitle3("학원");
        albaDTO14.setAlbaText("학원");
        albaDTO14.setAlbaTextTitle("학원");
        albaDTO14.setAlbaTitle("학원");
        albaDTO14.setAlbaTitleOne("학원");
        albaDTO14.setAlbaWorkDate(localDateTime);
        albaDTO14.setMember(findMember.get());



        Alba alba1 = albaDTO1.toEntity();
        Alba alba2 = albaDTO2.toEntity();
        Alba alba3 = albaDTO3.toEntity();
        Alba alba4 = albaDTO4.toEntity();
        Alba alba5 = albaDTO5.toEntity();
        Alba alba6 = albaDTO6.toEntity();
        Alba alba7 = albaDTO7.toEntity();
        Alba alba8 = albaDTO8.toEntity();
        Alba alba9 = albaDTO9.toEntity();
        Alba alba10 = albaDTO10.toEntity();
        Alba alba11 = albaDTO11.toEntity();
        Alba alba12 = albaDTO12.toEntity();
        Alba alba13 = albaDTO13.toEntity();
        Alba alba14 = albaDTO14.toEntity();
        alba1.changeMember(albaDTO1.getMember());
        alba2.changeMember(albaDTO2.getMember());
        alba3.changeMember(albaDTO3.getMember());
        alba4.changeMember(albaDTO4.getMember());
        alba5.changeMember(albaDTO5.getMember());
        alba6.changeMember(albaDTO6.getMember());
        alba7.changeMember(albaDTO7.getMember());
        alba8.changeMember(albaDTO8.getMember());
        alba9.changeMember(albaDTO9.getMember());
        alba10.changeMember(albaDTO10.getMember());
        alba11.changeMember(albaDTO11.getMember());
        alba12.changeMember(albaDTO12.getMember());
        alba13.changeMember(albaDTO13.getMember());
        alba14.changeMember(albaDTO14.getMember());
        albaRepository.save(alba1);
        albaRepository.save(alba2);
        albaRepository.save(alba3);
        albaRepository.save(alba4);
        albaRepository.save(alba5);
        albaRepository.save(alba6);
        albaRepository.save(alba7);
        albaRepository.save(alba8);
        albaRepository.save(alba9);
        albaRepository.save(alba10);
        albaRepository.save(alba11);
        albaRepository.save(alba12);
        albaRepository.save(alba13);
        albaRepository.save(alba14);
    }

    @Test
    public void pageTest(){

        LocalDateTime localDateTime = LocalDateTime.now();

        Optional<Member> findMember = memberRepository.findById(131L);

        for (int i=0; i<100; i++) {

            Alba alba = new Alba();

            alba.setAlbaAddress("연태관");
            alba.setAlbaApplyCount(1);
            alba.setAlbaApplyEndDate(LocalDateTime.of(2022, 12, 18, 0, 0));
            alba.setAlbaApplyStartDate(LocalDateTime.of(2022, 12, 6, 0, 0));
            alba.setAlbaApplyTotalCount(5);
            alba.setAlbaBannerOne("연태관");
            alba.setAlbaBannerTitle("연태관");
            alba.setAlbaImage("연태관");
            alba.setAlbaMainContent("연태관");
            alba.setAlbaMainTitle("연태관");
            alba.setAlbaPrice(20_200);
            alba.setAlbaProfileContent1("연태관");
            alba.setAlbaProfileContent2("연태관");
            alba.setAlbaProfileTitle1("연태관");
            alba.setAlbaProfileTitle2("연태관");
            alba.setAlbaStrongContent1("연태관");
            alba.setAlbaStrongContent2("연태관");
            alba.setAlbaStrongContent3("연태관");
            alba.setAlbaStrongTitle1("연태관");
            alba.setAlbaStrongTitle2("연태관");
            alba.setAlbaStrongTitle3("연태관");
            alba.setAlbaText("연태관");
            alba.setAlbaTextTitle("연태관");
            alba.setAlbaTitle("연태관");
            alba.setAlbaTitleOne("연태관");
            alba.setAlbaWorkDate(localDateTime);
            alba.setMember(findMember.get());
        }

            Pageable pageable = PageRequest.of(1, 10);

            List<Alba> albas = jpaQueryFactory.selectFrom(QAlba.alba)
            .where(QAlba.alba.albaBannerTitle.eq("연태관"))
                    .orderBy(QAlba.alba.albaId.desc())
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetch();

            long total = jpaQueryFactory.selectFrom(QAlba.alba)
                    .where(QAlba.alba.albaBannerTitle.eq("연태관"))
                    .fetch().size();

            Page<Alba> albaPaging = new PageImpl<>(albas, pageable, total);
            albaPaging.getTotalPages(); // 전체 페이지 개수
            albaPaging.getNumber(); // 현재 페이지

            albaPaging.isFirst();
            albaPaging.isLast();

            albaPaging.getContent().stream().map(Alba::toString).forEach(log::info);
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

