package com.xy.xyv9search.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xy.api.search.ISearchService;
import com.xy.v9.common.pojo.PageResultBean;
import com.xy.v9.common.pojo.ResultBean;
import com.xy.v9.entity.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("search")
public class SearchController {

    @Reference
    private ISearchService searchService;

    @RequestMapping("initAllData")
    @ResponseBody
    public ResultBean initAllData() {
        return searchService.initAllData();
    }

//    @RequestMapping("searchByKeyWord")
//    public String searchByKeyWord(String keyWord, Model model) {
//        List<Product> list = searchService.searchByKeyWord(keyWord);
//        model.addAttribute("list", list);
//        return "search";
//    }

    @RequestMapping("searchByKeyWord/{pageIndex}/{rows}")
    public String searchByKeyWordBy(String keyWord, Model model,
                                    @PathVariable("pageIndex") Integer pageIndex, @PathVariable("rows") Integer rows, HttpServletRequest request) {
        if (StringUtils.isEmpty(pageIndex) || pageIndex < 1) pageIndex = 1;
        if (StringUtils.isEmpty(rows) || rows < 1) rows = 5;
        PageResultBean<Product> page = searchService.searchByKeyWord(keyWord, pageIndex, rows);
        request.getSession().setAttribute("searchCache", keyWord);
        request.getSession().getAttribute("searchCache");

        model.addAttribute("page", page);
        return "search";
    }
}
