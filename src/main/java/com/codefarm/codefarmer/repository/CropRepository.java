package com.codefarm.codefarmer.repository;

import com.codefarm.codefarmer.entity.Board;
import com.codefarm.codefarmer.entity.Crop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CropRepository extends JpaRepository<Crop, Long> {
}
