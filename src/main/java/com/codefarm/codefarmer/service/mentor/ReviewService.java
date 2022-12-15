package com.codefarm.codefarmer.service.mentor;

import com.codefarm.codefarmer.domain.mentor.MentorCriteria;
import com.codefarm.codefarmer.domain.mentor.ReviewDTO;
import com.codefarm.codefarmer.entity.mentor.Review;
import com.codefarm.codefarmer.repository.member.MemberRepository;
import com.codefarm.codefarmer.repository.mentor.MentorBoardRepository;
import com.codefarm.codefarmer.repository.mentor.ReviewCustomRepositoryImpl;
import com.codefarm.codefarmer.repository.mentor.ReviewRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final JPAQueryFactory jpaQueryFactory;
    private final ReviewRepository reviewRepository;
    private final MentorBoardRepository mentorBoardRepository;
    private final MemberRepository memberRepository;
    private final ReviewCustomRepositoryImpl reviewCustomRepositoryImpl;

    //    해당 보드에 리뷰댓글 추가하기
    public void reviewAdd(ReviewDTO reviewDTO){
        Review review = reviewDTO.toEntity();
        review.changeMentorBoard(mentorBoardRepository.findById(reviewDTO.getMentorBoardId()).get());
        review.changeMember(memberRepository.findById(reviewDTO.getMemberId()).get());
        reviewRepository.save(review);
    }

    //    리뷰 단 사람 닉넴 갖고오기
    public String showReviewNickName(Long reviewId){
        Review review = reviewRepository.findById(reviewId).get();

        return review.getMember().getMemberNickname();
    }

    //    해당 보드에있는 리뷰댓글 특정 개수
    public Long getTotal(Long mentorBoardId){
        return reviewRepository.countByMentorBoardMentorBoardId(mentorBoardId);
    }

    //    내가 작성한 리뷰댓글 지우기
    public void removeReview(Long reviewId){
        reviewRepository.deleteById(reviewId);
    }


    //  댓글 페이징 처리
    public Page<ReviewDTO> showReview(Pageable pageable, MentorCriteria criteria, Long mentorBoardId){
        return criteria.getKeyword().equals("null") ? reviewCustomRepositoryImpl.findAll(pageable, mentorBoardId) : null;
    }

}
