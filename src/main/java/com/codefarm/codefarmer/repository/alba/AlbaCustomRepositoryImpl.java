package com.codefarm.codefarmer.repository.alba;

import com.codefarm.codefarmer.entity.alba.Alba;
import com.codefarm.codefarmer.entity.alba.QAlba;
import com.codefarm.codefarmer.entity.member.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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



}
