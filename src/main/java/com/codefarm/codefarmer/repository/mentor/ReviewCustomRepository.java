package com.codefarm.codefarmer.repository.mentor;

import com.codefarm.codefarmer.domain.mentor.ReviewDTO;
import com.codefarm.codefarmer.entity.mentor.Review;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewCustomRepository {
    public List<Review> findAll();

    public List<ReviewDTO> findAllList(Long mentorBoardId);


    public List<Review> findByNickname(String memberNickname, Pageable pageable);
    public Integer countByMemberNickname(String memberNickname);
}
