package com.codefarm.codefarmer.entity.board;

import com.codefarm.codefarmer.domain.board.BoardDTO;
import com.codefarm.codefarmer.entity.board.Board;
import com.codefarm.codefarmer.entity.member.Farmer;
import com.codefarm.codefarmer.entity.member.User;
import com.codefarm.codefarmer.repository.board.BoardRepository;
import com.codefarm.codefarmer.repository.member.FarmerRepository;
import com.codefarm.codefarmer.repository.board.ReplyRepository;
import com.codefarm.codefarmer.repository.member.UserRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

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
    private FarmerRepository farmerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReplyRepository replyRepository;

//    게시판 제목, 내용 작성
    @Test
    public void boardSaveTest(){
        BoardDTO boardDTO = new BoardDTO();
        Optional<Farmer> findFarmer = farmerRepository.findById(1L);
        boardDTO.setBoardTitle("과연 값이 들어갈까");
        boardDTO.setBoardContent("값 들어옴! 대박");
        boardDTO.setMember(findFarmer.get());

        Board board = boardDTO.toEntity();
        boardDTO.getMember();
        boardRepository.save(board);
    }

//    게시판 제목, 내용 수정
    @Test
    public void boardUpdateTest(){
        BoardDTO boardDTO = new BoardDTO();
        Optional<Farmer> findFarmer = farmerRepository.findById(1L);
        Board board = boardRepository.findById(5L).get();

        boardDTO.setBoardTitle("수정된 제목");
        boardDTO.setBoardContent("수정된 내용");
        boardDTO.setMember(findFarmer.get());
        boardDTO.setBoardId(board.getBoardId());
//        boardDTO.setMemberId(findFarmer.get());
//
//        board.changeMember(boardDTO.getMemberId());

        board.update(boardDTO);
    }

//   Detail 게시글 작성한 사람 닉네임 갖고오기
    @Test
    public void findGetBoardUser(){
        Optional<Board> findBoardUser = boardRepository.findById(38L);
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
        Optional<Farmer> findFarmer = farmerRepository.findById(1L);
        Optional<User> findUser = userRepository.findById(2L);
        log.info("닉네임 : " + findFarmer.get().getMemberNickname());
        log.info("닉네임 : " + findUser.get().getMemberNickname());
    }





//    게시글 제목, 내용 select
    @Test
    public void findDetailTest(){
        Optional<Board> findTitleContent = boardRepository.findById(5L);

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
        log.info("내가 등록한 게시글 총 수 : " + boardRepository.countByMemberMemberId(1L));
    }

//    viewCount 갖고오기(count++하는건 컨트롤러에서 작업)
    @Test
    public void findBoardViewCount(){
        Optional<Board> findBoardViewCount = boardRepository.findById(7L);
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




//    게시판 지우기
    @Test
    public void boardDeleteTest(){
        boardRepository.deleteById(3L);
    }
}
