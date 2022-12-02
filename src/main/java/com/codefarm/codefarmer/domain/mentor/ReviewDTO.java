package com.codefarm.codefarmer.domain.mentor;

import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.entity.mentor.MentorBoard;
import com.codefarm.codefarmer.entity.mentor.Review;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@NoArgsConstructor
@Data
public class ReviewDTO {
    private Long reviewId;
    private Member memberId;
    private MentorBoard mentorBoardId;
    private String reviewContent;
    private int reviewStar;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;

    @QueryProjection
    public ReviewDTO(Long reviewId, Member member, MentorBoard mentorBoard, String reviewContent, int reviewStar, LocalDateTime createdDate, LocalDateTime updateDate) {
        this.reviewId = reviewId;
        this.memberId = member;
        this.mentorBoardId = mentorBoard;
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