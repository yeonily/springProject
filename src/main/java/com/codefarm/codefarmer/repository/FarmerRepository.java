package com.codefarm.codefarmer.repository;

import com.codefarm.codefarmer.domain.FarmerDTO;
import com.codefarm.codefarmer.entity.ChatRoom;
import com.codefarm.codefarmer.entity.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FarmerRepository extends JpaRepository<Farmer, Long> {
}
