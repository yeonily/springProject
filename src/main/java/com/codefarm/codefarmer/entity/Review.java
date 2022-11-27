package com.codefarm.codefarmer.entity;

import com.codefarm.codefarmer.domain.ReviewDTO;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Table(name = "TBL_REVIEW")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends Period{
    @Id @GeneratedValue
    private Long reviewId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MENTOR_BOARD_ID")
    private MentorBoard mentorBoard;

    @NotNull
    private String reviewContent;
    @ColumnDefault("5")
    private int reviewStar;

    public void update(ReviewDTO reviewDTO){
        this.reviewContent = reviewContent;
        this.reviewStar = reviewStar;
    }

    @Builder
    public Review(String reviewContent, int reviewStar) {
        this.reviewContent = reviewContent;
        this.reviewStar = reviewStar;
    }
}
