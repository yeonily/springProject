package com.codefarm.codefarmer.repository.admin;

import com.codefarm.codefarmer.entity.admin.Policy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PolicyRepository extends JpaRepository<Policy, Long> {
}
