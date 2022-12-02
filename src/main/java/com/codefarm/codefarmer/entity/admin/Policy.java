package com.codefarm.codefarmer.entity.admin;

import com.codefarm.codefarmer.entity.period.Period;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_POLICY")
@Getter @Setter @ToString
<<<<<<< HEAD:src/main/java/com/codefarm/codefarmer/entity/admin/Policy.java
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Policy extends Period {
=======
@NoArgsConstructor
public class Policy extends Period{
>>>>>>> origin/master:src/main/java/com/codefarm/codefarmer/entity/Policy.java
    @Id @GeneratedValue
    private Long policyId;
    @NotNull
    private String policyKeyword;
    @NotNull
    private String policyTitle;
    @NotNull
    private String policyContent;

    public void update(Policy policy){
        this.policyKeyword = policy.getPolicyKeyword();
        this.policyTitle = policy.getPolicyTitle();
        this.policyContent = policy.getPolicyContent();
    }

    @Builder
    public Policy(String policyKeyword, String policyTitle, String policyContent) {
        this.policyKeyword = policyKeyword;
        this.policyTitle = policyTitle;
        this.policyContent = policyContent;
    }
}
