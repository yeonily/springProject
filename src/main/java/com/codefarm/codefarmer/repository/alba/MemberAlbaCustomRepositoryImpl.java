package com.codefarm.codefarmer.repository.alba;

import com.codefarm.codefarmer.entity.alba.MemberAlba;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.codefarm.codefarmer.entity.alba.QMemberAlba.memberAlba;

@Repository
@RequiredArgsConstructor
public class MemberAlbaCustomRepositoryImpl implements MemberAlbaCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<MemberAlba> findByMemberAlba(String keyword, String searchText) {
        return jpaQueryFactory.selectFrom(memberAlba)
                .where(
                        eqAlbaTitle(keyword, searchText),
                        eqAlbaName(keyword, searchText),
                        eqAlbaNickname(keyword, searchText),
                        eqAlbaAddress(keyword, searchText)
                )
                .orderBy(memberAlba.albaApplyId.desc())
                .fetch();
    }

    @Override
    public Integer countByMemberAlbaSearch(String keyword, String searchText) {
        return Math.toIntExact(jpaQueryFactory.select(memberAlba.count())
            .from(memberAlba)
            .where(
                    eqAlbaTitle(keyword, searchText),
                    eqAlbaName(keyword, searchText),
                    eqAlbaNickname(keyword, searchText),
                    eqAlbaAddress(keyword, searchText)
            )
            .fetchOne());
    }

    private BooleanExpression eqAlbaTitle(String keyword, String searchText) {
        if (keyword.equals("j")) {
            return memberAlba.alba.albaTitle.contains(searchText);
        }
        return null;
    }
    private BooleanExpression eqAlbaName(String keyword, String searchText) {
        if (keyword.equals("p")) {
            return memberAlba.member.memberName.contains(searchText);
        }
        return null;
    }
    private BooleanExpression eqAlbaNickname(String keyword, String searchText) {
        if (keyword.equals("n")) {
            return memberAlba.member.memberNickname.contains(searchText);
        }
        return null;
    }
    private BooleanExpression eqAlbaAddress(String keyword, String searchText) {
        if (keyword.equals("a")) {
            return memberAlba.alba.albaAddress.contains(searchText);
        }
        return null;
    }
}
