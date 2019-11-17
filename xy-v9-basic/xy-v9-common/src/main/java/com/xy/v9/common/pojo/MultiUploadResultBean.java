package com.xy.v9.common.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class MultiUploadResultBean implements Serializable {
    private String errno;
    private String[] data;
}
