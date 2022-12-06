package com.codefarm.codefarmer.service.board;

import com.codefarm.codefarmer.domain.board.BoardDTO;
import com.codefarm.codefarmer.domain.board.QBoardDTO;
import com.codefarm.codefarmer.domain.board.ReplyDTO;
import com.codefarm.codefarmer.entity.board.Board;
import com.codefarm.codefarmer.entity.board.QBoard;
import com.codefarm.codefarmer.entity.member.Farmer;
import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.entity.member.User;
import com.codefarm.codefarmer.repository.board.BoardRepository;
import com.codefarm.codefarmer.repository.board.ReplyRepository;
import com.codefarm.codefarmer.repository.member.FarmerRepository;
import com.codefarm.codefarmer.repository.member.UserRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.codefarm.codefarmer.entity.board.QBoard.board;


@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {
    private final JPAQueryFactory jpaQueryFactory;
    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;
    private final FarmerRepository farmerRepository;
    private final UserRepository userRepository;

//    게시글 제목, 내용 작성
    public void boardAdd(BoardDTO boardDTO){
        Board board = boardDTO.toEntity();
        board.changeMember(boardDTO.getMember());
        boardRepository.save(board);
    }

//    게시글 제목, 내용 수정
    public void boardUpdate(BoardDTO boardDTO){

        Board board = boardRepository.findById(boardDTO.getBoardId()).get();
        board.update(boardDTO);
    }

//    게시글 제목, 내용 상세페이지에서 보기
    public BoardDTO boardShowDetail(Long boardNumber){
        Board board = boardRepository.findById(boardNumber).get();

        BoardDTO boardDTO = new BoardDTO();

        boardDTO.setBoardTitle(board.getBoardTitle());
        boardDTO.setBoardContent(board.getBoardContent());
        boardDTO.setMemberNickName(board.getMember().getMemberNickname());
        boardDTO.setCreatedDate(board.getCreatedDate());
        boardDTO.setUpdateDate(board.getUpdatedDate());

        return boardDTO;
    }

//    내가 작성한 게시글 총 개수
    public Long showBoardCountMine(Long memberId){
        return boardRepository.countByMemberMemberId(memberId);
    }

//  해당보드 viewCount 갖고오기
    public int showViewCount(Long boardId){
         Board board = boardRepository.findById(boardId).get();

         return board.getBoardViewCount();
    }

//  해당 보드 댓글 총 수 갖고오기
    public Long showBoardReplyCount(Long boardId){
        return replyRepository.countByBoard_BoardId(boardId);
    }

//  현재 로그인 되어있는 사람 닉넴 갖고오기(농장주)
    public String showNickName(Long memberId){
        Optional<Farmer> farmer = farmerRepository.findById(memberId);
        if(!farmer.isPresent()){
            Optional<User> user = userRepository.findById(memberId);
           return user.get().getMemberNickname();
        }else {
            return farmer.get().getMemberNickname();
        }
    }
//  현재 로그인 되어있는 사람 닉넴 갖고오기(일반회원)
//    public String showUserNickName(Long memberId){
//        Optional<User> user = userRepository.findById(memberId);
//        if(!user.isPresent()){
//            return "닉넴";
//        }else{
//            return user.get().getMemberNickname();
//        }
//
//    }
//
//    public String getNickName(Long memberId){
//        if(!showUserNickName(memberId).equals("닉넴")){
//            return showUserNickName(memberId);
//        }else {
//            if(!showFarmerNickName(memberId).equals("닉넴")){
//                return showFarmerNickName(memberId);
//            }
//        }
//        return "닉넴";
//
//    }





   /* 보드 목록 갖고오기*/
    public List<BoardDTO> getBoardList(){
         return jpaQueryFactory.select(new QBoardDTO(
                 board.boardId,
                 board.boardTitle,
                 board.boardContent,
                 board.boardViewCount,
                 board.member.memberNickname,
                 board.createdDate,
                 board.updatedDate
                 )).from(board)
                 .orderBy(board.createdDate.desc())
                 .fetch();
    }

    @Transactional(readOnly = true)
    public Page<BoardDTO> showAll(Long boardId, Pageable pageable){
        return boardRepository.findAllByBoardId(boardId ,pageable);
    }

    public String getNickNameNologin(){
        return "닉네임";
    }




//    게시판 지우기
    public void removeBoard(Long boardId){
        boardRepository.deleteById(boardId);
    }

}
