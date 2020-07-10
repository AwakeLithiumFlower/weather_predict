package com.bjsxt.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.bjsxt.dao.UserDao;
import com.bjsxt.dao.impl.UserDaoImpl;
import com.bjsxt.model.City;
import com.bjsxt.model.User;

public class UserServlet extends HttpServlet {

	private UserDao udao = new UserDaoImpl();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doPost(request, response);

	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
			request.setCharacterEncoding("utf-8");
			String method = request.getParameter("method");
			if("getCity".equals(method)){
				getCity(request , response);
			} else if("save".equals(method)){
				save(request, response);
			} else if("getList".equals(method)){
				getList(request , response);
			} else if("getCityName".endsWith(method)){
				getCityName(request , response);
			} else if("update".equals(method)){
				update(request , response);
			} else if("delete".equals(method)){
				delete(request , response);
			} else if("searchName".equals(method)){
				searchName(request , response);
			}
	}



	/**
	 * 鑾峰彇鍩庡競鏁版嵁
	 * @param request
	 * @param response
	 */
	private void getCity(HttpServletRequest request,
			HttpServletResponse response) {
			try {
				
				List<City> clist = new ArrayList<City>();
				clist.add(new City(1,"北京",0));
				clist.add(new City(2,"上海",0));
				clist.add(new City(3,"广州",0));
				clist.add(new City(4,"深圳",0));
				response.setContentType("text/html;charset=utf-8");
				String str = JSONArray.fromObject(clist).toString();
				response.getWriter().write(str);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	

	/**
	 * 鐢ㄦ埛淇濆瓨鏂规硶
	 */
	@SuppressWarnings("unused")
	private void save(HttpServletRequest request, HttpServletResponse response) {
		try {
			
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String sex = request.getParameter("sex");
			int age = Integer.parseInt(request.getParameter("age"));
			String birthday = request.getParameter("birthday");
			int city = Integer.parseInt(request.getParameter("city"));
			String salary = request.getParameter("salary");
			String startTime = request.getParameter("startTime");
			String endTime = request.getParameter("endTime");
			String description = request.getParameter("description");
			//int a = 1/0 ;
			
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			user.setSex(sex);
			user.setAge(age);
			user.setBirthday(birthday);
			user.setCity(city);
			user.setSalary(salary);
			user.setStartTime(startTime);
			user.setEndTime(endTime);
			user.setDescription(description);
			
			this.udao.save(user);
			
			response.setContentType("text/html;charset=utf-8");
			//{"status":"ok" , "message":"鎿嶄綔鎴愬姛"}
			String str = "{\"status\":\"ok\" , \"message\":\"成功!\"}";
			response.getWriter().write(str);
			
		} catch (Exception e) {
			response.setContentType("text/html;charset=utf-8");
			//{"status":"fail" , "message":"鎿嶄綔澶辫触!"}
			String str = "{\"status\":\"fail\" , \"message\":\"遇到了一点问题……\"}";
			try {
				response.getWriter().write(str);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	
	
	/**
	 * 鑾峰彇鐢ㄦ埛鍒楄〃淇℃伅
	 * @param request
	 * @param response
	 */
	private void getList(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			
			int currentPage = Integer.parseInt(request.getParameter("page"));
			int pageSize    = Integer.parseInt(request.getParameter("rows"));
				
			String username = request.getParameter("username") == null?"":request.getParameter("username");
			String startTime = request.getParameter("startTime")== null?"":request.getParameter("startTime");
			String endTime   = request.getParameter("endTime")== null?"":request.getParameter("endTime");
			String order     = request.getParameter("order")== null?"":request.getParameter("order");		//鎺掑簭鐨勬柟寮�
			String sort     = request.getParameter("sort")== null?"":request.getParameter("sort");			//鎺掑簭鐨勫瓧娈� 
			
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("username", username);
			m.put("startTime", startTime);
			m.put("startTime", startTime);
			m.put("order", order);
			m.put("sort", sort);
			
			List<User> ulist = this.udao.findByPagination(currentPage , pageSize , m);
			int total = this.udao.getTotal(m);
			response.setContentType("text/html;charset=utf-8");
			//{"total":10 , "rows":[{},{}]}
			String json = "{\"total\":"+total+" , \"rows\":"+JSONArray.fromObject(ulist).toString()+"}";
			response.getWriter().write(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	
	/**
	 * 鏍规嵁鍩庡競id鍙� 鑾峰彇鎸囧畾鍩庡競瀵硅薄 
	 * @param request
	 * @param response
	 */
	private void getCityName(HttpServletRequest request,
			HttpServletResponse response) {
		try {

			List<City> clist = new ArrayList<City>();
			clist.add(new City(1,"北京" ,0));
			clist.add(new City(2,"上海", 0));
			clist.add(new City(3,"广州", 0));
			clist.add(new City(4,"深圳" ,0));
			
			
			int id = Integer.parseInt(request.getParameter("id"));
			
			for (Iterator iterator = clist.iterator(); iterator.hasNext();) {
				City city = (City) iterator.next();
				if(city.getId() == id){
					response.setContentType("text/html;charset=utf-8");
					response.getWriter().write(JSONObject.fromObject(city).toString());
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * 淇敼鏂规硶 
	 * @param request
	 * @param response
	 */
	private void update(HttpServletRequest request, HttpServletResponse response) {
		try {
			
			int id = Integer.parseInt(request.getParameter("id"));
			User user = this.udao.findById(id);
			
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String sex = request.getParameter("sex");
			int age = Integer.parseInt(request.getParameter("age"));
			String birthday = request.getParameter("birthday");
			int city = Integer.parseInt(request.getParameter("city"));
			String salary = request.getParameter("salary");
			String startTime = request.getParameter("startTime");
			String endTime = request.getParameter("endTime");
			String description = request.getParameter("description");

			user.setUsername(username);
			user.setPassword(password);
			user.setSex(sex);
			user.setAge(age);
			user.setBirthday(birthday);
			user.setCity(city);
			user.setSalary(salary);
			user.setStartTime(startTime);
			user.setEndTime(endTime);
			user.setDescription(description);
			
			this.udao.update(user);
			
			response.setContentType("text/html;charset=utf-8");
			String str = "{\"status\":\"ok\" , \"message\":\"鎿嶄綔鎴愬姛!\"}";
			response.getWriter().write(str);
			
		} catch (Exception e) {
			response.setContentType("text/html;charset=utf-8");
			String str = "{\"status\":\"fail\" , \"message\":\"鎿嶄綔澶辫触!\"}";
			try {
				response.getWriter().write(str);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}	
	
	
	/**
	 * 鍒犻櫎鏂规硶
	 * @param request
	 * @param response
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			String[] ids = request.getParameter("ids").split(",");
			for (int i = 0; i < ids.length; i++) {
				this.udao.delete(Integer.parseInt(ids[i]));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	
	
	/**
	 * 鑷姩鎼滅储鐨勬柟娉�  
	 * @param request
	 * @param response
	 */
	private void searchName(HttpServletRequest request,
			HttpServletResponse response) {
		try{
			
			String q = request.getParameter("q")== null?"":request.getParameter("q");
			List<User> ulist = this.udao.searchByName(q.trim());
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(JSONArray.fromObject(ulist).toString());
			
			
		} catch (Exception e) {
				e.printStackTrace();
		}
	}

	

	
	
	
	

}
