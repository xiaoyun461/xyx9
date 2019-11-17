package com.xy.xyv9background.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.gson.Gson;
import com.xy.api.product.IProductTypeService;
import com.xy.v9.entity.ProductType;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("productType")
public class ProductTypeController {

    @Reference
    private IProductTypeService productTypeService;

    @GetMapping("list")
    public List<ProductType> list() {
        return productTypeService.list();
    }

    @GetMapping("listForJsonp")
    public String listForHsonp(String callback) {
        //获取要回调客户端函数
        System.out.println("callback=" + callback);
        List<ProductType> list = productTypeService.list();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return callback + "(" + json + ")";
    }


}
