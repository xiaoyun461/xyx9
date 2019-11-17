package com.xy.xyv9productservice.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xy.api.product.IProductService;
import com.xy.api.product.vo.ProductVO;
import com.xy.v9.entity.Product;
import com.xy.v9.entity.ProductDesc;
import com.xy.v9.mapper.ProductDescMapper;
import com.xy.v9.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author cxl
 * @since 2019-11-11
 */
@Service
public class ProductService extends ServiceImpl<ProductMapper, Product> implements IProductService {

    @Autowired
    private ProductMapper productMapper;
    @Autowired

    private ProductDescMapper productDescMapper;

    @Override
    @Transactional
    public Long add(ProductVO productVO) {
        Product product = productVO.getProduct();
        product.setCreateTime(new Date());
        product.setUpdateTime(product.getCreateTime());
        product.setFlag(true);
        product.setCreateUser(1L);
        product.setUpdateUser(product.getCreateUser());
        productMapper.insert(product);

        ProductDesc desc = new ProductDesc();
        desc.setProductId(product.getId());
        desc.setPDesc(productVO.getProductDesc());
        productDescMapper.insert(desc);
        return product.getId();
    }
}
