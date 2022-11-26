package com.codefarm.codefarmer.entity;

import com.codefarm.codefarmer.type.Oauth;
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
//@RequiredArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "MEMBER_TYPE")
public abstract class Member extends Period{
    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long memberId;
    @Column(nullable = false)
    private String memberName;
    @Column(unique = true)
    private String memberNickname;
    @Column(nullable = false)
    private String memberPhone;
    @Column(nullable = false)
    private String memberLocation;
    @Column(nullable = false)
    private String memberBirth;
    @Column(nullable = false)
    private String memberEmail;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Oauth memberOauth;

    public void update(String memberNickname, String memberPhone, String memberLocation, String memberEmail, Oauth memberOauth){
        this.memberNickname = memberNickname;
        this.memberPhone = memberPhone;
        this.memberLocation = memberLocation;
        this.memberEmail = memberEmail;
        this.memberOauth = memberOauth;
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
