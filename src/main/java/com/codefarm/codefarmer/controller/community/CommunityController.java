package com.codefarm.codefarmer.controller.community;

import com.codefarm.codefarmer.domain.board.BoardDTO;
import com.codefarm.codefarmer.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value={"/community/*"})
public class CommunityController {
    private final BoardService boardService;

    @GetMapping("/list")
    public String communityPage(){
        return "/community/community";
    }

//    @GetMapping("/list")
//    public void communityList(){
//
//    }

    @GetMapping("/detail")
    public void detailPage(){
    }

    @GetMapping("/register")
    public void writePage(Model model){
        model.addAttribute("board", new BoardDTO());
    }

    public RedirectView write(BoardDTO boardDTO, RedirectAttributes redirectAttributes){
        boardService.boardAdd(boardDTO);
        redirectAttributes.addFlashAttribute("boardId", boardDTO.getBoardId());
        return  new RedirectView("list");
    }
}
