package com.example.authdemo.controller;

import com.example.authdemo.client.AuthClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;


@Controller
public class AuthServerController {

    @Autowired
    private AuthClient authClient;/*

    // 提交申请code的请求
    @GetMapping("/requestCode")
    public String requestServerFirst(){
        return "redirect:" + authClient.authCodeUrl();
    }*/

    @GetMapping("/code2Token")
    @ResponseBody
    public String code2Token(HttpServletRequest httpRequest){
        String code = httpRequest.getParameter(AuthClient.RESPONSE_TYPE);
        String directUrl = httpRequest.getParameter("redirect_uri");
        String result = null;
        try {
            result = URLDecoder.decode(directUrl, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String token =  authClient.getAccessToken(code,result);
        return token;
    }
}