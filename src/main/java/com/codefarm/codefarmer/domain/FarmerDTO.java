package com.codefarm.codefarmer.domain;

import com.codefarm.codefarmer.entity.Farmer;
import com.codefarm.codefarmer.type.FarmerType;
import com.codefarm.codefarmer.type.Oauth;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

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
}
