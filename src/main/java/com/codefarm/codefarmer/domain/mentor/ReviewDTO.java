package com.codefarm.codefarmer.domain.mentor;

import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.entity.mentor.MentorBoard;
import com.codefarm.codefarmer.entity.mentor.Review;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@NoArgsConstructor
@Data
public class ReviewDTO {
    private Long reviewId;
    private Long memberId;
    private Long mentorBoardId;
    private String memberNickName;
    private String reviewContent;
    private int reviewStar;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;

    private List<ReviewDTO> reviewList;

    private int endPage;

    private int totalCount;

    private int totalStar;

    @QueryProjection
    public ReviewDTO(Long reviewId, Long memberId, Long mentorBoardId, String memberNickName, String reviewContent, int reviewStar, LocalDateTime createdDate, LocalDateTime updateDate) {
        this.reviewId = reviewId;
        this.memberId = memberId;
        this.mentorBoardId = mentorBoardId;
        this.memberNickName = memberNickName;
        this.reviewContent = reviewContent;
        this.reviewStar = reviewStar;
        this.createdDate = createdDate;
        this.updateDate = updateDate;
    }





    public Review toEntity(){
        return Review.builder()
                .reviewContent(reviewContent)
                .reviewStar(reviewStar)
                .build();
    }
}