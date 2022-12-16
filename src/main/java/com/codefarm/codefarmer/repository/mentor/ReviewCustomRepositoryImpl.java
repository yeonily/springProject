package com.codefarm.codefarmer.repository.mentor;

import com.codefarm.codefarmer.domain.board.QReplyDTO;
import com.codefarm.codefarmer.domain.mentor.QReviewDTO;
import com.codefarm.codefarmer.domain.mentor.ReviewDTO;
import com.codefarm.codefarmer.entity.mentor.QReview;
import com.codefarm.codefarmer.entity.mentor.Review;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.codefarm.codefarmer.entity.mentor.QReview.review;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ReviewCustomRepositoryImpl implements ReviewCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Review> findAll() {
        return jpaQueryFactory.selectFrom(review).fetch();
    }

    @Override
    public List<ReviewDTO> findAllList(Long mentorBoardId) {
        return jpaQueryFactory.select(new QReviewDTO(
                review.reviewId,
                review.member.memberId,
                review.mentorBoard.mentorBoardId,
                review.member.memberNickname,
                review.reviewContent,
                review.reviewStar,
                review.createdDate,
                review.updatedDate
        ))
                .from(review)
                .where(review.mentorBoard.mentorBoardId.eq(mentorBoardId))
                .orderBy(review.updatedDate.desc())
                .fetch();
    }


    @Override
    public List<Review> findByNickname(String memberNickname, Pageable pageable) {
        return jpaQueryFactory.select(review)
                .from(review)
                .where(review.member.memberNickname.contains(memberNickname))
                .orderBy(review.reviewId.desc())
                .fetch();
    }

    @Override
    public Integer countByMemberNickname(String memberNickname) {
        return Math.toIntExact(jpaQueryFactory.select(review.count())
                .from(review)
                .where(review.member.memberNickname.contains(memberNickname))
                .fetchOne());
    }


    // 댓글 페이징처리
    public Page<ReviewDTO> findAll(Pageable pageable, Long mentorBoardId){
        List<ReviewDTO> reviews = jpaQueryFactory.select(new QReviewDTO(
                review.reviewId,
                review.member.memberId,
                review.mentorBoard.mentorBoardId,
                review.member.memberNickname,
                review.reviewContent,
                review.reviewStar,
                review.createdDate,
                review.updatedDate
                )).from(review)
                .where(review.mentorBoard.mentorBoardId.eq(mentorBoardId))
                .orderBy(review.reviewId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize()).fetch();

        long total = jpaQueryFactory.selectFrom(review)
                .where(review.mentorBoard.mentorBoardId.eq(mentorBoardId))
                .fetch().size();

        return new PageImpl<>(reviews, pageable, total);
    }
}
