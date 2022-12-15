package com.codefarm.codefarmer.service.board;

import com.codefarm.codefarmer.domain.board.QReplyDTO;
import com.codefarm.codefarmer.domain.board.ReplyDTO;
import com.codefarm.codefarmer.entity.board.QReply;
import com.codefarm.codefarmer.entity.board.Reply;
import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.repository.board.BoardRepository;
import com.codefarm.codefarmer.repository.board.ReplyRepository;
import com.codefarm.codefarmer.repository.member.MemberRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.codefarm.codefarmer.entity.board.QReply.reply;

@Service
@RequiredArgsConstructor
public class ReplyService {
    private final JPAQueryFactory jpaQueryFactory;
    private final ReplyRepository replyRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

//    해당 보드에 댓글 추가하기
    public void replyAdd(ReplyDTO replyDTO){
        Reply reply = replyDTO.toEntity();
        reply.changeBoard(boardRepository.findById(replyDTO.getBoardId()).get());
        reply.changeMember(memberRepository.findById(replyDTO.getMemberId()).get());
        replyRepository.save(reply);
    }

//    댓글 리스트 갖고오기
    public List<ReplyDTO> getReplyList(){
        return jpaQueryFactory.select(new QReplyDTO(
                reply.replyId,
                reply.board.boardId,
                reply.member.memberId,
                reply.member.memberNickname,
                reply.replyContent,
                reply.createdDate,
                reply.updatedDate
            )).from(reply)
            .orderBy(reply.updatedDate.desc())
            .fetch();
    }


//    댓글 수정하기
//    public void replyUpdate(ReplyDTO replyDTO){
//        Reply reply = replyRepository.findById(replyDTO.getReplyId()).get();
//        reply.update(replyDTO);
//    }

//    댓글 단 사람 닉네임 갖고오기기
    public String showReplyNickName(Long replyId){
        Reply reply = replyRepository.findById(replyId).get();

        return reply.getMember().getMemberNickname();
    }

//     내가 남긴 댓글 총 개수(전체)
    public Long showReplyAllCount(Long memberId){
       return jpaQueryFactory.select(reply.count())
               .from(reply)
               .where(reply.member.memberId.eq(memberId))
               .fetchOne();
    }

//    public ReplyDTO show(Long replyId){
//       return replyRepository.findAllByReplyIdAndReplyContent(replyId);
//    }


//    보드에 있는 댓글 특정개수
    public Long getTotal(Long boardId){
        return replyRepository.countAllByBoardBoardId(boardId);
    }

//   해당 게시글의 댓글
    public List<ReplyDTO> getReplyByBoardId(Long boardId){
        return jpaQueryFactory.select(new QReplyDTO(
                reply.replyId,
                reply.board.boardId,
                reply.member.memberId,
                reply.member.memberNickname,
                reply.replyContent,
                reply.createdDate,
                reply.updatedDate
        )).from(reply)
          .where(reply.board.boardId.eq(boardId))
          .orderBy(reply.updatedDate.desc())
          .limit(3)
          .fetch();
    }



//    내가 작성한 댓글 지우기
    public void removeReply(Long replyId){
//        Reply reply = replyDTO.toEntity();
//        reply.changeMember(memberRepository.findById(replyDTO.getMemberId()).get());
        replyRepository.deleteById(replyId);
    }


}

















