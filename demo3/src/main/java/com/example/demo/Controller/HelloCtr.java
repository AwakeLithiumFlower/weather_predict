package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloCtr {
    @RequestMapping("/")        //默认界面，重定向到login界面
    public String hello() {
        return "redirect:login";
    }

    @RequestMapping("login")    //返回登录界面
    public String login() {
        return "login";
    }

    @RequestMapping("register") //返回注册界面
    public String register() {
        return "register";
    }
}
