package com.codefarm.codefarmer.repository;

import com.codefarm.codefarmer.entity.Farmer;
import com.codefarm.codefarmer.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
