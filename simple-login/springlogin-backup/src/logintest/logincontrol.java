package logintest; //��servlet.xml�ļ��ж�Ӧ�İ���Ҫ��Ӧ

import org.springframework.stereotype.Controller;  
import org.springframework.web.bind.annotation.RequestMapping;  
@Controller //��ʾΪ������
public class logincontrol {
	@RequestMapping("login")  //��Ӧform�� action�е�����
	public String login(String username,String password){
        if ("test".equals(username)) {//׼���޸�Ϊ���ݿ��¼��ע��
            System.out.println(username +" ��¼�ɹ�");
            return "Success";    
        }
        return "Error";
    }   
}
