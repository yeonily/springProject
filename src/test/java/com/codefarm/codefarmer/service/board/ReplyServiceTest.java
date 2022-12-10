package com.codefarm.codefarmer.service.board;

import com.codefarm.codefarmer.domain.board.ReplyDTO;
import com.codefarm.codefarmer.entity.board.Board;
import com.codefarm.codefarmer.entity.board.Reply;
import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.repository.board.BoardRepository;
import com.codefarm.codefarmer.repository.board.ReplyRepository;
import com.codefarm.codefarmer.repository.member.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class ReplyServiceTest {

    @Autowired
    private ReplyService replyService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ReplyRepository replyRepository;



//    해당 보드에 댓글 작성하기
//    @Test
//    public void replyAddTest(){
//        ReplyDTO replyDTO = new ReplyDTO();
//        Optional<Member> findFarmer = memberRepository.findById(3L);
//        Optional<Board> findBoard = boardRepository.findById(14L);
//
//        Reply reply = replyDTO.toEntity();
//        reply.changeMember(findFarmer.get());
//        reply.changeBoard(findBoard.get());
//
//        replyDTO.setReplyContent("김장 하라고해도 안하고싶다!!");
//
//
//        replyService.replyAdd(replyDTO);
//    }

//    댓글 수정하기
    @Test
    public void replyUpdateTest(){
        ReplyDTO replyDTO = new ReplyDTO();
        Optional<Member> findFarmer = memberRepository.findById(14L);
        Optional<Reply> findReply = replyRepository.findById(19L);

        replyDTO.setReplyContent("수정된 댓글");
//        replyDTO.setMemberId(findFarmer.get());
        replyDTO.setReplyId(findReply.get().getReplyId());

//        replyService.replyUpdate(replyDTO);
    }

//    댓글 단 사람의 닉네임 갖고오기
    @Test
    public void showReplyNickNameTest(){
        log.info("댓글 단 사람의 닉네임 : " + replyService.showReplyNickName(20L));
    }

//    내가 등록한 댓글 총 수 가져오기
    @Test
    public void showReplyAllCountTest(){
        log.info("내가 남긴 댓글 : " + replyService.showReplyAllCount(14L));
    }

//    댓글 목록 가져오기
    @Test
    public void getReplyListTest(){
        log.info("댓글 목록 : " + replyService.getReplyList());
    }

//  내가 등록한 댓글 삭제하기
    @Test
    public void removeReplyTest(){
        replyService.removeReply(108L);
    }

    @Test
    public void test(){
        log.info("카운트" + replyService.getTotal(26L));
    }


}
