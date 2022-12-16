package com.codefarm.codefarmer.repository.program;

import com.codefarm.codefarmer.entity.program.MemberProgram;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.codefarm.codefarmer.entity.program.QMemberProgram.memberProgram;

@Repository
@RequiredArgsConstructor
public class MemberProgramCustomRepositoryImpl implements MemberProgramCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<MemberProgram> findByMemberProgram(String keyword, String searchText) {
        return jpaQueryFactory.selectFrom(memberProgram)
                .where(
                        eqProgramTitle(keyword, searchText),
                        eqMemberName(keyword, searchText),
                        eqProgramLocation(keyword, searchText)
                )
                .orderBy(memberProgram.programApplyId.desc())
                .fetch();
    }

    @Override
    public Integer countByMemeberProgramSearch(String keyword, String searchText) {
        return Math.toIntExact(jpaQueryFactory.select(memberProgram.count())
                .from(memberProgram)
                .where(
                        eqProgramTitle(keyword, searchText),
                        eqMemberName(keyword, searchText),
                        eqProgramLocation(keyword, searchText)
                )
                .fetchOne());
    }

    private BooleanExpression eqProgramTitle(String keyword, String searchText) {
        if (keyword.equals("p")) {
            return memberProgram.program.programTitle.contains(searchText);
        }
        return null;
    }
    private BooleanExpression eqMemberName(String keyword, String searchText) {
        if (keyword.equals("n")) {
            return memberProgram.member.memberName.contains(searchText);
        }
        return null;
    }
    private BooleanExpression eqProgramLocation(String keyword, String searchText) {
        if (keyword.equals("l")) {
            return memberProgram.programApplyLocation.contains(searchText);
        }
        return null;
    }
}
