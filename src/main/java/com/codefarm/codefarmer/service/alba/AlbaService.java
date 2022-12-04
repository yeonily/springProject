package com.codefarm.codefarmer.service.alba;

import com.codefarm.codefarmer.domain.alba.AlbaDTO;
import com.codefarm.codefarmer.entity.alba.Alba;
import com.codefarm.codefarmer.repository.alba.AlbaRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlbaService {

    private final JPAQueryFactory jpaQueryFactory;
    private final AlbaRepository albaRepository;

    public List<AlbaDTO> showAlbaList() {
        List<Alba> list = albaRepository.findByLatest();
        return list.stream().map(AlbaDTO::new).collect(Collectors.toList());
    }
}
