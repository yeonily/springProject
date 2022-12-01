package com.codefarm.codefarmer.service;

import com.codefarm.codefarmer.domain.BannerDTO;
import com.codefarm.codefarmer.domain.BoardDTO;
import com.codefarm.codefarmer.entity.Board;
import com.codefarm.codefarmer.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

//    게시글 제목, 내용 작성
    public void boardAdd(BoardDTO boardDTO){
        Board board = boardDTO.toEntity();
        board.changeMember(boardDTO.getMemberId());
        boardRepository.save(board);
    }

//    게시글 제목, 내용 수정
    public void boardUpdate(BoardDTO boardDTO){
        Board board = boardRepository.findById(boardDTO.getBoardId()).get();
        board.update(boardDTO);
    }

//    게시글 제목, 내용 상세페이지에서 보기
    public BoardDTO boardShowDetail(Long boardNumber){
        Board board = boardRepository.findById(boardNumber).get();

        BoardDTO boardDTO = new BoardDTO();


        boardDTO.setBoardTitle(board.getBoardTitle());
        boardDTO.setBoardContent(board.getBoardContent());
        boardDTO.setMemberNickName(board.getMember().getMemberNickname());

        return boardDTO;
    }






}
