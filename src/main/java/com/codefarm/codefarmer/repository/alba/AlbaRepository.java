package com.codefarm.codefarmer.repository.alba;

import com.codefarm.codefarmer.entity.alba.Alba;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;


public interface AlbaRepository extends JpaRepository<Alba, Long>, AlbaCustomRepository {
    public List<Alba> findTop8ByOrderByAlbaApplyEndDateDesc();
}
