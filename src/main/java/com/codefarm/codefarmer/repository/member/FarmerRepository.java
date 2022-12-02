package com.codefarm.codefarmer.repository.member;

import com.codefarm.codefarmer.entity.member.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FarmerRepository extends JpaRepository<Farmer, Long> {
}
