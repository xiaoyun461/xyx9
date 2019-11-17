package com.xy.xyv9searchservice.handler;

import com.xy.api.search.ISearchService;
import com.xy.v9.common.constant.RabbitMQConstant;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    @Autowired
    private ISearchService searchService;

    @RabbitListener(queues = RabbitMQConstant.BACKGROUND_PRODUCT_UPDATE_QUEUE)
    public void processAddOrUpdate(Long productId) {
        searchService.updateById(productId);
    }
}
