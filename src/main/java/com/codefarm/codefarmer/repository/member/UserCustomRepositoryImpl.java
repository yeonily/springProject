package com.codefarm.codefarmer.repository.member;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.codefarm.codefarmer.entity.member.QUser.user;

@Repository
@RequiredArgsConstructor
public class UserCustomRepositoryImpl implements UserCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Integer checkUserNick(String nickname) {
        return Math.toIntExact(queryFactory.select(user.memberNickname.count())
                .from(user)
                .where(user.memberNickname.eq(nickname))
                .fetchOne());
    }


}
