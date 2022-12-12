package com.codefarm.codefarmer.controller.community;

import com.codefarm.codefarmer.domain.board.BoardDTO;
import com.codefarm.codefarmer.domain.board.ReplyDTO;
import com.codefarm.codefarmer.entity.board.Board;
import com.codefarm.codefarmer.repository.board.BoardCustomRepository;
import com.codefarm.codefarmer.repository.board.BoardRepository;
import com.codefarm.codefarmer.repository.member.MemberRepository;
import com.codefarm.codefarmer.service.board.BoardService;
import com.codefarm.codefarmer.service.board.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value={"/community/*"})
public class CommunityController {
    private final BoardService boardService;
    private final ReplyService replyService;
    private final BoardRepository boardRepository;
    private final BoardCustomRepository boardCustomRepository;
    private final MemberRepository memberRepository;


    @GetMapping("/community")
    public void pagingList(Model model,/*HttpServletRequest request,*/ HttpSession session){
//        Page<Board> boards = boardRepository.findAll(pageable);

//        model.addAttribute("lists", boards);
//         HttpSession session = (HttpSession)request.getSession();
            Long memberId = (Long)session.getAttribute("memberId");
        if(memberId != null){
            Long boardCount = boardService.showBoardCountMine(memberId);
            Long replyCount = replyService.showReplyAllCount(memberId);
            model.addAttribute("boardCount", boardCount);
            model.addAttribute("replyCount", replyCount);
        }else {
            model.addAttribute("boardCount", 0);
            model.addAttribute("replyCount", 0);
        }
        model.addAttribute("sessionMemberId", memberId);
//        String farmerNickName = boardService.showFarmerNickName(memberId);
//        String userNickName = boardService.showUserNickName(memberId);

        if(memberId==null){
            model.addAttribute("nickName","닉네임");
            model.addAttribute("memberType" , "로그인 후 이용해주세요.");
        }else{
            model.addAttribute("nickName",boardService.showNickName(memberId));
            model.addAttribute("memberType", boardService.getMemberType(memberId));
        }
    }



    @GetMapping("/detail")
    public void detailPage(Model model, Long boardId, HttpSession session){
        Long memberId = (Long)session.getAttribute("memberId");

        BoardDTO list = boardService.boardShowDetail(boardId);
        boardService.updateViewCount(boardId);

        model.addAttribute("list", list);
        model.addAttribute("sessionMemberId", memberId);
    }

    @GetMapping("/delete")
    public RedirectView detailDelete(Long boardId){
        boardService.removeBoard(boardId);
        return new RedirectView("/community/community");
    }


//    @PostMapping("/delete")
//    public RedirectView detailDelete(Long boardId){
//        boardService.removeBoard(boardId);
//        return new RedirectView("/community/community");
//    }
//
//    @GetMapping("/detail")
//    public void detailPage(Long boardId, Model model){
//        boardService.updateViewCount(boardId);
//        model.addAttribute("board", boardService.showOne(boardId));
//    }

    @GetMapping("/register")
    public void writePage(Model model){
        model.addAttribute("board", new BoardDTO());
    }

    @GetMapping("/update")
    public void updatePage(Model model, @RequestParam Long boardId){
        BoardDTO list = boardService.boardShowDetail(boardId);
        model.addAttribute("list", list);
    }

    @PostMapping("updated")
    public RedirectView updateBoard(BoardDTO boardDTO){
        boardService.boardUpdate(boardDTO, boardDTO.getBoardId());
        return new RedirectView("/community/community");
    }

    @PostMapping("/register")
    public RedirectView write(BoardDTO boardDTO, RedirectAttributes redirectAttributes, HttpSession session){
        Long memberId = (Long)session.getAttribute("memberId");
        boardDTO.setMemberId(memberId);
        boardService.boardAdd(boardDTO);
        redirectAttributes.addFlashAttribute("boardId", boardDTO.getBoardId());
        return  new RedirectView("/community/community");
    }


}
