package com.example.authdemo.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.http.HttpStatus;

/**
 * 通用返回对象
 * Created by eagle on 2019/4/19.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult<T> {
    private Integer code;
    private String message;
    private T data;
    private String requestId = ThreadContext.get("traceId");

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     * @param
     */
    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<T>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), data,ThreadContext.get("traceId"));
    }

    /**
     * 失败返回结果
     * @param
     */
    public static <T> CommonResult<T> failed(String message) {
        return new CommonResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), message, null,ThreadContext.get("traceId"));
    }

}
