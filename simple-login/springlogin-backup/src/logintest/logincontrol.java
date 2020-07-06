package logintest; //与servlet.xml文件中对应的包名要对应

import org.springframework.stereotype.Controller;  
import org.springframework.web.bind.annotation.RequestMapping;  
@Controller //表示为控制类
public class logincontrol {
	@RequestMapping("login")  //对应form表单 action中的内容
	public String login(String username,String password){
        if ("test".equals(username)) {//准备修改为数据库登录及注册
            System.out.println(username +" 登录成功");
            return "Success";    
        }
        return "Error";
    }   
}
