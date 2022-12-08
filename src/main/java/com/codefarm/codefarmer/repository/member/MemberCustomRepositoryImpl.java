package com.codefarm.codefarmer.repository.member;

import com.codefarm.codefarmer.entity.alba.Alba;
import com.codefarm.codefarmer.entity.alba.QAlba;
import com.codefarm.codefarmer.entity.board.Board;
import com.codefarm.codefarmer.entity.inquire.Inquire;
import com.codefarm.codefarmer.entity.inquire.QInquire;
import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.entity.member.QMember;
import com.codefarm.codefarmer.entity.program.Program;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.codefarm.codefarmer.entity.alba.QAlba.alba;
import static com.codefarm.codefarmer.entity.board.QBoard.board;
import static com.codefarm.codefarmer.entity.inquire.QInquire.*;
import static com.codefarm.codefarmer.entity.member.QMember.*;
import static com.codefarm.codefarmer.entity.program.QProgram.program;

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

    @Override
    public List<Alba> findMyAlba(Long memberId) {
        return jpaQueryFactory.select(alba)
                .from(alba).join(alba.member)
                .where(alba.member.memberId.eq(memberId))
                .fetchJoin().fetch();
    }

    @Override
    public List<Program> findMyProgram(Long memberId) {
        return  jpaQueryFactory.select(program)
                .from(program).join(program.member)
                .where(program.member.memberId.eq(memberId))
                .fetchJoin().fetch();
    }

    @Override
    public List<Board> findMyBoard(Long memberId) {
        return jpaQueryFactory.select(board)
                .from(board).join(board.member)
                .where(board.member.memberId.eq(memberId))
                .fetchJoin().fetch();
    }

    @Override
    public List<Inquire> findMyInquire(Long memberId) {
        return jpaQueryFactory.select(inquire)
                .from(inquire).join(inquire.member)
                .where(inquire.member.memberId.eq(memberId))
                .fetchJoin().fetch();
    }


}


