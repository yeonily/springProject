package com.codefarm.codefarmer.repository.chat;

import com.codefarm.codefarmer.entity.chat.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {
}
