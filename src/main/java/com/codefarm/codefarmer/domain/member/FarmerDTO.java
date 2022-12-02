package com.codefarm.codefarmer.domain.member;

import com.codefarm.codefarmer.entity.member.Farmer;
import com.codefarm.codefarmer.type.FarmerType;
import com.codefarm.codefarmer.type.Oauth;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@Data
public class FarmerDTO {
    private Long memberId;
    private String memberName;
    private String memberNickname;
    private String memberPhone;
    private String memberLocation;
    private String memberBirth;
    private String memberEmail;
    private Oauth memberOauth;
    private FarmerType farmerType;


   public Farmer toEntity(){
       return Farmer.builder()
               .farmerType(farmerType)
               .memberBirth(memberBirth)
               .memberEmail(memberEmail)
               .memberLocation(memberLocation)
               .memberName(memberName)
               .memberNickname(memberNickname)
               .memberOauth(memberOauth)
               .memberPhone(memberPhone)
               .build();
   }

    @QueryProjection
    public FarmerDTO(Long memberId, String memberName, String memberNickname, String memberPhone, String memberLocation, String memberBirth, String memberEmail, Oauth memberOauth) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberNickname = memberNickname;
        this.memberPhone = memberPhone;
        this.memberLocation = memberLocation;
        this.memberBirth = memberBirth;
        this.memberEmail = memberEmail;
        this.memberOauth = memberOauth;
    }
}
