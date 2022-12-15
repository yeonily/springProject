package com.codefarm.codefarmer.service.board;

import com.codefarm.codefarmer.domain.board.BoardDTO;
import com.codefarm.codefarmer.domain.board.QBoardDTO;
import com.codefarm.codefarmer.domain.board.ReplyDTO;
import com.codefarm.codefarmer.domain.member.MemberDTO;
import com.codefarm.codefarmer.entity.board.Board;
import com.codefarm.codefarmer.entity.board.QBoard;
import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.repository.board.BoardRepository;
import com.codefarm.codefarmer.repository.board.ReplyRepository;
import com.codefarm.codefarmer.repository.member.MemberRepository;
import com.codefarm.codefarmer.type.MemberType;
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
    private final MemberRepository memberRepository;

//    게시글 제목, 내용 작성
    public void boardAdd(BoardDTO boardDTO){
        Board board = boardDTO.toEntity();
        board.changeMember(memberRepository.findById(boardDTO.getMemberId()).get());
        boardRepository.save(board);
    }

//    타입 구별
    public String getMemberType(Long memberId) {
        Optional<Member> memberType = memberRepository.findById(memberId);
        if (memberType.get().getMemberType() == MemberType.FARMER) {
            return "농장주";
        } else if (memberType.get().getMemberType() == MemberType.USER) {
            return "일반회원";
        } else if (memberType.get().getMemberType() == MemberType.MENTOR) {
            return "농장주 / 멘토";
        } else if(memberType.get().getMemberType() == MemberType.MENTEE){
            return "일반회원 / 멘티";
        }else{
            return "관리자";
        }
    }

//    게시글 제목, 내용 수정
    public void boardUpdate(BoardDTO boardDTO, Long boardId){
        Board board = boardRepository.findById(boardId).get();
        board.update(boardDTO);
        boardRepository.save(board);
    }

//    게시글 제목, 내용 상세페이지에서 보기
    public BoardDTO boardShowDetail(Long boardNumber){
        Board board = boardRepository.findById(boardNumber).get();

        BoardDTO boardDTO = new BoardDTO();

        boardDTO.setMemberId(board.getMember().getMemberId());
        boardDTO.setBoardId(board.getBoardId());
        boardDTO.setBoardTitle(board.getBoardTitle());
        boardDTO.setBoardContent(board.getBoardContent());
        boardDTO.setMemberNickName(board.getMember().getMemberNickname());
        boardDTO.setCreatedDate(board.getCreatedDate());
        boardDTO.setUpdatedDate(board.getUpdatedDate());

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

//  해당 게시판 총 댓글아이디 수 갖고오기
    public Long showBoardReplyCount(Long boardId){
        return replyRepository.countByBoard_BoardId(boardId);
    }

//  해당 게시판 댓글 닉네임,내용,댓글 총 개수 가져오기

//  현재 로그인 되어있는 사람 닉넴 갖고오기(농장주)
    public String showNickName(Long memberId){
        Optional<Member> member = memberRepository.findById(memberId);
        if(!member.isPresent()){
            Optional<Member> user = memberRepository.findById(memberId);
           return user.get().getMemberNickname();
        }else {
            return member.get().getMemberNickname();
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

//      보드 viewCount 증가
    public void updateViewCount(Long boardId){
        boardRepository.updateViewCount(boardId);
    }

//    보드 디테일 보기
    public Board showOne(Long boardId){
        return boardRepository.findById(boardId).get();
    }



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
                 .orderBy(board.updatedDate.desc())
                 .limit(5)
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
