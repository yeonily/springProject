package com.codefarm.codefarmer.domain;

import com.codefarm.codefarmer.entity.Farmer;
import com.codefarm.codefarmer.entity.Member;
import com.codefarm.codefarmer.entity.Mentor;
import com.codefarm.codefarmer.entity.Program;
import com.codefarm.codefarmer.type.FarmerType;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Component
@NoArgsConstructor
@Data
public class MentorDTO {
    private Long mentorId;
    private Member memberId;
    private FarmerType farmerType;
    private String mentorCrop;
    private String mentorYear;

    public Mentor toEntity(){
        return Mentor.builder()
                .mentorCrop(mentorCrop)
                .mentorYear(mentorYear)
                .farmerType(farmerType)
                .build();
    }
}