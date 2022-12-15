package com.codefarm.codefarmer.controller.mento;


import com.codefarm.codefarmer.domain.mentor.MentorCriteria;
import com.codefarm.codefarmer.domain.mentor.ReviewDTO;
import com.codefarm.codefarmer.repository.member.MemberRepository;
import com.codefarm.codefarmer.repository.mentor.MentorBoardRepository;
import com.codefarm.codefarmer.repository.mentor.ReviewCustomRepository;
import com.codefarm.codefarmer.service.mentor.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
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
    public ReviewDTO create(@RequestBody ReviewDTO reviewDTO, HttpSession session){
        log.info(reviewDTO.toString());
        Long memberId = (Long)session.getAttribute("memberId");

        reviewDTO.setMemberId(memberId);
        reviewService.reviewAdd(reviewDTO);
        return reviewDTO;
    }

    //  리뷰 댓글 조회
    @GetMapping("list/{bno}/{page}/{keyword}")
    public ReviewDTO getList(@PathVariable("bno") Long mentorBoardId, @PathVariable int page, @PathVariable(required = false) String keyword){

        String decodeKeyword = URLDecoder.decode(keyword, StandardCharsets.UTF_8);

        MentorCriteria criteria = new MentorCriteria();
        criteria.setPage(page);
        criteria.setKeyword(decodeKeyword);

        Pageable pageable = PageRequest.of(criteria.getPage() == 0 ? 0 : criteria.getPage()-1, 5);

        Page<ReviewDTO> reviewDTOPage = reviewService.showReview(pageable, criteria, mentorBoardId);
        int endPage = (int)(Math.ceil(reviewDTOPage.getNumber()+1 / (double)10)) * 10;
        if(reviewDTOPage.getTotalPages() < endPage){
            endPage = reviewDTOPage.getTotalPages() == 0 ? 1 : reviewDTOPage.getTotalPages();
        }

        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setReviewList(reviewDTOPage.getContent());
        reviewDTO.setEndPage(endPage);
        reviewDTO.setTotalCount(reviewCustomRepository.findAllList(mentorBoardId).size());


        // 리뷰 별점 전체 개수
        List<ReviewDTO> reviewDTOs = new ArrayList<ReviewDTO>();
        reviewDTOs = reviewCustomRepository.findAllList(mentorBoardId);
        int starCount=0;

        for(ReviewDTO reviewStar : reviewDTOs) {
            starCount += reviewStar.getReviewStar();
        }

        reviewDTO.setTotalStar(starCount);

        return reviewDTO;
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
