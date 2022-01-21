package com.example.authdemo.resp;


import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PageResp<T> implements Serializable {
    private Integer pageNum;
    private Integer pageSize;
    private Long totalPage;
    private Long total;
    private List<T> list;
    private Boolean isPage = true;

}
