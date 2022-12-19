package com.codefarm.codefarmer.repository.program;

import com.codefarm.codefarmer.entity.program.Program;
import com.codefarm.codefarmer.entity.program.QProgram;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.codefarm.codefarmer.entity.program.QProgram.program;

@Repository
@RequiredArgsConstructor
public class ProgramCustomRepositoryImpl implements ProgramCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Program> findByProgramSearch(String keyword, String searchText) {
        return jpaQueryFactory.selectFrom(program)
                .where(
                        eqProgramTitle(keyword, searchText),
                        eqProgramWriter(keyword, searchText),
                        eqProgramLocation(keyword, searchText)
                )
                .orderBy(program.programId.desc())
                .fetch();
    }

    @Override
    public Integer countByProgramSearch(String keyword, String searchText) {
        return Math.toIntExact(jpaQueryFactory.select(program.count())
                .from(program)
                .where(
                        eqProgramTitle(keyword, searchText),
                        eqProgramWriter(keyword, searchText),
                        eqProgramLocation(keyword, searchText)
                )
                .fetchOne());
    }



    @Override
    public List<Program> showAdmin() {
        return jpaQueryFactory.selectFrom(program)
                .orderBy(program.programId.desc())
                .limit(5)
                .fetch();
    }


    private BooleanExpression eqProgramTitle (String keyword, String searchText){
        if (keyword.equals("p")) {
            return program.programTitle.contains(searchText);
        }
        return null;
    }
    private BooleanExpression eqProgramWriter (String keyword, String searchText){
        if (keyword.equals("n")) {
            return program.member.memberNickname.contains(searchText);
        }
        return null;
    }
    private BooleanExpression eqProgramLocation (String keyword, String searchText){
        if (keyword.equals("a")) {
            return program.programLocation.contains(searchText);
        }
        return null;
    }
}
