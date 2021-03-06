package com.example.authdemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.authdemo.client.ResourceClient;
import com.example.authdemo.common.CommonResult;
import com.example.authdemo.request.DeliverJobRequest;
import com.example.authdemo.request.EvidenceReq;
import com.example.authdemo.resp.DeliverDetailResp;
import com.example.authdemo.resp.DeliverJobResp;
import com.example.authdemo.resp.PageResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/resources")
@Slf4j
public class ResourceController {

    @Autowired
    private ResourceClient resourceClient;

    @GetMapping("/list")
    public CommonResult<PageResp<DeliverJobResp>> list(@RequestHeader("Authorization") String token,
                       @RequestParam("page") int indexPage, @RequestParam("limit") int pageSize){
        String data =  resourceClient.getTaskList(token, indexPage, pageSize);
        return this.dataCoverToResult(data);
    }

    @GetMapping("/detail/{id}")
    public CommonResult<DeliverDetailResp> detail(@RequestHeader("Authorization") String token, @PathVariable String id){
        String data = resourceClient.getTaskDetail(id,token);
        return this.dataCoverToResult(data);
    }

    @PostMapping("/detail/{id}/log")
    public CommonResult log(@RequestHeader("Authorization") String token, @RequestBody DeliverJobRequest deliverJobRequest, @PathVariable String id){
        String data =  resourceClient.postLog(id,deliverJobRequest,token);
        return this.dataCoverToResult(data);
    }

    @PostMapping("/detail/{id}/evidence")
    public CommonResult evidence(@RequestHeader("Authorization") String token, @RequestBody EvidenceReq req, @PathVariable String id){
        String data = resourceClient.postEvidence(id, req, token);
        return this.dataCoverToResult(data);
    }


    /**
     * ???????????? json ???????????????  CommonResult
     * @param data
     * @return
     */
    private CommonResult dataCoverToResult(String data){
        CommonResult result;
        log.info("?????????????????????" + data);
        try {
            result = JSONObject.parseObject(data, CommonResult.class);
        }catch (Exception e){
            return  CommonResult.failed("?????????????????????????????????");
        }
        if(result == null || result.getCode() == null){
            if(result != null){
                return  CommonResult.failed("???????????????????????????" + result.getMessage());
            }
            return  CommonResult.failed("????????????????????????");
        }
        return result;
    }


}
