package com.codefarm.codefarmer.repository;

import com.codefarm.codefarmer.entity.Crop;
import com.codefarm.codefarmer.entity.Inquire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InquireRepository extends JpaRepository<Inquire, Long> {
}
