package com.codefarm.codefarmer.entity;

import com.codefarm.codefarmer.type.Oauth;
import com.sun.istack.NotNull;
import lombok.*;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.hibernate.internal.util.collections.JoinedIterable;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "TBL_MEMBER")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "MEMBER_TYPE")
public abstract class Member extends Period{
    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long memberId;
    @NotNull
    private String memberName;
    @NotNull
    @Column(unique = true)
    private String memberNickname;
    @NotNull
    private String memberPhone;
    @NotNull
    private String memberLocation;
    @NotNull
    private String memberBirth;
    @NotNull
    private String memberEmail;
    @Enumerated(EnumType.STRING)
    @NotNull
    private Oauth memberOauth;

    public void update(String memberNickname, String memberPhone, String memberLocation, String memberEmail){
        this.memberNickname = memberNickname;
        this.memberPhone = memberPhone;
        this.memberLocation = memberLocation;
        this.memberEmail = memberEmail;
    }

    public Member(String memberName, String memberNickname, String memberPhone, String memberLocation, String memberBirth, String memberEmail, Oauth memberOauth) {
        this.memberName = memberName;
        this.memberNickname = memberNickname;
        this.memberPhone = memberPhone;
        this.memberLocation = memberLocation;
        this.memberBirth = memberBirth;
        this.memberEmail = memberEmail;
        this.memberOauth = memberOauth;
    }
}
