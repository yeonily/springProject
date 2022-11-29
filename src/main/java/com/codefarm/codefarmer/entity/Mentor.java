package com.codefarm.codefarmer.entity;

import com.codefarm.codefarmer.domain.MentorDTO;
import com.codefarm.codefarmer.type.FarmerType;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_MENTOR")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Mentor extends Period{
    @Id @GeneratedValue
    private Long mentorId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @NotNull
    private String mentorCrop;
    @NotNull
    private String mentorYear;

    @Enumerated(EnumType.STRING)
    private FarmerType farmerType;

    public void update(MentorDTO mentorDTO){
        this.mentorCrop = mentorDTO.getMentorCrop();
        this.mentorYear = mentorDTO.getMentorYear();
    }

    public void changeMember(Member member){
        this.member = member;
    }

    public void getType(FarmerType farmerType){
        this.farmerType = farmerType;
    }

    @Builder
    public Mentor(String mentorCrop, String mentorYear, FarmerType farmerType) {
        this.mentorCrop = mentorCrop;
        this.mentorYear = mentorYear;
        this.farmerType = farmerType;
    }
}