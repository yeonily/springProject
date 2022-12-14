package com.codefarm.codefarmer.service.Mentor;


import com.codefarm.codefarmer.domain.mentor.ReviewDTO;
import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.entity.mentor.MentorBoard;
import com.codefarm.codefarmer.entity.mentor.Review;
import com.codefarm.codefarmer.repository.member.MemberRepository;
import com.codefarm.codefarmer.repository.mentor.MentorBoardRepository;
import com.codefarm.codefarmer.repository.mentor.ReviewRepository;
import com.codefarm.codefarmer.service.mentor.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class ReviewServiceTest {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MentorBoardRepository mentorBoardRepository;

    @Autowired
    private ReviewRepository reviewRepository;


//    해당 보드에 리뷰댓글 작성하기
    @Test
    public void reivewAddTest(){
        ReviewDTO reviewDTO = new ReviewDTO();

        Optional<Member> findMember = memberRepository.findById(112L);
        Optional<MentorBoard> findMentorBoard = mentorBoardRepository.findById(294L);

        reviewDTO.setReviewContent("리뷰댓글입니다.");
        reviewDTO.setReviewStar(3);

        Review review = reviewDTO.toEntity();
        review.changeMember(findMember.get());
        review.changeMentorBoard(findMentorBoard.get());

        reviewService.reviewAdd(reviewDTO);
    }
}
