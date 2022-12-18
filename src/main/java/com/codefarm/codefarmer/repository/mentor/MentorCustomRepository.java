package com.codefarm.codefarmer.repository.mentor;

import com.codefarm.codefarmer.domain.mentor.MentorBoardDTO;
import com.codefarm.codefarmer.entity.mentor.Mentor;
import com.codefarm.codefarmer.entity.mentor.MentorBoard;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface MentorCustomRepository {
    public List<MentorBoard> findAllQDSL();

    public Slice<MentorBoard> findAllSlice(Pageable pageable);

    public Slice<MentorBoardDTO> findAllSliceDTO(Pageable pageable);

    public List<Mentor> ShowAllMentor(String keyword, String searchText);
    public Integer searchCountByMentor(String keyword, String searchText);
}
