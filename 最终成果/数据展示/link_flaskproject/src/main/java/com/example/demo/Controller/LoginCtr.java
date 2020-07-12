package com.example.demo.Controller;

import com.example.demo.UserDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import java.util.Map;
/**
 * 包含注册和登录的控制器
 * */
@Controller
public class LoginCtr {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @PostMapping(value = "/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String,Object> map) {
        UserDb db =new UserDb(jdbcTemplate);
        if(db.login("users",username,password)){   //密码正确
            map.put("username",username);
            return "redirect:index";    //重定向到主页
        }
        //密码错误
        map.put("msg","用户名或密码错误");
        return "login";
    }

    @PostMapping(value = "/register")
    //@RequestMapping(value = "/user/login",method = RequestMethod.POST)
    public String register(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String,Object> map) {
        UserDb db =new UserDb(jdbcTemplate);
        if(db.register("users",username,password)){ //注册成功
            map.put("msg","注册成功，请登录");
            return "login";    //返回登录界面
        }
        //遇到错误
        map.put("msg","遇到错误，注册失败");
        return "register";
    }




    @RequestMapping("index")
    public String index(){
        return "index";
    }
}
