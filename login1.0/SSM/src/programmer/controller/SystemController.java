package programmer.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
    *   ϵͳ��ҳ������
 * @author 36334
 *
 */
import org.springframework.web.servlet.ModelAndView;

import programmer.util.CpachaUtil;
@RequestMapping("/system")
@Controller
public class SystemController {
	@RequestMapping(value = "/index",method = RequestMethod.GET)
	public String index() {
		return "hello world";//����WEB-INF������Ϊ�ַ������ļ�
	}
	
	@RequestMapping(value = "/login",method = RequestMethod.GET)
	public ModelAndView login(ModelAndView model) {
		model.setViewName("system/login");
		return model;
	}
	
	//��֤��
	@RequestMapping(value = "/get_kpacha",method = RequestMethod.GET)
	public void getKpacha(HttpServletRequest request ,HttpServletResponse response) {
		//System.out.println("��ȡ��֤��");
		CpachaUtil cpachaUtil = new CpachaUtil(4,98,33);
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
