package com.codefarm.codefarmer.repository.mentor;

import com.codefarm.codefarmer.entity.mentor.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

//    해당 멘토상세페이지 리뷰에 달려있는 댓글 총 수
    public Long countByMentorBoardMentorBoardId(@Param("reviewId") Long reviewId);

//    멘토 내용 갖고오기 DESC
//    @Query()
//    public List<Review> findByReviewContent(@Param("reviewContent") String reviewCount)


    @Query("select count(r) from Review r")
    public int countByReview();

    public Page<Review> findByReviewContentContaining(String reviewContent, Pageable pageable);
}
