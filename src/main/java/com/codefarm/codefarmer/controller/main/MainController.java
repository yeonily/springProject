package com.codefarm.codefarmer.controller.main;

import com.codefarm.codefarmer.service.alba.AlbaListService;
import com.codefarm.codefarmer.service.alba.AlbaService;
import com.codefarm.codefarmer.service.board.BoardService;
import com.codefarm.codefarmer.service.board.ReplyService;
import com.codefarm.codefarmer.service.program.ProgramListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/main/*")
@RequiredArgsConstructor
public class MainController {

    private final AlbaListService albaListService;
    private final ProgramListService programListService;
    private final BoardService boardService;
    private final ReplyService replyService;

    @GetMapping("main")
    public void getAlbaList(Model model) {
        model.addAttribute("albas", albaListService.showListByRecentEndDate());
        model.addAttribute("programs", programListService.findTop8ByOrderByProgramApplyEndDateDesc());

//        게시판 글에 해당하는 댓글 총 개수 가져오기
        List<Long> boardIds= new ArrayList<>();
        boardService.getBoardList().stream().map(t -> t.getBoardId()).forEach(t -> boardIds.add(t));
        List<Long> boardReplys = new ArrayList<>();
        boardIds.stream().map(t -> boardService.showBoardReplyCount(t)).forEach(t -> boardReplys.add(t));
        model.addAttribute("boardReplys" , boardReplys);

        model.addAttribute("boards", boardService.getBoardList());
        model.addAttribute("replies", replyService.getReplyList());
    }


}

