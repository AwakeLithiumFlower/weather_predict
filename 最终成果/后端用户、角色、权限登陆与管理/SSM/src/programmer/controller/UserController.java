package programmer.controller;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import programmer.entity.User;
import programmer.service.UserService;

/**
 * �û�����Ա������
 * @author 36334
 *
 */
@RequestMapping("/user")
@Controller
public class UserController {
	
	@Autowired
	public UserService userService;
	
	/**
	 *  �û������б�ҳ
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	public ModelAndView list(ModelAndView model) {
		model.setViewName("user/user_list");
		return model;
	}
	
	@RequestMapping(value = "/get_list",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getList(){
		Map<String, Object > ret = new HashMap<String, Object>();
		Map<String, Object > queryMap = new HashMap<String, Object>();
		queryMap.put("username", "%%");
		queryMap.put("offset", 0);
		queryMap.put("pageSize", 10);
		ret.put("rows", userService.findList(queryMap));
		ret.put("total", 10);//easyUI dataGrid���������ĸ�ʽ
		
		
		return ret;
	}
	
	/**
	 * ����û�
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(User user){
		Map<String, String > ret = new HashMap<String, String>();
		if(user == null) {
			ret.put("type", "error");
			ret.put("msg", "������");
			return ret;
		}
		if(StringUtils.isEmpty(user.getUsername())) {
			ret.put("type", "error");
			ret.put("msg", "�û�������Ϊ��");
			return ret;
		}
		if(StringUtils.isEmpty(user.getPassword())) {
			ret.put("type", "error");
			ret.put("msg", "���벻��Ϊ��");
			return ret;
		}
		//���ݿ����
		//�û�������
		User existUser = userService.findByUsername(user.getUsername());
		if(existUser!=null) {
			ret.put("type", "error");
			ret.put("msg", "�û��Ѵ���");
			return ret;
		}
		if(userService.add(user)<=0) {
			ret.put("type", "error");
			ret.put("msg", "���ʧ��");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "��ӳɹ�");
		return ret;
	}
	
	/**
	 * �༭�û�����
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/edit",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> edit(User user){
		Map<String, String > ret = new HashMap<String, String>();
		if(user == null) {
			ret.put("type", "error");
			ret.put("msg", "������");
			return ret;
		}
		if(StringUtils.isEmpty(user.getUsername())) {
			ret.put("type", "error");
			ret.put("msg", "�û�������Ϊ��");
			return ret;
		}
		if(StringUtils.isEmpty(user.getPassword())) {
			ret.put("type", "error");
			ret.put("msg", "���벻��Ϊ��");
			return ret;
		}
		//���ݿ����
		//�û�������
		User existUser = userService.findByUsername(user.getUsername());
		if(existUser!=null) {
			if(user.getId()!=existUser.getId()) {
				ret.put("type", "error");
				ret.put("msg", "�û��Ѵ���");
				return ret;
			}
		}
		if(userService.edit(user)<=0) {
			ret.put("type", "error");
			ret.put("msg", "�޸�ʧ��");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "�޸ĳɹ�");
		return ret;
	}
	
	/**
	 * ɾ���û�����
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/delete",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delete(
			@RequestParam(value = "ids[]",required = true) Long[] ids
			){
		Map<String, String > ret = new HashMap<String, String>();
		if(ids == null) {
			ret.put("type", "error");
			ret.put("msg", "ѡ��ɾ������");
			return ret;
		}
		String idString="";
		for(Long id:ids) {
			idString += id + ",";
		}
		idString = idString.substring(0,idString.length()-1);
		if(userService.delete(idString)<=0) {
			ret.put("type", "error");
			ret.put("msg", "ɾ��ʧ��");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "ɾ���ɹ�");
		return ret;
	}
}
