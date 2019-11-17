package com.xy.xyv9productservice.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xy.api.product.IProductTypeService;
import com.xy.v9.entity.ProductType;
import com.xy.v9.mapper.ProductTypeMapper;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author cxl
 * @since 2019-11-11
 */
@Service
public class ProductTypeService extends ServiceImpl<ProductTypeMapper, ProductType> implements IProductTypeService {

}
