package com.codefarm.codefarmer.entity;

import com.codefarm.codefarmer.domain.MentorDTO;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_MENTOR")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@RequiredArgsConstructor
public class Mentor extends Period{
    @Id @GeneratedValue
    private Long mentorId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @Column(nullable = false)
    private String mentorCrop;
    @Column(nullable = false)
    private String mentorYear;

    public void update(MentorDTO mentorDTO){
        this.mentorCrop = mentorCrop;
        this.mentorYear = mentorYear;
    }

    @Builder
    public Mentor(String mentorCrop, String mentorYear) {
        this.mentorCrop = mentorCrop;
        this.mentorYear = mentorYear;
    }
}