package com.codefarm.codefarmer.repository.member;

import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.entity.member.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.codefarm.codefarmer.entity.member.QMember.*;

@Repository
@RequiredArgsConstructor
public class MemberCustomRepositoryImpl implements MemberCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public Integer checkNick(String nickname) {
        return Math.toIntExact(jpaQueryFactory.select(member.memberNickname.count())
                .from(member)
                .where(member.memberNickname.eq(nickname))
                .fetchOne());
    }

    @Override
    public List<Member> findMyProgramApplyers(Long memberId) {


        /*
         * SELECT * FROM TBL_MEMBER_PROGRAM MP JOIN TBL_PROGRAM P
         * ON P.MEMBER_ID = 1 AND MP.PROGRAM_ID = P.PROGRAM_ID
         *
         *
         * */

//    @Override
//    public List<User> findMyProgramApplyers(Long memberId) {
//        QProgram subProgram = new QProgram("subProgram");
//        return queryFactory.select(Projections.fields(QMemberProgram.class,
//                        QMemberProgram.memberProgram.program.programId.as("mp"),
//                        ExpressionUtils.as(
//                                JPAExpressions.select(QProgram.program.programId)
//                                        .from(QProgram.program)
//                                        .where(QProgram.program.member.memberId.eq(memberId)),
//                                "p")
//                )
//                .from(QMemberProgram.memberProgram)
//                .fetch();
//    }


//    @Override
//    public LatestOrderDto findLatestOrderHistoryByLimit(Long orderId) {
//        return from(orders)
//                .leftJoin(ordersHistory)
//                .on(ordersHistory.orders.eq(orders))
//                .where(orders.id.eq(orderId))
//                .select(
//                        Projections.constructor(LatestOrderDto.class,
//                                orders,
//                                Expressions.as(
//                                        from(ordersHistory)
//                                                .orderBy(ordersHistory.createdAt.desc())
//                                                .limit(1)
//                                        , "ordersHistory")
//                        )
//                )
//                .fetchFirst();
//    }
        return null;
    }

    @Override
    public Integer checkOauth(String oauthId) {
        return Math.toIntExact(jpaQueryFactory.select(member.memberOauthId.count())
                .from(member)
                .where(member.memberOauthId.eq(oauthId))
                .fetchOne());
    }


}


