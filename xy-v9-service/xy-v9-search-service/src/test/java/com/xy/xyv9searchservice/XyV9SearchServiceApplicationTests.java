package com.xy.xyv9searchservice;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class XyV9SearchServiceApplicationTests {
    @Autowired
    private SolrClient solrClient;

    @Test
    void add() throws IOException, SolrServerException {
        SolrInputDocument document = new SolrInputDocument();
        document.setField("id", "101");
        document.setField("product_name", "张学友香港红馆演唱会门票");
        document.setField("product_price", "19999");
        document.setField("product_sale_point", "歌神,张学友仅此一次");
        document.setField("product_images", "暂无");
        solrClient.add(document);
        solrClient.commit();
    }

    @Test
    void query() throws IOException, SolrServerException {
        SolrQuery query = new SolrQuery();
//        query.setQuery("*:*");
        query.setQuery("product_keywords:张学友刘德华同台演出");

        QueryResponse response = solrClient.query(query);
        SolrDocumentList results = response.getResults();
        for (SolrDocument document : results) {
            System.out.println(document.get("id"));
            System.out.println(document.get("product_name"));
            System.out.println(document.get("product_sale_point"));
        }
    }

    @Test
    void delete() throws IOException, SolrServerException {
        solrClient.deleteByQuery("product_name:张学友刘德华同台演出");
        solrClient.commit();
    }


}
