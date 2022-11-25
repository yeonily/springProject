package com.codefarm.codefarmer.repository;

import com.codefarm.codefarmer.entity.ChatRoom;
import com.codefarm.codefarmer.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
