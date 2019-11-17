package com.xy.xyv9background.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xy.api.item.ItemService;
import com.xy.api.product.IProductService;
import com.xy.api.product.vo.ProductVO;
import com.xy.api.search.ISearchService;
import com.xy.v9.common.constant.RabbitMQConstant;
import com.xy.v9.entity.Product;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("product")
public class ProductController {

    @Reference
    private IProductService productService;
    @Reference
    private ISearchService searchService;
    @Reference
    private ItemService itemService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("list")
    public String list(Model model) {
        model.addAttribute("list", productService.list());
        return "product/list";
    }

    @GetMapping("page/{pageIndex}/{pageSize}")
    public String page(Model model,
                       @PathVariable("pageIndex") Integer pageIndex,
                       @PathVariable("pageSize") Integer pageSize
    ) {
        IPage<Product> page = productService.page(new Page<>(pageIndex, pageSize));
        Set<Integer> pageCounts = new HashSet<>();
        Integer pa = Integer.parseInt(String.valueOf(page.getPages()));

        if (page.getPages() > 1) {
            for (int i = 1; i <= pa; i++) {
                pageCounts.add(i);
            }
        }
        Integer current = Integer.parseInt(String.valueOf(page.getCurrent()));
        Set<Integer> paCount = pageCounts.stream().filter(key -> key < current + 3 && key > current - 3).collect(Collectors.toSet());
        model.addAttribute("pageInfo", page);
        model.addAttribute("pageCountList", paCount);
        model.addAttribute("pageSizes", pageSize);
        return "product/list";
    }

    @PostMapping("add")
    public String add(ProductVO productVO) {
        Long productId = productService.add(productVO);
        //发送消息到交换机即可
        rabbitTemplate.convertAndSend(RabbitMQConstant.BACKGROUND_EXCHANGE, "product.add", productId);

        //调用搜索服务，让其同步这个数据

//        searchService.updateById(productId);
        itemService.createHtmlById(productId);


        return "redirect:/product/page/1/1";
    }


}
