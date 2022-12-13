package com.codefarm.codefarmer.controller.community;

import com.codefarm.codefarmer.domain.board.ReplyDTO;
import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.repository.board.BoardRepository;
import com.codefarm.codefarmer.repository.board.ReplyCustomRepository;
import com.codefarm.codefarmer.repository.member.MemberRepository;
import com.codefarm.codefarmer.service.board.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reply/*")
@Slf4j
public class ReplyRestController {

    private final ReplyService replyService;
    private final ReplyCustomRepository replyCustomRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;


    //    댓글 등록
//    브라우저에서 JSON 타입으로 데이터를 전송하고 서버에서는 댓글의 처리 결과에 따라 문자열로 결과를 리턴한다.
//    consumes : 전달받은 데이터의 타입
//    produces : 콜백함수로 결과를 전달할 때의 타입
//    @RequestBody : 전달받은 데이터를 알맞는 매개변수로 주입
//    ResponseEntity : 서버의 상태코드, 응답 메세지 등을 담을 수 있는 타입

//    스프링 프레임 워크(아래쪽) 동작 원리 한번 더 보자!

//    @PostMapping(value = "/new", consumes = "application/json", produces = "text/plain; charset=utf-8")
//    public ResponseEntity<String> write(@RequestBody ReplyDTO replyDTO) throws UnsupportedEncodingException {
//        replyService.replyAdd(replyDTO);
//        return new ResponseEntity<>(new String("write success".getBytes(), "UTF-8"), HttpStatus.OK);
//    }

//  댓글 등록
    @PostMapping("/new")
    public String create(@RequestBody ReplyDTO replyDTO, HttpSession session){
        Long memberId = (Long)session.getAttribute("memberId");
        replyDTO.setMemberId(memberId);
        replyService.replyAdd(replyDTO);
        return "create success";
    }
//화면에서 값 가져와서 서비스에 있는 add 쓴다. 파라미터를 정해서 넘겨주는건데 중괄호 열어서 내가 키랑 값을 정해서 넘겨준다 그게 뭉쳐져서 콜백이랑 에러 적힌 부분에 디티오에 담긴다.
//    댓글 조회
    @GetMapping("/list/{bno}")
    public List<ReplyDTO> getList(@PathVariable("bno") Long boardId){
        return replyCustomRepository.findAllList(boardId);
    }


//    댓글 수정
//    ReplyDTO에 전달될 데이터 중 replyWriter가 전달되지 않았을 경우 required를 false로 변경해주고
//    Optional 객체를 사용하여 null을 검사해준다.
//    @PatchMapping(value = {"/{rno}","/{rno}"})
//    public String update(@RequestBody ReplyDTO replyDTO, @PathVariable("rno") Long replyId){
//        replyDTO.setReplyId(replyId);
//        replyService.replyUpdate(replyDTO);
//        return "update success";
//    }


//    댓글 삭제
    @DeleteMapping("/{replyId}")
    public String delete(@PathVariable Long replyId){
        replyService.removeReply(replyId);
        return "delete success";
    }

//    특정 게시글에 작성된 댓글 개수
    @PostMapping("/{boardId}")
    public Long getTotal(@PathVariable Long boardId){
        return replyService.getTotal(boardId);
    }


}
