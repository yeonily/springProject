package com.codefarm.codefarmer.repository;

import com.codefarm.codefarmer.entity.Crop;
import com.codefarm.codefarmer.entity.Inquire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InquireRepository extends JpaRepository<Inquire, Long> {
}
