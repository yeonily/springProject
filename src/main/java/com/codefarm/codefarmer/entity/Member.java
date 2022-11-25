package com.codefarm.codefarmer.entity;

import com.codefarm.codefarmer.type.Oauth;
import lombok.*;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.hibernate.internal.util.collections.JoinedIterable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "TBL_MEMBER")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Member extends Period{
    @Id @GeneratedValue
    private Long memberId;
    @Column(nullable = false)
    private String memberName;
    @Column(nullable = false)
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
