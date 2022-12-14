package com.codefarm.codefarmer.controller.main;

import com.codefarm.codefarmer.domain.board.ReplyDTO;
import com.codefarm.codefarmer.domain.program.ProgramDTO;
import com.codefarm.codefarmer.domain.program.ProgramFileDTO;
import com.codefarm.codefarmer.entity.program.Program;
import com.codefarm.codefarmer.entity.program.ProgramFile;
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
import java.util.stream.Collectors;

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


//        programDTO에 file정보 추가해서 model객체로 보냄
        List<ProgramDTO> programDTOs = programListService.findTop8ByOrderByProgramApplyEndDateDesc();
        for (ProgramDTO programDTO : programDTOs){
            programDTO.setFiles(programListService.showFiles(programDTO.getProgramId()));
        }
        log.info("파일 잘 들어갔니? "+ programDTOs.toString() );
        model.addAttribute("programs", programDTOs);



//        게시판 글에 해당하는 댓글 총 개수 가져오기
        List<Long> boardIds= new ArrayList<>();
        boardService.getBoardList().stream().map(t -> t.getBoardId()).forEach(t -> boardIds.add(t));
        List<Long> boardReplys = new ArrayList<>();
        boardIds.stream().map(t -> boardService.showBoardReplyCount(t)).forEach(t -> boardReplys.add(t));

//        해당 게시글 댓글 총 개수
        model.addAttribute("boardReplys" , boardReplys);

        model.addAttribute("boards", boardService.getBoardList());
//        boardService.getBoardList().stream().map(t -> t.getBoardId()).forEach(t -> replyService.getReplyList(t));
        List<ReplyDTO> replys = new ArrayList<>();
//        replys = boardIds.stream().map(t -> replyService.getReplyList(t))
//        boardIds.stream().map(t -> replyService.getReplyList(t)).forEach(t -> replys.add(t));
        model.addAttribute("replies", replyService.getReplyList());
    }


}

