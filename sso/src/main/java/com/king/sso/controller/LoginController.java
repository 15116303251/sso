package com.king.sso.controller;

import com.king.sso.util.SSOCheck;
import com.king.sso.util.URLUtil;
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

    @RequestMapping(value = "/api/user/login",method = RequestMethod.GET)
    public String login(HttpServletResponse response, String username, String password){
        if(username!=null && password!=null){
            //2、如果是ok，就设置cookie 的值
            Cookie cookie=new Cookie("ssocookie","sso");
            cookie.setDomain(".x.com");
            cookie.setPath("/");
            response.addCookie(cookie);
        }else{
            return "demo1请重新登录";
        }
        return "demo1登录成功";
    }

    /**
     * 应用程序1
     * @return
     */
    @RequestMapping("/program1")
    public String demo1(){

        HttpServletRequest request=SSOCheck.getRequest();
        Cookie[] cookies=request.getCookies();
        if(cookies!=null){
            for (Cookie cookie:cookies){
                if(cookie.getName().equalsIgnoreCase("ssocookie")){
                    String result= URLUtil.doGet("http://check.x.com:8082/sso/checkCookie",cookie.getName(),cookie.getValue());
                    if("1".equalsIgnoreCase(result)){
                        return "demo1登录成功";
                    }
                }
            }
        }
        return "redirect:/api/user/login?username=1&password=1";
    }
}
