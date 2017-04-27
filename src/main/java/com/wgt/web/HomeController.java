package com.wgt.web;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2017/4/27.
 */
@Controller
public class HomeController {
    @RequestMapping(value = "/")
    public String index(HttpServletResponse response){
        Cookie cookie =new Cookie("userPhone","78945612310");
        response.addCookie(cookie);
        return "index";
    }
}
