package com.xy.xyv9searchservice.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xy.api.search.ISearchService;
import com.xy.v9.common.pojo.PageResultBean;
import com.xy.v9.common.pojo.ResultBean;
import com.xy.v9.entity.Product;
import com.xy.v9.mapper.ProductMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@Service
public class SearchServiceImpl implements ISearchService {

    @Autowired(required = false)
    private ProductMapper productMapper;

    @Autowired
    private SolrClient solrClient;

    @Override
    public ResultBean initAllData() {
        List<Product> list = productMapper.selectList(null);
        for (Product product : list) {
            SolrInputDocument document = new SolrInputDocument();
            document.setField("id", product.getId());
            document.setField("product_name", product.getName());
            document.setField("product_images", product.getImage());
            document.setField("product_sale_point", product.getSalePoint());
            document.setField("product_price", product.getPrice());
            try {
                solrClient.add(document);
            } catch (SolrServerException | IOException e) {
                e.printStackTrace();
                return ResultBean.error("添加到索引库失败!");
            }
            try {
                solrClient.commit();
            } catch (SolrServerException | IOException e) {
                e.printStackTrace();
                ResultBean error = ResultBean.error("添加到索引库失败!");
                return ResultBean.error("添加到索引库失败!");
            }
        }
        return ResultBean.success("数据同步成功");
    }

    @Override
    public ResultBean updateById(Long id) {
        Product product = productMapper.selectById(id);

        SolrInputDocument document = new SolrInputDocument();
        document.setField("id", product.getId());
        document.setField("product_name", product.getName());
        document.setField("product_images", product.getImage());
        document.setField("product_sale_point", product.getSalePoint());
        document.setField("product_price", product.getPrice());
        try {
            solrClient.add(document);
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
            return ResultBean.error("添加到索引库失败!");
        }
        try {
            solrClient.commit();
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
            ResultBean error = ResultBean.error("添加到索引库失败!");
            return ResultBean.error("添加到索引库失败!");
        }
        return ResultBean.success("数据同步成功");
    }

    @Override
    public List<Product> searchByKeyWord(String keyWord) {
        SolrQuery query = new SolrQuery();
        if (!StringUtils.isAllEmpty(keyWord)) {
            query.setQuery("product_keywords:" + keyWord);
        } else {
            query.setQuery("product_keywords:华为");
        }
        query.setHighlight(true);
        query.addHighlightField("product_name");
        query.addHighlightField("product_sale_point");
        query.setHighlightSimplePre("<font color='red'>");
        query.setHighlightSimplePost("</font'>");


        List<Product> result = null;
        try {
            QueryResponse response = solrClient.query(query);
            SolrDocumentList list = response.getResults();
            //获取高亮效果的信息
            Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();

            result = new ArrayList<>(list.size());

            for (SolrDocument document : list) {
                Product product = new Product();
//                String str = document.getFieldValue("id").toString();
//                String str1 = document.getFieldValue("product_images").toString();
//                String str2 = document.getFieldValue("product_name").toString();

                product.setId(Long.parseLong(document.getFieldValue("id").toString()));
//                product.setName(document.getFieldValues("product_name").toString());
                product.setImage(document.getFieldValue("product_images").toString());
                product.setPrice(Long.parseLong(document.get("product_price").toString()));
                //获取商品名称的高亮信息
                //获取到当前这条记录的高亮信息
                Map<String, List<String>> map = highlighting.get(document.getFieldValue("id").toString());
                //获取某个字段的高亮信息
                List<String> productNameHighlight = map.get("product_name");
                //单独处理高亮的设置
                if (productNameHighlight != null && productNameHighlight.size() > 0) {
                    product.setName(productNameHighlight.get(0));
                } else {
                    product.setName(document.getFieldValue("product_name").toString());
                }
                List<String> productSalePointHighlight = map.get("product_sale_point");
                if (productSalePointHighlight != null && productSalePointHighlight.size() > 0) {
                    product.setSalePoint(productSalePointHighlight.get(0));
                } else {
                    product.setSalePoint(document.getFieldValue("product_sale_point").toString());
                }

//                product.setSalePoint(document.getFieldValue("product_sale_point").toString());
                result.add(product);
            }
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public PageResultBean<Product> searchByKeyWord(String keyWord, Integer pageIndex, Integer rows) {

        PageResultBean<Product> pageResultBean = new PageResultBean<>();
        SolrQuery query = new SolrQuery();
        if (!StringUtils.isAllEmpty(keyWord)) {
            query.setQuery("product_keywords:" + keyWord);
        } else {
            query.setQuery("product_keywords:华为");
        }
        query.setHighlight(true);
        query.addHighlightField("product_name");
        query.addHighlightField("product_sale_point");
        query.setHighlightSimplePre("<font color='red'>");
        query.setHighlightSimplePost("</font'>");

        query.setStart((pageIndex - 1) * rows);
        query.setRows(rows);


        List<Product> result = null;
        long totalCount = 0;
        try {
            QueryResponse response = solrClient.query(query);
            SolrDocumentList list = response.getResults();
            totalCount = list.getNumFound();

            //获取高亮效果的信息
            Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();

            result = new ArrayList<>(list.size());

            for (SolrDocument document : list) {
                Product product = new Product();
                product.setId(Long.parseLong(document.getFieldValue("id").toString()));
                product.setImage(document.getFieldValue("product_images").toString());
                product.setPrice(Long.parseLong(document.get("product_price").toString()));
                //获取商品名称的高亮信息
                //获取到当前这条记录的高亮信息
                Map<String, List<String>> map = highlighting.get(document.getFieldValue("id").toString());
                //获取某个字段的高亮信息
                List<String> productNameHighlight = map.get("product_name");
                //单独处理高亮的设置
                if (productNameHighlight != null && productNameHighlight.size() > 0) {
                    product.setName(productNameHighlight.get(0));
                } else {
                    product.setName(document.getFieldValue("product_name").toString());
                }
                List<String> productSalePointHighlight = map.get("product_sale_point");
                if (productSalePointHighlight != null && productSalePointHighlight.size() > 0) {
                    product.setSalePoint(productSalePointHighlight.get(0));
                } else {
                    product.setSalePoint(document.getFieldValue("product_sale_point").toString());
                }

                result.add(product);
            }
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        pageResultBean.setPageNum(pageIndex);
        pageResultBean.setPageSize(rows);
        pageResultBean.setList(result);
        pageResultBean.setTotal(totalCount);
        pageResultBean.setPages((int) (totalCount % rows == 0 ? (totalCount / rows) : (totalCount / rows) + 1));
        return pageResultBean;
    }
}
