package com.codefarm.codefarmer.repository;

import com.codefarm.codefarmer.entity.Inquire;
import com.codefarm.codefarmer.entity.Policy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PolicyRepository extends JpaRepository<Policy, Long> {
}
