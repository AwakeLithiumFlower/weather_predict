package com.bjsxt.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.bjsxt.dao.ProvinceDao;
import com.bjsxt.dao.impl.ProvinceDaoImpl;
import com.bjsxt.model.City;
import com.bjsxt.model.Province;

public class ProvinceServlet extends HttpServlet {

	private ProvinceDao pdao = new ProvinceDaoImpl();
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doPost(request, response);

	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			String method = request.getParameter("method");

			if("getPro".equals(method)){
				getPro(request , response);
			} else if("getCity".equals(method)){
				getCity(request , response);
			}
	}


	/**
	 * 获取所有的省份 
	 * @param request
	 * @param response
	 */
	private void getPro(HttpServletRequest request, HttpServletResponse response) {
		try {
			
			List<Province> plist = this.pdao.findAll();
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(JSONArray.fromObject(plist).toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据省份的id 获取所有的城市 
	 * @param request
	 * @param response
	 */
	private void getCity(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String pid = request.getParameter("pid");
			List<City> clist = this.pdao.findCitiesByProId(Integer.parseInt(pid));
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(JSONArray.fromObject(clist).toString());			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	

}
