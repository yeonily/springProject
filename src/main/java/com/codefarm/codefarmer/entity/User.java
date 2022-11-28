package com.codefarm.codefarmer.entity;

import com.codefarm.codefarmer.domain.FarmerDTO;
import com.codefarm.codefarmer.domain.UserDTO;
import com.codefarm.codefarmer.type.FarmerType;
import com.codefarm.codefarmer.type.Oauth;
import com.codefarm.codefarmer.type.UserType;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_USER")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("USER")
public class User extends Member{
    @NotNull
    @Enumerated(EnumType.STRING)
    private UserType userType;

    public void update(UserDTO userDTO){
        super.update(userDTO.getMemberNickname(), userDTO.getMemberPhone(), userDTO.getMemberLocation(), userDTO.getMemberEmail());
        this.userType = userDTO.getUserType();
    }


    @Builder
    public User(String memberName, String memberNickname, String memberPhone, String memberLocation, String memberBirth, String memberEmail, Oauth memberOauth, UserType userType) {
        super(memberName, memberNickname, memberPhone, memberLocation, memberBirth, memberEmail, memberOauth);
        this.userType = userType;
    }
}
