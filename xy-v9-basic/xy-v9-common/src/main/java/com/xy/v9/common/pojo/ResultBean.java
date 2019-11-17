package com.xy.v9.common.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultBean implements Serializable {
    private Integer statusCode;
    private String data;
    private String msg;


    public static ResultBean success(String data) {
        ResultBean resultBean = new ResultBean();
        resultBean.setStatusCode(200);
        resultBean.setData(data);
        return resultBean;
    }

    public static ResultBean error(String msg) {
        ResultBean resultBean = new ResultBean();
        resultBean.setStatusCode(200);
        resultBean.setData(msg);
        return resultBean;
    }
}
