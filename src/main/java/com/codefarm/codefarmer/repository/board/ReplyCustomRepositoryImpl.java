package com.codefarm.codefarmer.repository.board;

import com.codefarm.codefarmer.domain.board.QReplyDTO;
import com.codefarm.codefarmer.domain.board.ReplyDTO;
import com.codefarm.codefarmer.entity.board.QBoard;
import com.codefarm.codefarmer.entity.board.QReply;
import com.codefarm.codefarmer.entity.board.Reply;
import com.codefarm.codefarmer.entity.member.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.codefarm.codefarmer.entity.board.QBoard.board;
import static com.codefarm.codefarmer.entity.board.QReply.reply;
import static com.codefarm.codefarmer.entity.member.QMember.member;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ReplyCustomRepositoryImpl implements ReplyCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public List<Reply> findAll() {
        return jpaQueryFactory.selectFrom(reply).fetch();
    }

    @Override
    public List<ReplyDTO> findAllList(Long boardId) {
        return jpaQueryFactory.select(new QReplyDTO(
                reply.replyId,
                reply.board.boardId,
                reply.member.memberId,
                reply.member.memberNickname,
                reply.replyContent,
                reply.createdDate,
                reply.updatedDate
                ))
                .from(reply)
                .where(reply.board.boardId.eq(boardId))
                .orderBy(reply.updatedDate.desc())
                .fetch();
    }




}
