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
import org.springframework.data.domain.Pageable;
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

    //	닉네임으로 검색
    @Override
    public List<Reply> findByNickname(String memberNickname, Pageable pageable) {
        return jpaQueryFactory.select(reply)
                .from(reply)
                .where(reply.member.memberNickname.contains(memberNickname))
                .orderBy(reply.replyId.desc())
                .fetch();
    }
    //	닉네임으로 검색했을 때 카운트
    @Override
    public Integer countByMemberNickname(String memberNickname) {
        return Math.toIntExact(jpaQueryFactory.select(reply.count())
                .from(reply)
                .where(reply.member.memberNickname.contains(memberNickname))
                .fetchOne());
    }

    @Override
    public List<Reply> showAdmin() {
        return jpaQueryFactory.selectFrom(reply)
                .orderBy(reply.createdDate.desc())
                .limit(5)
                .fetch();
    }


}
