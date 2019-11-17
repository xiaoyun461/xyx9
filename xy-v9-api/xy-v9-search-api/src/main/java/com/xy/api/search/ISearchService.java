package com.xy.api.search;

import com.xy.v9.common.pojo.PageResultBean;
import com.xy.v9.common.pojo.ResultBean;
import com.xy.v9.entity.Product;

import java.util.List;

public interface ISearchService {
    ResultBean initAllData();

    ResultBean updateById(Long id);

    List<Product> searchByKeyWord(String keyWord);

    PageResultBean<Product> searchByKeyWord(String keyWord, Integer pageIndex, Integer rows);

}
