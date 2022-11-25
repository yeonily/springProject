package com.codefarm.codefarmer.repository;

import com.codefarm.codefarmer.entity.Chat;
import com.codefarm.codefarmer.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
}
