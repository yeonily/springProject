package com.codefarm.codefarmer.repository.mentor;

import com.codefarm.codefarmer.entity.mentor.MentorBoard;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.codefarm.codefarmer.entity.mentor.QMentorBoard.mentorBoard;

@Repository
@RequiredArgsConstructor
public class MentorBoardCustomRepositoryImpl implements MentorBoardCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<MentorBoard> findByMentorBoardSearch(String keyword, String searchText) {
        return jpaQueryFactory.selectFrom(mentorBoard)
                .where(
                        eqMentorBoardTitle(keyword, searchText),
                        eqMentorBoardWriter(keyword, searchText),
                        eqMentorBoardCrop(keyword, searchText),
                        eqMentorBoardLocation(keyword, searchText)
                )
                .orderBy(mentorBoard.mentorBoardId.desc())
                .fetch();
    }

    @Override
    public Integer searchCountByMentorBoard(String keyword, String searchText) {
        return Math.toIntExact(jpaQueryFactory.select(mentorBoard.count())
                .from(mentorBoard)
                .where(
                        eqMentorBoardTitle(keyword, searchText),
                        eqMentorBoardWriter(keyword, searchText),
                        eqMentorBoardCrop(keyword, searchText),
                        eqMentorBoardLocation(keyword, searchText)
                )
                .fetchOne());
    }

    @Override
    public List<MentorBoard> showAdmin() {
        return jpaQueryFactory.selectFrom(mentorBoard)
                .orderBy(mentorBoard.mentorBoardId.desc())
                .limit(5)
                .fetch();
    }

    private BooleanExpression eqMentorBoardTitle (String keyword, String searchText){
        if (keyword.equals("t")) {
            return mentorBoard.mentorTitle.contains(searchText);
        }
        return null;
    }
    private BooleanExpression eqMentorBoardWriter (String keyword, String searchText){
        if (keyword.equals("w")) {
            return mentorBoard.member.memberNickname.contains(searchText);
        }
        return null;
    }
    private BooleanExpression eqMentorBoardCrop (String keyword, String searchText){
        if (keyword.equals("c")) {
            return mentorBoard.mentor.mentorCrop.contains(searchText);
        }
        return null;
    }
    private BooleanExpression eqMentorBoardLocation (String keyword, String searchText){
        if (keyword.equals("l")) {
            return mentorBoard.mentor.member.memberLocation.contains(searchText);
        }
        return null;
    }
}
