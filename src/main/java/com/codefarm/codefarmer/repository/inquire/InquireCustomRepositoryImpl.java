package com.codefarm.codefarmer.repository.inquire;

import com.codefarm.codefarmer.entity.inquire.Inquire;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.codefarm.codefarmer.entity.inquire.QInquire.inquire;

@Repository
@RequiredArgsConstructor
public class InquireCustomRepositoryImpl implements InquireCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Inquire> findByInquireLikeMemberNickname(String memberNickname) {
        return jpaQueryFactory.select(inquire)
                .from(inquire)
                .where(inquire.member.memberNickname.contains(memberNickname))
                .orderBy(inquire.inquireId.desc())
                .fetch();
    }

    @Override
    public Integer countByMemberNickname(String memberNickname) {
        return Math.toIntExact(jpaQueryFactory.select(inquire.count())
                .from(inquire)
                .where(inquire.member.memberNickname.contains(memberNickname))
                .fetchOne());
    }
}
