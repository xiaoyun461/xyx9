package com.xy.xyv9index.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xy.api.product.IProductTypeService;
import com.xy.v9.entity.ProductType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("index")
public class IndexController {

    @Reference
    private IProductTypeService productTypeService;

    @RequestMapping("show")
    public String showIndex(Model model) {
        List<ProductType> list = productTypeService.list();
        model.addAttribute("list", list);
        return "index";
    }

    @RequestMapping("list")
    @ResponseBody
    public List<ProductType> list() {
        return productTypeService.list();
    }

}
