package com.xy.api.product.vo;

import com.xy.v9.entity.Product;
import lombok.Data;

import java.io.Serializable;

@Data
public class ProductVO implements Serializable {
    private Product product;
    private String productDesc;
}
