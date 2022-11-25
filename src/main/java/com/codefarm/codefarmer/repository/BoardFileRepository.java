package com.codefarm.codefarmer.repository;

import com.codefarm.codefarmer.entity.Board;
import com.codefarm.codefarmer.entity.BoardFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardFileRepository extends JpaRepository<BoardFile, Long> {
}
