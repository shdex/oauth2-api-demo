package com.example.authdemo.client;

import com.alibaba.fastjson.JSON;
import com.example.authdemo.request.DeliverJobRequest;
import com.example.authdemo.request.EvidenceReq;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ResourceClient {
    @Value("${client.resources.get-uri}")
    private String getResourceUri;

    @Value("${client.resources.post-uri}")
    private String postResourceUri;

    @Value("${client.resources.token-head}")
    private String tokenHead;

    public String getTaskList(String token, int indexPage, int pageSize){
            return doGet(getResourceUri + "?indexPage=" + indexPage + "&pageSize="+pageSize, token);
    }

    public String getTaskDetail(String taskId, String token){
        return doGet(getResourceUri+"/"+taskId, token);
    }


    public String postLog(String taskId, DeliverJobRequest request, String token) {
        String url = postResourceUri+"/" + taskId + "/log";
        String jsonParameter = JSON.toJSONString(request);
        return doPost(url, jsonParameter, token);
    }


    public String postEvidence(String taskId, EvidenceReq req, String token) {
        String url = postResourceUri+"/" + taskId + "/evidence";
        String jsonParameter = JSON.toJSONString(req);
        return doPost(url, jsonParameter, token);
    }


    private String doPost(String url, String jsonParameter, String token){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader(tokenHead,token);
        RequestConfig config = RequestConfig.custom().setSocketTimeout(3000).setConnectTimeout(3000).build();
        httpPost.setConfig(config);
        StringEntity entity = null;
        CloseableHttpResponse response = null;
        try {
            entity = new StringEntity(jsonParameter,"utf-8");
            entity.setContentType("application/json");
            httpPost.setEntity(entity);
            response = httpClient.execute(httpPost);
            return EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    private String doGet(String url, String token){
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader(tokenHead,token);
        RequestConfig config = RequestConfig.custom().setSocketTimeout(3000).setConnectTimeout(3000).build();
        httpGet.setConfig(config);
        CloseableHttpResponse response;
        try {
            response = httpclient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
