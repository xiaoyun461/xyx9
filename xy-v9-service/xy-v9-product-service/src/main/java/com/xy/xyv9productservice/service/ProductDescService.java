package com.xy.xyv9productservice.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xy.api.product.IProductDescService;
import com.xy.v9.entity.ProductDesc;
import com.xy.v9.mapper.ProductDescMapper;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author cxl
 * @since 2019-11-11
 */
@Service
public class ProductDescService extends ServiceImpl<ProductDescMapper, ProductDesc> implements IProductDescService {

}
