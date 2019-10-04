package com.king.sso.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SSOCheck {


    public static boolean CheckCookie(){

        HttpServletRequest request=getRequest();
        Cookie[] cookies=request.getCookies();

        if(cookies!=null){
            for(int i=0;i<cookies.length;i++){
                if("ssocookie".equalsIgnoreCase(cookies[i].getName()) && "sso".equalsIgnoreCase(cookies[i].getValue())){
                   return true;
                }
            }
        }
        return false;
    }

    public static boolean checkCookie(String cookieName,String cookieValue){
        if("ssocookie".equalsIgnoreCase(cookieName) && "sso".equalsIgnoreCase(cookieValue)){
            return true;
        }else{
            return false;
        }
    }

    public static HttpServletResponse getResponse(){
        ServletRequestAttributes servletRequestAttributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return servletRequestAttributes.getResponse();
    }

    public static HttpServletRequest getRequest(){
        ServletRequestAttributes servletRequestAttributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return servletRequestAttributes.getRequest();
    }
}
