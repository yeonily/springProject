package com.codefarm.codefarmer.domain;

import com.codefarm.codefarmer.entity.Farmer;
import com.codefarm.codefarmer.entity.User;
import com.codefarm.codefarmer.type.FarmerType;
import com.codefarm.codefarmer.type.Oauth;
import com.codefarm.codefarmer.type.UserType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@Data
public class UserDTO {
    private Long memberId;
    private String memberName;
    private String memberNickname;
    private String memberPhone;
    private String memberLocation;
    private String memberBirth;
    private String memberEmail;
    private Oauth memberOauth;
    private UserType userType;


   public User toEntity(){
       return User.builder()
               .userType(userType)
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
