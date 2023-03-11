package com.waci.erp.shared.searchutils;

import com.waci.erp.shared.utils.BaseSearchObject;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class CustomSearchUtils {

    public static<T extends BaseSearchObject> Pageable generateGenericPageable(T baseSearchObject){
        Pageable pageable = PageRequest.of(baseSearchObject.getOffset() - 1, baseSearchObject.getLimit(),
                baseSearchObject.isSortAscending() ? Sort.by(baseSearchObject.getSortField()).ascending()
                        : Sort.by(baseSearchObject.getSortField()).descending()
        );
        return pageable;
    }
}
