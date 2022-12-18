package com.codefarm.codefarmer.repository.alba;

import com.codefarm.codefarmer.domain.alba.AlbaDTO;
import com.codefarm.codefarmer.domain.alba.QAlbaDTO;
import com.codefarm.codefarmer.entity.admin.Criteria;
import com.codefarm.codefarmer.entity.alba.Alba;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.codefarm.codefarmer.entity.alba.QAlba.*;

@Repository
@RequiredArgsConstructor
public class AlbaCustomRepositoryImpl implements AlbaCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Alba> findByLatest() {
        return queryFactory.selectFrom(alba).orderBy(alba.albaId.desc()).offset(0).limit(8).fetch();
    }

    // 최신순
    @Override
    public Page<AlbaDTO> findByAlbaNewList(Pageable pageable){
        LocalDateTime localDateTime = LocalDateTime.now();

        List<AlbaDTO> albas = queryFactory.select(new QAlbaDTO(
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
                alba.albaProfileContent2,
                alba.member.memberId
        )).from(alba)
                .where(alba.albaApplyStartDate.before(localDateTime).and(alba.albaApplyEndDate.after(localDateTime)))
                .orderBy(alba.albaApplyStartDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory.selectFrom(alba).where(alba.albaApplyStartDate.before(localDateTime).and(alba.albaApplyEndDate.after(localDateTime))).fetch().size();

        return new PageImpl<>(albas, pageable, total);
    }

    // 시급순
   @Override
    public Page<AlbaDTO> findByAlbaPayList(Pageable pageable){
        LocalDateTime localDateTime = LocalDateTime.now();

        List<AlbaDTO> albas = queryFactory.select(new QAlbaDTO(
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
                alba.albaProfileContent2,
                alba.member.memberId
        )).from(alba)
                .where(alba.albaApplyStartDate.before(localDateTime).and(alba.albaApplyEndDate.after(localDateTime)))
                .orderBy(alba.albaPrice.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory.selectFrom(alba).where(alba.albaApplyStartDate.before(localDateTime).and(alba.albaApplyEndDate.after(localDateTime))).fetch().size();

        return new PageImpl<>(albas, pageable, total);
    }

    // 마감순
    @Override
    public Page<AlbaDTO> findByAlbaEndList(Pageable pageable) {
        LocalDateTime localDateTime = LocalDateTime.now();

        List<AlbaDTO> albas = queryFactory.select(new QAlbaDTO(
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
                alba.albaProfileContent2,
                alba.member.memberId
        )).from(alba)
                .where(alba.albaApplyStartDate.before(localDateTime).and(alba.albaApplyEndDate.after(localDateTime)))
                .orderBy(alba.albaApplyEndDate.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory.selectFrom(alba).where(alba.albaApplyStartDate.before(localDateTime).and(alba.albaApplyEndDate.after(localDateTime))).fetch().size();

        return new PageImpl<>(albas, pageable, total);
    }

    @Override
    public List<Alba> findByAlbaLikeMemberName(String memberName) {
        return queryFactory.select(alba)
                .from(alba)
                .where(alba.member.memberName.contains(memberName))
                .orderBy(alba.albaId.desc())
                .fetch();
    }

    @Override
    public Integer countByMemberName(String memberName) {
        return Math.toIntExact(queryFactory.select(alba.count())
                .from(alba)
                .where(alba.member.memberName.contains(memberName))
                .fetchOne());
    }

    @Override
    public List<Alba> showAdmin() {
        return queryFactory.selectFrom(alba)
                .orderBy(alba.albaId.desc())
                .limit(5)
                .fetch();
    }
}
