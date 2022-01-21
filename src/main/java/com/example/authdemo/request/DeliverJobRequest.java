package com.example.authdemo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class DeliverJobRequest {
    // 用户具体操作行为
    private String action;

    // 上传数据的具体描述
    private DataInfo data;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DataInfo {
        // 时间
        private Date time;

        // 批次号
        private String batchNo;

        // 数据名称
        private String dataName;

        // 数据条目, 指本次增量上传的数据条数, 单位条
        private Long dataNumber;

        // 数据条目, 指本次增量上传的数据大小，单位字节 byte
        private Long dataSize;
    }
}
