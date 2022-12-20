package com.codefarm.codefarmer.controller.main;

import com.codefarm.codefarmer.domain.board.BoardDTO;
import com.codefarm.codefarmer.domain.board.ReplyDTO;
import com.codefarm.codefarmer.domain.mentor.MentorBoardDTO;
import com.codefarm.codefarmer.domain.mentor.QReviewDTO;
import com.codefarm.codefarmer.domain.mentor.ReviewDTO;
import com.codefarm.codefarmer.domain.notice.NoticeDTO;
import com.codefarm.codefarmer.domain.program.ProgramDTO;
import com.codefarm.codefarmer.domain.program.ProgramFileDTO;
import com.codefarm.codefarmer.entity.admin.Banner;
import com.codefarm.codefarmer.entity.board.Board;
import com.codefarm.codefarmer.entity.program.Program;
import com.codefarm.codefarmer.entity.program.ProgramFile;
import com.codefarm.codefarmer.repository.admin.BannerRepository;
import com.codefarm.codefarmer.repository.board.BoardRepository;
import com.codefarm.codefarmer.repository.board.ReplyRepository;
import com.codefarm.codefarmer.repository.notice.NoticeRepository;
import com.codefarm.codefarmer.service.alba.AlbaListService;
import com.codefarm.codefarmer.service.alba.AlbaService;
import com.codefarm.codefarmer.service.board.BoardService;
import com.codefarm.codefarmer.service.board.ReplyService;
import com.codefarm.codefarmer.service.mentor.MentorService;
import com.codefarm.codefarmer.service.notice.NoticeService;
import com.codefarm.codefarmer.service.program.ProgramListService;
import com.codefarm.codefarmer.type.BannerStatus;
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
@RequiredArgsConstructor
public class MainController {

    private final AlbaListService albaListService;
    private final ProgramListService programListService;
    private final BoardService boardService;
    private final ReplyService replyService;
    private final MentorService mentorService;
    private final BannerRepository bannerRepository;
    private final NoticeService noticeService;


    @GetMapping("/main")
    public String getAlbaList(Model model) {
//      배너부분
        List<Banner> banners = bannerRepository.findAll();
        List<Banner> realBanners = new ArrayList<>();

        for(Banner banner: banners){
            log.info("배너 상태:" + banner.getBannerStatus());
            if(banner.getBannerStatus().equals(BannerStatus.USING)){
                realBanners.add(banner);
            }
        }
        realBanners.forEach(t -> log.info("들어간 배너:" + t.toString()));

        model.addAttribute("banners",realBanners);

//        멘토부분
//        mentorBoardDTO에 file정보 추가해서 model객체로 보냄
        List<MentorBoardDTO> mentorBoardDTOs = mentorService.findMainList();
        List<Long> mentorBoardIds = new ArrayList<>();
        List<Double> mentorBoardAvg = new ArrayList<>();
        List<Long> mentorBoardTotalCount = new ArrayList<>();
        for(MentorBoardDTO mentorBoardDTO : mentorBoardDTOs){
            mentorBoardDTO.setFiles(mentorService.showFiles(mentorBoardDTO.getMentorBoardId()));
//            mentorBoardDTO.setReviews(mentorService.showReviews(mentorBoardDTO.getMentorBoardId()));
            mentorBoardIds.add(mentorBoardDTO.getMentorBoardId());
            log.info("멘토board 리뷰 들어갔니 " +mentorBoardDTO.toString());
        }

        for(Long mentorBoardId : mentorBoardIds){
           mentorBoardAvg.add(mentorService.findReviewAvg(mentorBoardId).get(0));
           mentorBoardTotalCount.add(mentorService.findReviewCount(mentorBoardId).get(0));
        }

        log.info("평균 잘 들어갔니?:"+ mentorBoardAvg.toString());
        log.info("댓글 총 개수 잘 들어갔니?:" + mentorBoardTotalCount.toString());
        model.addAttribute("mentorBoardAvg", mentorBoardAvg);
        model.addAttribute("mentorBoardTotalCount", mentorBoardTotalCount);


        log.info("멘토보드 파일 잘 들어갔니? " + mentorBoardDTOs.toString());


        model.addAttribute("mentorList",mentorBoardDTOs);

        log.info("알바 파일 잘 들어갔니? : " + albaListService.showListByRecentEndDate());
        model.addAttribute("albas", albaListService.showListByRecentEndDate());


//        프로그램 부분
//        programDTO에 file정보 추가해서 model객체로 보냄
        List<ProgramDTO> programDTOs = programListService.findTop8ByOrderByProgramApplyEndDateDesc();
        for (ProgramDTO programDTO : programDTOs){
            programDTO.setFiles(programListService.showFiles(programDTO.getProgramId()));
        }
        log.info("파일 잘 들어갔니? "+ programDTOs.toString() );
        model.addAttribute("programs", programDTOs);



//        게시판 부분
        List<Long> boardIds= new ArrayList<>();
        boardService.getBoardList().stream().map(t -> t.getBoardId()).forEach(t -> boardIds.add(t));
        List<Long> boardReplys = new ArrayList<>();
        boardIds.stream().map(t -> boardService.showBoardReplyCount(t)).forEach(t -> boardReplys.add(t));
////        해당 게시글 댓글 총 개수
        model.addAttribute("boardReplys" , boardReplys);
//
//        model.addAttribute("boards", boardService.getBoardList());
////        boardService.getBoardList().stream().map(t -> t.getBoardId()).forEach(t -> replyService.getReplyList(t));
//        List<ReplyDTO> replys = new ArrayList<>();
////        replys = boardIds.stream().map(t -> replyService.getReplyList(t))
////        boardIds.stream().map(t -> replyService.getReplyList(t)).forEach(t -> replys.add(t));
//        model.addAttribute("replies", replyService.getReplyList());



        List<BoardDTO> boards = boardService.getBoardList();
        for(BoardDTO board:boards){
            Long boardId = board.getBoardId();
            List<ReplyDTO> replytests = replyService.getReplyByBoardId(boardId);
            board.setReplies(replytests);

             board.getReplies().forEach(t -> log.info("이름은?" + t.getMemberNickName()));
             board.getReplies().forEach(t -> log.info("댓글내용은?" + t.getReplyContent()));
        }
        model.addAttribute("boards",boards);

//        공지사항 부분
        List<NoticeDTO> notices  = noticeService.showNoticeByRecentThree();
        model.addAttribute("notices" , notices);
        return "/main/main";
    }

}

