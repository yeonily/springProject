package com.codefarm.codefarmer.repository.member;

import com.codefarm.codefarmer.entity.member.QUser;
import com.codefarm.codefarmer.entity.member.User;
import com.codefarm.codefarmer.entity.program.QMemberProgram;
import com.codefarm.codefarmer.entity.program.QProgram;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.codefarm.codefarmer.entity.member.QUser.user;

@Repository
@RequiredArgsConstructor
public class FarmerCustomRepositoryImpl implements FarmerCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<User> findMyProgramApplyers(Long memberId) {
        return null;
    }

//    SELECT * FROM TBL_MEMBER_PROGRAM tmp
//    JOIN (
//                    SELECT PROGRAM_ID FROM TBL_PROGRAM P WHERE MEMBER_ID = 1) JOINT
//    ON JOINT.PROGRAM_ID = TMP.PROGRAM_ID;


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
}
