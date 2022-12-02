package com.codefarm.codefarmer.repository.board;

import com.codefarm.codefarmer.entity.board.BoardFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardFileRepository extends JpaRepository<BoardFile, Long> {
}
