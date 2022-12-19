package com.codefarm.codefarmer.repository.mentor;


import com.codefarm.codefarmer.domain.mentor.MentorBoardDTO;
import com.codefarm.codefarmer.domain.mentor.QMentorBoardDTO;
import com.codefarm.codefarmer.entity.mentor.Mentor;
import com.codefarm.codefarmer.entity.mentor.MentorBoard;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.codefarm.codefarmer.entity.mentor.QMentor.mentor;
import static com.codefarm.codefarmer.entity.mentor.QMentorBoard.mentorBoard;

@Repository
@RequiredArgsConstructor
@Slf4j
public class MentorCustomRepositoryImpl implements MentorCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public List<MentorBoard> findAllQDSL() {
        return jpaQueryFactory.selectFrom(mentorBoard).fetch();
    }

    @Override
    public Slice<MentorBoard> findAllSlice(Pageable pageable) {
        List<MentorBoard> mentorBoardList = jpaQueryFactory.selectFrom(mentorBoard)
                .orderBy(mentorBoard.updatedDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize()+1)
                .fetch();

        ArrayList<MentorBoard> content = (ArrayList<MentorBoard>)mentorBoardList;

        boolean hasNext = false;
        if(content.size() > pageable.getPageSize()){
            content.remove(pageable.getPageSize());
            hasNext=true;
        }

        return new SliceImpl<>(content,pageable,hasNext);
    }

    @Override
    public Slice<MentorBoardDTO> findAllSliceDTO(Pageable pageable) {
        List<MentorBoardDTO> mentorBoardList = jpaQueryFactory.select(
                new QMentorBoardDTO(mentorBoard.mentorBoardId, mentorBoard.mentorCareer,
                        mentorBoard.mentorExCareer, mentorBoard.mentorStrongTitle1, mentorBoard.mentorStrongContent1, mentorBoard.mentorStrongTitle2, mentorBoard.mentorStrongContent2,
                        mentorBoard.mentorStrongTitle3, mentorBoard.mentorStrongContent3, mentorBoard.mentorTitle, mentorBoard.mentorTitleSub, mentorBoard.mentorTextTitle,
                        mentorBoard.mentorTextContent,
                        mentorBoard.mentor.mentorCrop
                )).from(mentorBoard)
                .orderBy(mentorBoard.updatedDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize()+1)
                .fetch();
        ArrayList<MentorBoardDTO> content = (ArrayList<MentorBoardDTO>)mentorBoardList;

        boolean hasNext = false;
        if(content.size() > pageable.getPageSize()){
            content.remove(pageable.getPageSize());
            hasNext=true;
        }
        return new SliceImpl<>(content,pageable,hasNext);
    }

//   멤버에게 멘토 아이디가 있으면 홍보글을 작성 할수 있는 권한이 생김.
//    public void findMentorId(Long MentorId){
//        List<MentorBoardDTO> mentorBoardDTOList = jpaQueryFactory.select(
//                new QMentorBoardDTO(mentorBoard.mentorBoardId, mentorBoard.mentorCareer,
//                        mentorBoard.mentorExCareer, mentorBoard.mentorStrongTitle1, mentorBoard.mentorStrongContent1, mentorBoard.mentorStrongTitle2, mentorBoard.mentorStrongContent2,
//                        mentorBoard.mentorStrongTitle3, mentorBoard.mentorStrongContent3, mentorBoard.mentorTitle, mentorBoard.mentorTitleSub, mentorBoard.mentorTextTitle,
//                        mentorBoard.mentorTextContent,
//                        mentorBoard.mentor.mentorCrop
//                        )).from(mentorBoard)
//                        .where(mentorBoard.mentor.mentorId.eq(MentorId))
//                        .fetch();
//        }

//.join(QMentor.mentor).fetchJoin()

    @Override
    public List<Mentor> ShowAllMentor(String keyword, String searchText) {
        return jpaQueryFactory.selectFrom(mentor)
                .where(
                        eqName(keyword, searchText),
                        eqNickname(keyword, searchText),
                        eqAddress(keyword, searchText)
                )
                .orderBy(mentor.mentorId.desc())
                .fetch();
    }

    @Override
    public Integer searchCountByMentor(String keyword, String searchText) {
        return Math.toIntExact(jpaQueryFactory.select(mentor.count())
                .from(mentor)
                .where(
                        eqName(keyword, searchText),
                        eqNickname(keyword, searchText),
                        eqAddress(keyword, searchText)
                )
                .fetchOne());
    }

    private BooleanExpression eqName (String keyword, String searchText) {
        if (keyword.equals("n")) {
            return mentor.member.memberName.contains(searchText);
        }
        return null;
    }
    private BooleanExpression eqNickname (String keyword, String searchText) {
        if (keyword.equals("nn")) {
            return mentor.member.memberNickname.contains(searchText);
        }
        return null;
    }
    private BooleanExpression eqAddress (String keyword, String searchText) {
        if (keyword.equals("l")) {
            return mentor.member.memberLocation.contains(searchText);
        }
        return null;
    }
}
