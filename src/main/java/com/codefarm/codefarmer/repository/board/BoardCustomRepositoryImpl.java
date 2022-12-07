package com.codefarm.codefarmer.repository.board;

import com.codefarm.codefarmer.domain.board.BoardDTO;
import com.codefarm.codefarmer.domain.board.QBoardDTO;
import com.codefarm.codefarmer.entity.board.Board;
import com.codefarm.codefarmer.entity.board.QBoard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.codefarm.codefarmer.entity.board.QBoard.*;


@Repository
@RequiredArgsConstructor
@Slf4j
public class BoardCustomRepositoryImpl implements BoardCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Board> findAllQDSL() {
        return jpaQueryFactory.selectFrom(board).fetch();
    }

    @Override
    public Slice<Board> findAllSlice(Pageable pageable) {
        List<Board> boardList = jpaQueryFactory.selectFrom(board)
                .orderBy(board.boardId.desc())
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

//         this.boardId = boardId;
//        this.boardTitle = boardTitle;
//        this.boardContent = boardContent;
//        this.boardViewCount = boardViewCount;
//        this.memberNickName = memberNickName;
//        this.createdDate = createdDate;
//        this.updateDate = updateDate;
    @Override
    public Slice<BoardDTO> findAllSliceDTO(Pageable pageable) {
        List<BoardDTO> boardList = jpaQueryFactory.select(
                new QBoardDTO(board.boardId,board.boardTitle,board.boardContent,
                board.boardViewCount,board.member.memberNickname,
                        board.createdDate,board.updatedDate)).from(board)
                .orderBy(board.boardId.desc())
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


}
