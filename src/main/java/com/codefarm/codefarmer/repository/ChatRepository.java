package com.codefarm.codefarmer.repository;

import com.codefarm.codefarmer.entity.BoardFile;
import com.codefarm.codefarmer.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {
}
