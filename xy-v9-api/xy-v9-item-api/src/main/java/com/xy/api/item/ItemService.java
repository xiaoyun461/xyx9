package com.xy.api.item;

import com.xy.v9.common.pojo.ResultBean;
import com.xy.v9.entity.Product;

import java.util.List;

public interface ItemService {

    ResultBean createHtmlById(Long productId);

    ResultBean batchCreateHtml(List<Long> idList);
}
