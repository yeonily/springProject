package com.codefarm.codefarmer.repository.alba;

import com.codefarm.codefarmer.entity.alba.Alba;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlbaCustomRepository {
    public List<Alba> findByLatest();
}
