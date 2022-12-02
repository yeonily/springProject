package com.codefarm.codefarmer.repository.member;

import com.codefarm.codefarmer.entity.member.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
