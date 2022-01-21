package com.example.authdemo.request;

import lombok.Data;

import java.util.Date;

/**
 * 数据交付信息反馈请求参数
 */
@Data
public class EvidenceReq {
    //密文的采样数据
    private String data;

    //批次号
    private String batchNo;
}
