package com.codefarm.codefarmer.repository.board;

import com.codefarm.codefarmer.domain.board.BoardDTO;
import com.codefarm.codefarmer.entity.board.Board;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface BoardCustomRepository {
    public List<Board> findAllQDSL();

    public Slice<Board> findAllSlice(Pageable pageable);

    public Slice<BoardDTO> findAllSliceDTO(Pageable pageable);

}
