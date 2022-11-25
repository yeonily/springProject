package com.codefarm.codefarmer.entity;

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

    @Column(nullable = false)
    private String reviewContent;
    @ColumnDefault("5")
    private int reviewStar;

    @Builder
    public Review(MentorBoard mentorBoard, String reviewContent, int reviewStar) {
        this.mentorBoard = mentorBoard;
        this.reviewContent = reviewContent;
        this.reviewStar = reviewStar;
    }
}
