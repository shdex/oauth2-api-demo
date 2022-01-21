package com.example.authdemo.resp;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliverDetailResp implements Serializable{

    private static final long serialVersionUID = 9081445333710791967L;

    // 工单编号
    private String taskId;

    // 交付状态
    private String status;

    // 卖方名称
    private String seller;

    // 买方名称
    private String buyer;

    // 工单开始时间
    private Date beginTime;

    // 工单结束时间
    private Date endTime;

    // 当前用户角色，SELLER = 卖方 , BUYER = 买方
    private String role;

    // 限制交付数据条目,通常为按照计算时长交付。”-1 表示无显示， >0 表示有具体的交付条目数限制
    private String limitDataNumber;

    // 数据集scheme
    private List<Scheme> dataScheme;

    // 扩展字段，暂时未用
    private Object ext;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Scheme{

        // 对应的数据集的字段名称
        private String itemName;

        // 对应的数据集的字断类型, 类型分为 ‘String’, ‘Integer’ , ‘Float’ 三种类型
        private String itemType;
    }


}


