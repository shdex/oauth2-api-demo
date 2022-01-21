package com.example.authdemo.controller;

import com.example.authdemo.client.ResourceClient;
import com.example.authdemo.request.DeliverJobRequest;
import com.example.authdemo.request.EvidenceReq;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;


@SpringBootTest
@RunWith(SpringRunner.class)
public class ResourceControllerTest {


    @Autowired
    private ResourceClient resourceClient;
    @Test
    public void list() {
        String token =  "bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJhZG1pbiIsInVzZXJfbmFtZSI6ImFkbWluIiwiY3JlYXRlZCI6MTY0MjU3MjM5MTgxMiwic2NvcGUiOlsiYWxsIl0sIl9hdXRob3JpdGllcyI6bnVsbCwiZXhwIjoxNjQyNjE1NTkxLCJqdGkiOiJpQ3NzZUw5a1RMMjBET0ZXY2Y1UzB0UVQ0RmsiLCJjbGllbnRfaWQiOiJERVgtNDY2ODM0LTAwMDAyMSJ9.J7j9sNKQcEcHE_UtoXlzqjXP4qyL_V6dg8tQIp_C2ulqecABXjF118orKeyl7xa_9YBg1IdmJ-PRxGdHacMmTw";
        Object obj = resourceClient.getTaskList(token,1,20);
        System.out.println(obj);
    }

    @Test
    public void detail() {
    }

    @Test
    public void log(){
        DeliverJobRequest req = new DeliverJobRequest();
        req.setAction("UPLOAD");
        DeliverJobRequest.DataInfo dataDescribe = new DeliverJobRequest.DataInfo();
        dataDescribe.setTime(new Date());
        dataDescribe.setDataName("测试数据");
        dataDescribe.setDataNumber(10000L);
        dataDescribe.setDataSize(2399123L);
        dataDescribe.setBatchNo(UUID.randomUUID().toString());
        req.setData(dataDescribe);
        String token =  "Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJhZG1pbiIsInVzZXJfbmFtZSI6ImFkbWluIiwiY3JlYXRlZCI6MTY0MjY0NzA5NTMwNiwic2NvcGUiOlsiYWxsIl0sInN2SWQiOiIyIiwiX2F1dGhvcml0aWVzIjpudWxsLCJleHAiOjE2NDI2OTAyOTUsImp0aSI6ImxfMFk2ZlFDT2YzS2k5dWJsN2NacExzZXQ1MCIsImNsaWVudF9pZCI6IkRFWC00NjY4MzQtMDAwMDIxIn0.ttMuaoFZC2GqDtifhP9-0Fbl3k_5LJ0bzjbGFpc9oTEBQHF8Y6IC2Ah75RbYbbMAlqTgtrnApQ9iOtodsRPzFg"; //request.getHeader("Authorization");
        //String token  = "Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJhZG1pbiIsInVzZXJfbmFtZSI6ImFkbWluIiwiY3JlYXRlZCI6MTY0MjE1NTc1MTEyMCwic2NvcGUiOlsiYWxsIl0sIl9hdXRob3JpdGllcyI6bnVsbCwiZXhwIjoxNjQyMTk4OTUxLCJqdGkiOiJYUDJHWDZYQXBJNGRMdGZNSU9mcUNyYkxSRHciLCJjbGllbnRfaWQiOiJkZXh1bXNvYXV0aDIifQ.4IDmYNZiDaWzkWF23yhjHQjHiSPxm3D-gNUbEZIk8uImzq9tXccY-QZXfph2tchARyvgleqfWsMwfyBxs7TpzQ";
        String result = resourceClient.postLog("101",req, token);

        System.out.println(result);
    }

    @Test
    public void evidence(){
        EvidenceReq req = new EvidenceReq();
        req.setData("密文的采样测试数据");
        req.setBatchNo(UUID.randomUUID().toString());
        String token =  "bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJhZG1pbiIsInVzZXJfbmFtZSI6ImFkbWluIiwiY3JlYXRlZCI6MTY0MjU3MjM5MTgxMiwic2NvcGUiOlsiYWxsIl0sIl9hdXRob3JpdGllcyI6bnVsbCwiZXhwIjoxNjQyNjE1NTkxLCJqdGkiOiJpQ3NzZUw5a1RMMjBET0ZXY2Y1UzB0UVQ0RmsiLCJjbGllbnRfaWQiOiJERVgtNDY2ODM0LTAwMDAyMSJ9.J7j9sNKQcEcHE_UtoXlzqjXP4qyL_V6dg8tQIp_C2ulqecABXjF118orKeyl7xa_9YBg1IdmJ-PRxGdHacMmTw"; //request.getHeader("Authorization");
        //String token  = "Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJhZG1pbiIsInVzZXJfbmFtZSI6ImFkbWluIiwiY3JlYXRlZCI6MTY0MjE1NTc1MTEyMCwic2NvcGUiOlsiYWxsIl0sIl9hdXRob3JpdGllcyI6bnVsbCwiZXhwIjoxNjQyMTk4OTUxLCJqdGkiOiJYUDJHWDZYQXBJNGRMdGZNSU9mcUNyYkxSRHciLCJjbGllbnRfaWQiOiJkZXh1bXNvYXV0aDIifQ.4IDmYNZiDaWzkWF23yhjHQjHiSPxm3D-gNUbEZIk8uImzq9tXccY-QZXfph2tchARyvgleqfWsMwfyBxs7TpzQ";
        String result = resourceClient.postEvidence("101",req, token);
        System.out.println(result);
    }


}