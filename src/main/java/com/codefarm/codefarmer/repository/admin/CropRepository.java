package com.codefarm.codefarmer.repository.admin;

import com.codefarm.codefarmer.entity.admin.Crop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CropRepository extends JpaRepository<Crop, Long> {
}
