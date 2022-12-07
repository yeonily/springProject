package com.codefarm.codefarmer.controller.community;

import com.codefarm.codefarmer.domain.board.BoardDTO;
import com.codefarm.codefarmer.entity.board.Board;
import com.codefarm.codefarmer.repository.board.BoardCustomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board/*")
public class CommunityRestController {
    private final BoardCustomRepository boardCustomRepository;

    @GetMapping("list")
    public Slice<BoardDTO> getList(@PageableDefault(size = 10, sort = "UpdatedDate", direction = Sort.Direction.DESC) Pageable pageable){
        return boardCustomRepository.findAllSliceDTO(pageable);
    }
}
