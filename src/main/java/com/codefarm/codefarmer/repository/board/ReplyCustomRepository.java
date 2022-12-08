package com.codefarm.codefarmer.repository.board;

import com.codefarm.codefarmer.domain.board.ReplyDTO;
import com.codefarm.codefarmer.entity.board.Reply;

import java.util.List;

public interface ReplyCustomRepository {
    public List<Reply> findAll();

    public List<ReplyDTO> findAllList(Long boardId);

}
