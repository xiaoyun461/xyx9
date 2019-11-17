package com.xy.api.product;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xy.api.product.vo.ProductVO;
import com.xy.v9.entity.Product;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author cxl
 * @since 2019-11-11
 */
public interface IProductService extends IService<Product> {

    Long add(ProductVO productVO);
}
