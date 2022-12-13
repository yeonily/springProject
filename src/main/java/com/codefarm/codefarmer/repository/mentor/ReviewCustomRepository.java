package com.codefarm.codefarmer.repository.mentor;

import com.codefarm.codefarmer.domain.mentor.ReviewDTO;
import com.codefarm.codefarmer.entity.mentor.Review;

import java.util.List;

public interface ReviewCustomRepository {
    public List<Review> findAll();

    public List<ReviewDTO> findAllList(Long mentorBoardId);
}
