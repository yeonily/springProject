package com.codefarm.codefarmer.repository.board;

import com.codefarm.codefarmer.domain.board.ReplyDTO;
import com.codefarm.codefarmer.entity.board.Reply;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReplyCustomRepository {
    public List<Reply> findAll();

    public List<ReplyDTO> findAllList(Long boardId);

    public List<Reply> findByNickname(String memberNickname, Pageable pageable);
    public Integer countByMemberNickname(String memberNickname);
    public List<Reply> showAdmin();

}
