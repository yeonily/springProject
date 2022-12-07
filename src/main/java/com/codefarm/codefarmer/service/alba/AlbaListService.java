package com.codefarm.codefarmer.service.alba;

import com.codefarm.codefarmer.domain.alba.AlbaDTO;
import com.codefarm.codefarmer.domain.alba.QAlbaDTO;
import com.codefarm.codefarmer.entity.alba.Alba;
import com.codefarm.codefarmer.repository.alba.AlbaRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static com.codefarm.codefarmer.entity.alba.QAlba.alba;

@Service
@RequiredArgsConstructor
public class AlbaListService {

    private final JPAQueryFactory jpaQueryFactory;
    private final AlbaRepository albaRepository;

    //알바 목록
    @Transactional(readOnly = true)
    public Page<Alba> albaShowAll(Pageable pageable){
        return albaRepository.findAll(pageable);
    }

    // 곧 마감 아르바이트 8개 정렬
    public List<AlbaDTO> showListByRecentEndDate() {
        LocalDateTime localDateTime = LocalDateTime.now();
        return jpaQueryFactory.select(new QAlbaDTO(
                alba.albaId,
                alba.albaTitle,
                alba.albaImage,
                alba.albaTitleOne,
                alba.albaApplyStartDate,
                alba.albaApplyEndDate,
                alba.albaWorkDate,
                alba.albaApplyCount,
                alba.albaApplyTotalCount,
                alba.albaAddress,
                alba.albaPrice,
                alba.albaMainTitle,
                alba.albaMainContent,
                alba.albaStrongTitle1,
                alba.albaStrongContent1,
                alba.albaStrongTitle2,
                alba.albaStrongContent2,
                alba.albaStrongTitle3,
                alba.albaStrongContent3,
                alba.albaBannerTitle,
                alba.albaBannerOne,
                alba.albaTextTitle,
                alba.albaText,
                alba.albaProfileTitle1,
                alba.albaProfileContent1,
                alba.albaProfileTitle2,
                alba.albaProfileContent2
        )).from(alba)
                .where(alba.albaApplyStartDate.before(localDateTime).and(alba.albaApplyEndDate.after(localDateTime)))
                .limit(8)
                .orderBy(alba.albaApplyEndDate.asc())
                .fetch();
    }

    //    알바 게시글 총 개수
    public Long showAlbaTotalCount() {
        return albaRepository.count();
    }


    // 알바 게시글 정렬에 맞게 개수
    public Long showCount(){
        LocalDateTime localDateTime = LocalDateTime.now();
       return jpaQueryFactory.select(alba.albaId.count())
                .from(alba)
                .where(alba.albaApplyStartDate.before(localDateTime).and(alba.albaApplyEndDate.after(localDateTime)))
                .fetchOne();
    }

    //    알바 채용정보 최신순
    public List<AlbaDTO> showListByRecent() {
        LocalDateTime localDateTime = LocalDateTime.now();
        return jpaQueryFactory.select(new QAlbaDTO(
                alba.albaId,
                alba.albaTitle,
                alba.albaImage,
                alba.albaTitleOne,
                alba.albaApplyStartDate,
                alba.albaApplyEndDate,
                alba.albaWorkDate,
                alba.albaApplyCount,
                alba.albaApplyTotalCount,
                alba.albaAddress,
                alba.albaPrice,
                alba.albaMainTitle,
                alba.albaMainContent,
                alba.albaStrongTitle1,
                alba.albaStrongContent1,
                alba.albaStrongTitle2,
                alba.albaStrongContent2,
                alba.albaStrongTitle3,
                alba.albaStrongContent3,
                alba.albaBannerTitle,
                alba.albaBannerOne,
                alba.albaTextTitle,
                alba.albaText,
                alba.albaProfileTitle1,
                alba.albaProfileContent1,
                alba.albaProfileTitle2,
                alba.albaProfileContent2
        )).from(alba)
                .where(alba.albaApplyStartDate.before(localDateTime))
                .orderBy(alba.albaApplyStartDate.desc())
                .fetch();
    }

    //    알바 채용정보 시급순
    public List<AlbaDTO> showListByHighPay(Pageable pageable) {
        LocalDateTime localDateTime = LocalDateTime.now();
        return jpaQueryFactory.select(new QAlbaDTO(
                alba.albaId,
                alba.albaTitle,
                alba.albaImage,
                alba.albaTitleOne,
                alba.albaApplyStartDate,
                alba.albaApplyEndDate,
                alba.albaWorkDate,
                alba.albaApplyCount,
                alba.albaApplyTotalCount,
                alba.albaAddress,
                alba.albaPrice,
                alba.albaMainTitle,
                alba.albaMainContent,
                alba.albaStrongTitle1,
                alba.albaStrongContent1,
                alba.albaStrongTitle2,
                alba.albaStrongContent2,
                alba.albaStrongTitle3,
                alba.albaStrongContent3,
                alba.albaBannerTitle,
                alba.albaBannerOne,
                alba.albaTextTitle,
                alba.albaText,
                alba.albaProfileTitle1,
                alba.albaProfileContent1,
                alba.albaProfileTitle2,
                alba.albaProfileContent2
        )).from(alba)
                .where(alba.albaApplyStartDate.before(localDateTime).and(alba.albaApplyEndDate.after(localDateTime)))
                .orderBy(alba.albaPrice.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    //    알바게시글 작성
    public void saveAll(AlbaDTO albaDTO) {
       albaRepository.save(albaDTO.toEntity());}

    // 최신순
    public List<AlbaDTO> showByRecent(Pageable pageable) {
        LocalDateTime localDateTime = LocalDateTime.now();
        return jpaQueryFactory.select(new QAlbaDTO(
                alba.albaId,
                alba.albaTitle,
                alba.albaImage,
                alba.albaTitleOne,
                alba.albaApplyStartDate,
                alba.albaApplyEndDate,
                alba.albaWorkDate,
                alba.albaApplyCount,
                alba.albaApplyTotalCount,
                alba.albaAddress,
                alba.albaPrice,
                alba.albaMainTitle,
                alba.albaMainContent,
                alba.albaStrongTitle1,
                alba.albaStrongContent1,
                alba.albaStrongTitle2,
                alba.albaStrongContent2,
                alba.albaStrongTitle3,
                alba.albaStrongContent3,
                alba.albaBannerTitle,
                alba.albaBannerOne,
                alba.albaTextTitle,
                alba.albaText,
                alba.albaProfileTitle1,
                alba.albaProfileContent1,
                alba.albaProfileTitle2,
                alba.albaProfileContent2
        )).from(alba)
                .where(alba.albaApplyStartDate.before(localDateTime).and(alba.albaApplyEndDate.after(localDateTime)))
                .orderBy(alba.albaApplyStartDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    // 마감순
    public List<AlbaDTO> showListByEndDate(Pageable pageable) {
        LocalDateTime localDateTime = LocalDateTime.now();
        return jpaQueryFactory.select(new QAlbaDTO(
                alba.albaId,
                alba.albaTitle,
                alba.albaImage,
                alba.albaTitleOne,
                alba.albaApplyStartDate,
                alba.albaApplyEndDate,
                alba.albaWorkDate,
                alba.albaApplyCount,
                alba.albaApplyTotalCount,
                alba.albaAddress,
                alba.albaPrice,
                alba.albaMainTitle,
                alba.albaMainContent,
                alba.albaStrongTitle1,
                alba.albaStrongContent1,
                alba.albaStrongTitle2,
                alba.albaStrongContent2,
                alba.albaStrongTitle3,
                alba.albaStrongContent3,
                alba.albaBannerTitle,
                alba.albaBannerOne,
                alba.albaTextTitle,
                alba.albaText,
                alba.albaProfileTitle1,
                alba.albaProfileContent1,
                alba.albaProfileTitle2,
                alba.albaProfileContent2
        )).from(alba)
                .where(alba.albaApplyStartDate.before(localDateTime).and(alba.albaApplyEndDate.after(localDateTime)))
                .orderBy(alba.albaApplyEndDate.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }




}