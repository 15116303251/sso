package com.king.sso.controller;

import com.king.sso.util.SSOCheck;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class LoginController {

    @RequestMapping("/sso/checkCookie")
    public void checkCookie(String cookieName,String cookieValue){
        boolean ok= SSOCheck.checkCookie(cookieName, cookieValue);
        String result="0";
        if(ok){
            result="1";
        }
        HttpServletResponse response=SSOCheck.getResponse();
        try {
            response.getWriter().print(result);
            response.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
