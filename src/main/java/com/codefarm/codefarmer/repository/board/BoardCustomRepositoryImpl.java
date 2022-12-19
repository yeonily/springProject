package com.codefarm.codefarmer.repository.board;

import com.codefarm.codefarmer.domain.board.BoardDTO;
import com.codefarm.codefarmer.domain.board.QBoardDTO;
import com.codefarm.codefarmer.entity.board.Board;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.codefarm.codefarmer.entity.board.QBoard.*;
import static com.codefarm.codefarmer.entity.board.QReply.reply;


@Repository
@RequiredArgsConstructor
public class BoardCustomRepositoryImpl implements BoardCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Board> findAllQDSL() {
        return jpaQueryFactory.selectFrom(board).fetch();
    }

    @Override
    public Slice<Board> findAllSlice(Pageable pageable) {
        List<Board> boardList = jpaQueryFactory.selectFrom(board)
                .orderBy(board.updatedDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize()+1)
                .fetch();

        ArrayList<Board> content = (ArrayList<Board>)boardList;

        boolean hasNext = false;
        if(content.size() > pageable.getPageSize()){
            content.remove(pageable.getPageSize());
            hasNext=true;
        }
        return new SliceImpl<>(content,pageable,hasNext);
    }


    @Override
    public Slice<BoardDTO> findAllSliceDTO(Pageable pageable) {
        List<BoardDTO> boardList = jpaQueryFactory.select(
                new QBoardDTO(board.boardId,board.boardTitle,board.boardContent,
                board.boardViewCount,board.member.memberNickname,
                        board.createdDate,board.updatedDate)).from(board)
                .orderBy(board.updatedDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize()+1)
                .fetch();
        ArrayList<BoardDTO> content = (ArrayList<BoardDTO>)boardList;

        boolean hasNext = false;
        if(content.size() > pageable.getPageSize()){
            content.remove(pageable.getPageSize());
            hasNext=true;
        }
        return new SliceImpl<>(content,pageable,hasNext);
    }

//    검색 시 글 개수
    @Override
    public Integer searchCountByBoard(String keyword, String searchText) {
        return Math.toIntExact(jpaQueryFactory.select(board.count())
            .from(board)
            .where(
                    eqBoardTitle(keyword, searchText),
                    eqBoardContent(keyword, searchText),
                    eqBoardTitleContent(keyword, searchText),
                    eqBoardWriter(keyword, searchText)
            )
            .fetchOne());
    }

    @Override
    public List<Board> showAdmin() {
        return jpaQueryFactory.selectFrom(board)
                .orderBy(board.boardId.desc())
                .limit(5)
                .fetch();
    }

    @Override
    public List<BoardDTO> ShowAllBoard(String keyword, String searchText) {
        return jpaQueryFactory.selectFrom(board).leftJoin(board.replies, reply)
                .fetchJoin()
                .where(
                        eqBoardTitle(keyword, searchText),
                        eqBoardContent(keyword, searchText),
                        eqBoardTitleContent(keyword, searchText),
                        eqBoardWriter(keyword, searchText)
                )
                .orderBy(board.boardId.desc())
                .stream()
                .map(board -> new BoardDTO(
                        board.getBoardId(),
                        board.getBoardTitle(),
                        board.getBoardContent(),
                        board.getBoardViewCount(),
                        board.getMember().getMemberNickname(),
                        board.getUpdatedDate(),
                        board.getCreatedDate(),
                        board.getReplies().size()))
                .collect(Collectors.toList());
    }

    private BooleanExpression eqBoardTitle(String keyword, String searchText) {
        if (keyword.equals("t")) {
            return board.boardTitle.contains(searchText);
        }
        return null;
    }
    private BooleanExpression eqBoardContent(String keyword, String searchText) {
        if (keyword.equals("c")) {
            return board.boardContent.contains(searchText);
        }
        return null;
    }
    private BooleanExpression eqBoardTitleContent(String keyword, String searchText) {
        if (keyword.equals("tc")) {
            return board.boardTitle.contains(searchText).or(board.boardContent.contains(searchText));
        }
        return null;
    }
    private BooleanExpression eqBoardWriter(String keyword, String searchText) {
        if (keyword.equals("w")) {
            return board.member.memberNickname.contains(searchText);
        }
        return null;
    }

}
