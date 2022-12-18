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

//    검색 + 페이징
    public List<BoardDTO> ShowAllBoard(String keyword, String searchText);
    public Integer searchCountByBoard(String keyword, String searchText);

    public List<Board> showAdmin();
}
