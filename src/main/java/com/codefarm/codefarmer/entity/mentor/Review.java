package com.codefarm.codefarmer.entity.mentor;

import com.codefarm.codefarmer.domain.mentor.ReviewDTO;
import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.entity.period.Period;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "TBL_REVIEW")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends Period {
    @Id @GeneratedValue
    private Long reviewId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MENTOR_BOARD_ID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private MentorBoard mentorBoard;

    @NotNull
    private String reviewContent;
    @ColumnDefault("5")
    private int reviewStar;

    public void update(ReviewDTO reviewDTO){
        this.reviewContent = reviewDTO.getReviewContent();
        this.reviewStar = reviewDTO.getReviewStar();
    }
    public void changeMember(Member member){
        this.member = member;
    }

    public void changeMentorBoard(MentorBoard mentorBoard){
        this.mentorBoard = mentorBoard;
    }


    @Builder
    public Review(String reviewContent, int reviewStar) {
        this.reviewContent = reviewContent;
        this.reviewStar = reviewStar;
    }
}
