package com.codefarm.codefarmer.service.admin;

import com.codefarm.codefarmer.entity.admin.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    public Criteria createCriteriaPage(Pageable pageable, String keyword, String searchText){
        Criteria criteria = new Criteria();
        criteria.setPage(pageable.getPageNumber());
        criteria.setKeyword(keyword);
        criteria.setSearchText(searchText);

        return criteria;
    }

}
