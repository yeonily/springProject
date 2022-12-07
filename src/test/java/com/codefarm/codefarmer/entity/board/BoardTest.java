package com.codefarm.codefarmer.entity.board;

import com.codefarm.codefarmer.domain.board.BoardDTO;
import com.codefarm.codefarmer.entity.board.Board;
import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.repository.board.BoardRepository;
import com.codefarm.codefarmer.repository.board.ReplyRepository;
import com.codefarm.codefarmer.repository.member.MemberRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.Optional;


@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class BoardTest {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private MemberRepository memberRepository;

//    게시판 제목, 내용 작성
    @Test
    public void boardSaveTest(){
        BoardDTO boardDTO = new BoardDTO();
        Optional<Member> findMember = memberRepository.findById(3L);
        boardDTO.setBoardTitle("페이징처리 너란 녀석");
        boardDTO.setBoardContent("호호호호");
        boardDTO.setMember(findMember.get());

        Board board = boardDTO.toEntity();
        board.changeMember(boardDTO.getMember());
        boardRepository.save(board);
    }

//    게시판 제목, 내용 수정
    @Test
    public void boardUpdateTest(){
        BoardDTO boardDTO = new BoardDTO();
        Optional<Member> findFarmer = memberRepository.findById(3L);
        Board board = boardRepository.findById(6L).get();

        boardDTO.setBoardTitle("수정된 제목");
        boardDTO.setBoardContent("수정된 내용");
        boardDTO.setMember(findFarmer.get());
        boardDTO.setBoardId(board.getBoardId());

        board.changeMember(boardDTO.getMember());

        board.update(boardDTO);
    }

//   Detail 게시글 작성한 사람 닉네임 갖고오기
    @Test
    public void findGetBoardUser(){
        Optional<Board> findBoardUser = boardRepository.findById(6L);
            log.info("board : " + findBoardUser.get().getBoardId());
            log.info("nickName : " + findBoardUser.get().getMember().getMemberNickname());
//            log.info("nickName : " + findBoardUser.get().getBoardFiles());
        findBoardUser.get().getMember().getMemberNickname();
    }

//    현재 로그인 되어있는 사람 닉네임 갖고오기
    @Test
    public void findNickName(){
//        Optional<Board> findNickName = boardRepository.findById(17L);
//        log.info("닉네임 : " +  findNickName.get().getMember().getMemberNickname());
        Optional<Member> findMember = memberRepository.findById(3L);
        log.info("닉네임 : " + findMember.get().getMemberNickname());
    }






//    게시글 제목, 내용 select
    @Test
    public void findDetailTest(){
        Optional<Board> findTitleContent = boardRepository.findById(6L);

        if(findTitleContent.isPresent()){
            log.info("boardTitle : " + findTitleContent.get().getBoardTitle());
            log.info("boardContent : " + findTitleContent.get().getBoardContent());
        }
    }

//    누가 댓글 남겼는지 상단에 최신꺼 한개 갖고오기(컨트롤러에서 작업)

//    글 올라왔는지 총 몇분 됐는지(컨트롤러에서 작업)

//    내가 게시한 게시글 총 개수
    @Test
    public void findBoardCountMine(){
        log.info("내가 등록한 게시글 총 수 : " + boardRepository.countByMemberMemberId(3L));
    }

//    viewCount 갖고오기(count++하는건 컨트롤러에서 작업)
    @Test
    public void findBoardViewCount(){
        Optional<Board> findBoardViewCount = boardRepository.findById(6L);
        log.info("viewCount : " + findBoardViewCount.get().getBoardViewCount());
    }


    //    해당 보드 댓글 총 수
    @Test
    public void findReplyCountBoardTest(){
        log.info("게시판 댓글 총 수 : " + replyRepository.countByBoard_BoardId(38L));
    }

//    보드 목록
//    @Test
//    public void findBoardListTest(){
//        jpaQueryFactory.select(board.boardTitle,board.boardContent,board.boardViewCount, board.member.memberNickname, board.createdDate)
//                .from(board)
//                .orderBy(board.createdDate.desc())
//                .fetch()
//                .stream().map(Board -> Board.toString()).forEach(log::info);
//    }

//  보드 목록 페이징
//    @Test
////    public void findAllByBoardTitleAndBoardContentAndBoardViewCountAndCreatedDateAndMember_MemberNicknameTest(){
////        boardRepository.findAll(PageRequest.of(0,5, Sort.by(Sort.Direction.DESC, "boardId")))
////        .getContent().stream().map(Board::toString).forEach(log::info);
////    }

//    @Test
//    public void paging() throws Exception{
//        PageRequest pageRequest = PageRequest.of(0,5, Sort.by(Sort.Direction.DESC,"boardId"));
//
//        Page<Board> page = boardRepository.findPageBy((Pageable) pageRequest);
//        Slice<Board> slice = boardRepository.findSliceBy((Pageable) pageRequest);
//    }

//  멤버 타입에 따라서 farmer면 농장주, user면 일반회원 이라는 문구 갖고와보기
//    @Test
//    public void getMemberType(){
//        Optional<Farmer> findFarmerType = farmerRepository.findById(14L);
//        Optional<User> findUserType = userRepository.findById(16L);
//
//        if(findFarmerType.get().getFarmerType() == FarmerType.MENTOR) {
//            log.info("농장주");
//        }else if (findFarmerType.get().getFarmerType() == FarmerType.FARMER){
//            log.info("농장주");
//        }else if(findUserType.get().getUserType() == UserType.USER){
//            log.info("일반회원");
//        }else if (findUserType.get().getUserType() == UserType.MENTEE){
//            log.info("일반회원");
//        }else if(findUserType.get().getUserType() == UserType.ADMIN){
//            log.info("관리자");
//        }else{
//            log.info("회원가입 후 이용하시길 바랍니다.");
//        }
//    }




//    게시판 지우기
    @Test
    public void boardDeleteTest(){
        boardRepository.deleteAll();
    }
}
