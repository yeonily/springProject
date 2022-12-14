package com.codefarm.codefarmer.domain.mentor;

import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.entity.mentor.Mentor;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@NoArgsConstructor
@Data
public class MentorDTO {
    private Long mentorId;
    private Long memberId;
    private Member farmerType;
    private String mentorCrop;
    private String mentorYear;

    public Mentor toEntity(){
        return Mentor.builder()
                .mentorCrop(mentorCrop)
                .mentorYear(mentorYear)
                .build();
    }

    @QueryProjection
    public MentorDTO(Long mentorId, Long memberId, Member farmerType, String mentorCrop, String mentorYear) {
        this.mentorId = mentorId;
        this.memberId = memberId;
        this.farmerType = farmerType;
        this.mentorCrop = mentorCrop;
        this.mentorYear = mentorYear;
    }
}