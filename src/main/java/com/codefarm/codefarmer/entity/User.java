package com.codefarm.codefarmer.entity;

import com.codefarm.codefarmer.type.FarmerType;
import com.codefarm.codefarmer.type.Oauth;
import com.codefarm.codefarmer.type.UserType;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_USER")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
public class User extends Member{
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Builder
    public User(String memberName, String memberNickname, String memberPhone, String memberLocation, String memberBirth, String memberEmail, Oauth memberOauth, UserType userType) {
        super(memberName, memberNickname, memberPhone, memberLocation, memberBirth, memberEmail, memberOauth);
        this.userType = userType;
    }
}
