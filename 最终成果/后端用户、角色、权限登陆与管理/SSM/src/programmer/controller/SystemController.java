package programmer.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
/**
    *   系统主页控制器
 * @author 36334
 *
 */
import org.springframework.web.servlet.ModelAndView;

import com.sun.javafx.collections.MappingChange.Map;

import programmer.entity.User;
import programmer.service.UserService;
import programmer.util.CpachaUtil;
@RequestMapping("/system")
@Controller
public class SystemController {
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value = "/index",method = RequestMethod.GET)
	public String index() {
		return "system/index";//会在WEB-INF下找名为字符串的文件
	}//this is a test
	
	/**
	 * 登录页面
	 * @return
	 */
	@RequestMapping(value = "/login",method = RequestMethod.GET)
	public ModelAndView login(ModelAndView model) {
		model.setViewName("system/login");
		return model;
	}
	
	/**
	 * 登录表单提交
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	@ResponseBody
	//↑转为json字符串返回
	public java.util.Map<String,String> login(
			@RequestParam(value = "username",required = true) String username,
			@RequestParam(value = "password",required = true) String password,
			@RequestParam(value = "vcode",required = true) String vcode,
			@RequestParam(value = "type",required = true) int type,
 			HttpServletRequest request,
 			HttpServletResponse response
			) throws IOException {
		//Map<String, String>ret =  new HashMap<>();
		java.util.Map<String, String> ret = new HashMap<String, String>();
		if(StringUtils.isEmpty(username)) {
			ret.put("type","error");
			ret.put("msg","username cannot be empty");
			return ret;
		}
		if(StringUtils.isEmpty(password)) {
			ret.put("type","error");
			ret.put("msg","password cannot be empty");
			return ret;
		}
		if(StringUtils.isEmpty(vcode)) {
			ret.put("type","error");
			ret.put("msg","Verification code cannot be empty");
			return ret;
		}
		//验证码校验
		String logvcode = (String)request.getSession().getAttribute("loginK");
		//判空
		if(StringUtils.isEmpty(logvcode)) {
			ret.put("type","error");
			ret.put("msg","Timeout, please refresh");
			return ret;
		}
		//判对错
		if(!vcode.toUpperCase().equals(logvcode.toUpperCase())) {
			ret.put("type","error");
			ret.put("msg","Verification code error");
			return ret;
		}
		request.getSession().setAttribute("loginK",null);
		//数据库
		if(type == 1) {
			
			User user = userService.findByUsername(username);
			if(user == null) {
				ret.put("type","error");
				ret.put("msg","user is not exist");
				return ret;
			}
			if(!password.equals(user.getPassword())) {
				ret.put("type","error");
				ret.put("msg","password error");
				return ret;
			}
			request.getSession().setAttribute("user", user);
			ret.put("type","success");
			ret.put("msg", "success login");
		}
		else if(type == 2) {
			
			User user = userService.findByUsername(username);
			if(user == null) {
				ret.put("type","error");
				ret.put("msg","user is not exist");
				return ret;
			}
			if(!password.equals(user.getPassword())) {
				ret.put("type","error");
				ret.put("msg","wrong password");
				return ret;
			}
			request.getSession().setAttribute("user", user);
			ret.put("type","2");
			ret.put("msg", "success login");
		}
		else if(type == 3) {
			
			User user = userService.findByUsername(username);
			if(user == null) {
				ret.put("type","error");
				ret.put("msg","user is not exist");
				return ret;
			}
			if(!password.equals(user.getPassword())) {
				ret.put("type","error");
				ret.put("msg","wrong password");
				return ret;
			}
			request.getSession().setAttribute("user", user);
			ret.put("type","3");
			ret.put("msg", "success login");
		}
		
		//都正确
		return ret;
	}
	
	//验证码
	@RequestMapping(value = "/get_kpacha",method = RequestMethod.GET)
	//加入参数可以使得每次点击验证码时都重新获取
	public void getKpacha(HttpServletRequest request ,
			@RequestParam(value = "vl",defaultValue = "4",required = false) Integer vl,
			@RequestParam(value = "w",defaultValue = "98",required = false) Integer w,
			@RequestParam(value = "h",defaultValue ="33",required = false) Integer h,			
			HttpServletResponse response) {
		//System.out.println("获取验证码");
		CpachaUtil cpachaUtil = new CpachaUtil(vl,w,h);
		String generatorVCode = cpachaUtil.generatorVCode();
		request.getSession().setAttribute("loginK", generatorVCode);
		BufferedImage image =cpachaUtil.generatorRotateVCodeImage(generatorVCode, true);
		try {
			ImageIO.write(image, "gif", response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}