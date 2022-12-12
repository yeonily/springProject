package com.codefarm.codefarmer.repository.board;

import com.codefarm.codefarmer.domain.board.BoardDTO;
import com.codefarm.codefarmer.entity.board.Board;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface BoardCustomRepository {
//    닉네임 검색
    public List<Board> findByBoardLikeMemberNickname(String memberNickname, Pageable pageable);
//    닉네임 검색 시 글 개수
    public Integer countByMemberNickname(String memberNickname);

    public List<Board> findAllQDSL();

    public Slice<Board> findAllSlice(Pageable pageable);

    public Slice<BoardDTO> findAllSliceDTO(Pageable pageable);

}
