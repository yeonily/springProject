package com.codefarm.codefarmer.service.admin;

import com.codefarm.codefarmer.entity.admin.Banner;
import com.codefarm.codefarmer.entity.board.Board;
import com.codefarm.codefarmer.repository.admin.BannerRepository;
import com.codefarm.codefarmer.repository.board.BoardCustomRepository;
import com.codefarm.codefarmer.repository.board.BoardRepository;
import com.codefarm.codefarmer.repository.board.ReplyCustomRepository;
import com.codefarm.codefarmer.repository.board.ReplyRepository;
import com.codefarm.codefarmer.repository.member.MemberRepository;
import com.codefarm.codefarmer.repository.mentor.ReviewRepository;
import com.codefarm.codefarmer.type.BannerStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminService {
//    배너
    private final BannerRepository bannerRepository;
//    공지
    private final BoardRepository boardRepository;
    private final BoardCustomRepository boardCustomRepository;
//    댓글
    private final ReplyRepository replyRepository;
    private final ReplyCustomRepository replyCustomRepository;
//    후기
    private final ReviewRepository reviewRepository;
//    멤버
    private final MemberRepository memberRepository;

//    공지 목록
    public Page<Board> boardShowAll(Pageable pageable, String keyword, String boardTitle, String boardContent, String memeberNickname){
        List<Board> boards = boardCustomRepository.findByBoardLikeMemberNickname(memeberNickname, pageable);
        final int total = boardCustomRepository.countByMemberNickname(memeberNickname);
        final int start = (int)pageable.getOffset();
        final int end = (start + pageable.getPageSize()) < total ? (start + pageable.getPageSize()) : total;

        if (keyword.equals("t")){
            return boardRepository.findByBoardTitleContaining(boardTitle, pageable);
        } else if (keyword.equals("c")){
            return boardRepository.findByBoardContentContaining(boardContent, pageable);
        } else if (keyword.equals("w")){
            return new PageImpl<>(boards.subList(start, end), pageable, boards.size());
        } else {
            return boardRepository.findByBoardTitleContainingOrBoardContentContaining(boardTitle, boardContent, pageable);
        }
    }

//    커뮤니티 글 개수
    public int countByBoard() { return boardRepository.countByBoard(); }

//    커뮤니티 - 작성자로 검색했을 때 글 개수
    public int countByBoardNickname(String memeberNickname) {
        int total = boardCustomRepository.countByMemberNickname(memeberNickname);
        return total % 10 == 0 ? (total / 10) : ((total / 10) + 1);
    }

////    댓글 목록
//    public Page<Reply> replyShowAll(Pageable pageable, String keyword, String searchText){
//        List<Reply> replies = replyCustomRepository.findByNickname(searchText, pageable);
//        final int total = replyCustomRepository.countByMemberNickname(searchText);
//        final int start = (int)pageable.getOffset();
//        final int end = (start + pageable.getPageSize()) < total ? (start + pageable.getPageSize()) : total;
//
//        if (keyword.equals("w")){
//            return new PageImpl<>(replies.subList(start, end), pageable, replies.size());
//        } else {
//            return replyRepository.findByReplyContentContaining(searchText, pageable);
//        }
//    }
////    작성자로 검색했을 때 개수
//    public int countByReplyNickname (String memeberNickname){
//        int total = replyCustomRepository.countByMemberNickname(memeberNickname);
//        return total%10 == 0 ? (total/10) : ((total/10) + 1);
//    }
////    댓글 개수
//    public int countByReply(){return replyRepository.countByReply();}
//
////    후기 목록
//    public Page<Review> reviewShowAll(Pageable pageable, String keyword, String searchText){
//        List<Review> reviews = reviewCustomRepository.findByNickname(searchText, pageable);
//        final int total = reviewCustomRepository.countByMemberNickname(searchText);
//        final int start = (int)pageable.getOffset();
//        final int end = (start + pageable.getPageSize()) < total ? (start + pageable.getPageSize()) : total;
//
//        if (keyword.equals("w")){
//            return new PageImpl<>(reviews.subList(start, end), pageable, reviews.size());
//        } else {
//            return reviewRepository.findByReviewContentContaining(searchText, pageable);
//        }
//    }
////    작성자로 검색했을 때 개수
//    public int countByReviewNickname (String memeberNickname){
//        int total = reviewCustomRepository.countByMemberNickname(memeberNickname);
//        return total%10 == 0 ? (total/10) : ((total/10) + 1);
//    }
////    댓글 개수
//    public int countByReview(){return reviewRepository.countByReview();}

//    배너 목록
    @Transactional(readOnly = true)
    public Page<Banner> bannerShowAll(Pageable pageable, String keyword, String bannerTitle, String bannerContent){
        if (keyword.equals("t")){
            return bannerRepository.findByBannerTitleContaining(bannerTitle, pageable);
        } else if (keyword.equals("c")){
            return bannerRepository.findByBannerInfoContaining(bannerContent, pageable);
        } else {
            return bannerRepository.findByBannerTitleContainingOrBannerInfoContaining(bannerTitle, bannerContent, pageable);
        }
    }

//    배너 디테일
    public Banner bannerShowOne(Long bannerId){
        return bannerRepository.findById(bannerId).get();
    }

//    배너 등록
    public void bannerAdd(Banner banner, String status, String startDate, String endDate) throws DateTimeParseException {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDateTime bannerStartDate = LocalDate.parse(startDate, format).atStartOfDay();
        LocalDateTime bannerEndDate = LocalDate.parse(endDate, format).atStartOfDay();

        banner.setBannerStartDate(bannerStartDate);
        banner.setBannerEndDate(bannerEndDate);

        if (status.equals("statusOk")){
            banner.setBannerStatus(BannerStatus.USING);
        } else if (status.equals("statusNo")) {
            banner.setBannerStatus(BannerStatus.NOT_USING);
        }

        bannerRepository.save(banner);
    }

//    배너 삭제
    public Long bannerDelete(Long bannerId){
        bannerRepository.delete(bannerRepository.findById(bannerId).get());
        return bannerId;
    }

//    배너 글 개수
    public int countByBanner() { return bannerRepository.countByBanner(); }

//    회원 목록
//    @Transactional(readOnly = true)
//    public Page<Member> memberShowAll(Pageable pageable, String keyword, String searchText){
//        if (keyword.equals("nn")){
//            return memberRepository.findMemberByMemberNicknameContaining(pageable, searchText);
//        } else if (keyword.equals("n")) {
//            return memberRepository.findMemberByMemberNameContaining(pageable, searchText);
//        } else {
//            return memberRepository.findMemberByMemberPhoneContaining(pageable, searchText);
//        }
//    }

//    회원 삭제
    public void memberRemove(Long memberId) { memberRepository.delete(memberRepository.findById(memberId).get()); }

//    회원 총 수
//    public int countByMember() { return memberRepository.countByMember(); }

}
