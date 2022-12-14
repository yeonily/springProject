package com.codefarm.codefarmer.controller.mento;


import com.codefarm.codefarmer.domain.mentor.ReviewDTO;
import com.codefarm.codefarmer.repository.member.MemberRepository;
import com.codefarm.codefarmer.repository.mentor.MentorBoardRepository;
import com.codefarm.codefarmer.repository.mentor.ReviewCustomRepository;
import com.codefarm.codefarmer.service.mentor.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review/*")
@Slf4j
public class ReviewController {
    private final ReviewService reviewService;
    private final ReviewCustomRepository reviewCustomRepository;
    private final MemberRepository memberRepository;
    private final MentorBoardRepository mentorBoardRepository;

//   리뷰 댓글등록
    @PostMapping("/new")
    public String create(@RequestBody ReviewDTO reviewDTO, HttpSession session){
        Long memberId = (Long)session.getAttribute("memberId");
        reviewDTO.setMemberId(memberId);
        reviewService.reviewAdd(reviewDTO);
        return "create success";
    }

//  리뷰 댓글 조회
    @GetMapping("list/{bno}")
    public List<ReviewDTO> getList(@PathVariable("bno") Long mentorBoardId){
        return reviewCustomRepository.findAllList(mentorBoardId);
    }

//   리뷰 댓글 삭제
    @DeleteMapping("/{reviewId}")
    public String delete(@PathVariable Long reviewId){
        reviewService.removeReview(reviewId);
        return "delete success";
    }


//    특정 멘토상세페이지에 작성된 댓글 개수
    @PostMapping("/{mentorBoardId}")
    public Long getTotal(@PathVariable Long mentorBoardId){
        return reviewService.getTotal(mentorBoardId);
    }









}
