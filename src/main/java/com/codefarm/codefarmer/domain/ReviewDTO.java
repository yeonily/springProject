package com.codefarm.codefarmer.domain;

import com.codefarm.codefarmer.entity.Board;
import com.codefarm.codefarmer.entity.Member;
import com.codefarm.codefarmer.entity.MentorBoard;
import com.codefarm.codefarmer.entity.Review;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Component
@NoArgsConstructor
@Data
public class ReviewDTO {
    private Long reviewId;
    private Member member;
    private MentorBoard mentorBoard;
    private String reviewContent;
    private int reviewStar;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;

    @QueryProjection
    public ReviewDTO(Long reviewId, Member member, MentorBoard mentorBoard, String reviewContent, int reviewStar, LocalDateTime createdDate, LocalDateTime updateDate) {
        this.reviewId = reviewId;
        this.member = member;
        this.mentorBoard = mentorBoard;
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