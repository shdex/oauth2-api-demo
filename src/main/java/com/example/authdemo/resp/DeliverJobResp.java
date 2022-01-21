package com.example.authdemo.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliverJobResp implements Serializable {
    private static final long serialVersionUID = 566645665577629632L;

    // 工单编号
    private String taskId;

    // 订单编号
    private String orderNo;

    // 工单状态 - 字典值[ 1：待配置（默认） 2：已完成 3：待交付 4：交付中 5：已中止 ]
    private String status;

    // 产品名称
    private String productName;

    // 产品类型
    private String productType;

    // 工单生成时间
    private Date createTime;

    // 工单开始配送时间
    private Date beginTime;

}
