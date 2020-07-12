package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCountCallbackHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserCtr {
    @Autowired
    JdbcTemplate jdbcTemplate;
//
//    @RequestMapping(value="/test", method= RequestMethod.GET)
//    public String index() {
//        //return getAllMsgFromForm("users");
//        //return  getSomeMsgFromForm("users","id");
//        //return  String.valueOf(login("users","u12","u1"));
//        //return String.valueOf(register("users","23","123"));
//        //return String.valueOf(remove("users","23"));
//        return  String.valueOf(update("users","u23","password","12345"));
//    }

//    //以列表的形式返回所有信息
//    public String getAllMsgFromForm(String form){
//        String sql = "SELECT * FROM "+form;
//        return jdbcTemplate.queryForList(sql).toString();
//    }
//    //以列表的形式返回某类信息
//    public String getSomeMsgFromForm(String form,String msg){
//        String sql = "SELECT "+msg+" FROM "+form;
////        RowCountCallbackHandler rcch = new RowCountCallbackHandler();
//
//        return jdbcTemplate.queryForList(sql).toString();
//    }
//    //返回登录的结果，true代表账户密码验证通过
//    public boolean login(String form, String username,String password){
//        String sql = "SELECT password FROM "+form+" where username = ?";
//        String mobile;
//        // 通过jdbcTemplate查询数据库
//        try {
//            mobile = (String)jdbcTemplate.queryForObject(
//                    sql, new Object[] { username }, String.class);
//        }catch (EmptyResultDataAccessException e){
//            return false;
//        }
//
//        return mobile.equals(password);
//
//    }
//    //返回注册的结果，注册成功返回true
//    public boolean register(String form, String username,String password){
//        String sql = "SELECT * FROM "+form+" WHERE username = ?";   //sql语句：查找具有指定用户名的条件
//        String sql1 = "INSERT INTO "+form+" (username, password) VALUES ("+username+", "+password+")";
//        // 通过jdbcTemplate查询数据库
//        try {
//            String mobile = (String)jdbcTemplate.queryForObject(
//                    sql, new Object[] { username }, String.class);
//        }catch (EmptyResultDataAccessException e){  //未找到
//            int line = jdbcTemplate.update(sql1);
//            return line == 1;
//        }
//        return false;
//    }
//
//    //删除指定的用户
//    public boolean remove(String form, String username){
//        String sql = "DELETE FROM "+form+" WHERE username = "+username;
//        int line = jdbcTemplate.update(sql);
//        return line == 1;
//    }
//    //更新指定表单中的指定用户的指定内容为新的值
//    public boolean update(String form, String username,String origin_property,String target){
//        String sql = "UPDATE "+form+" SET "+origin_property+" = '"+target+"' WHERE username = '"+username+"'";
//        int line = jdbcTemplate.update(sql);
//        return line == 1;
//    }

}
