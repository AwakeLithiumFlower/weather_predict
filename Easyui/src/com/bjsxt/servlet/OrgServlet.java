package com.bjsxt.servlet;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.bjsxt.dao.OrgDao;
import com.bjsxt.dao.impl.OrgDaoImpl;
import com.bjsxt.model.Org;

public class OrgServlet extends HttpServlet {

	private OrgDao	odao = new OrgDaoImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			request.setCharacterEncoding("utf-8");
			String method = request.getParameter("method");
			if("getOrgList".equals(method)){
				getOrgList(request , response);
			} else if("save".equals(method)){
				save(request , response);
			} else if("update".equals(method)){
				update(request, response);
			} else if("delete".equals(method)){
				delete(request , response);
			}
	}

	
	
	/**
	 * 获取组织机构数据 
	 * @param request
	 * @param response
	 */
	private void getOrgList(HttpServletRequest request,
			HttpServletResponse response) {
			try{
				String id = request.getParameter("id");
				List<Org> ogrList = this.odao.findList(id);
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().write(JSONArray.fromObject(ogrList).toString());				
			}catch (Exception e) {
				e.printStackTrace();
			}
	
	}

	
	
	
	/**
	 * 保存方法
	 * @param request
	 * @param response
	 */
	private void save(HttpServletRequest request, HttpServletResponse response) {
		try {
			String name = request.getParameter("name");
			String parentId = request.getParameter("parentId");
			String principal = request.getParameter("principal");
			String count = request.getParameter("count");
			String description = request.getParameter("description");
			
			Org org = new Org();
			org.setName(name);
			org.setDescription(description);
			org.setParent_id(Integer.parseInt(parentId));
			org.setCount(Integer.parseInt(count));
			org.setPrincipal(principal);
			
			this.odao.save(org);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 修改方法
	 * @param request
	 * @param response
	 */
	private void update(HttpServletRequest request, HttpServletResponse response) {
		try {
			
			String id = request.getParameter("id");
			Org org = this.odao.findById(Integer.parseInt(id));
			
			String name = request.getParameter("name");
			String principal = request.getParameter("principal");
			String count = request.getParameter("count");
			String description = request.getParameter("description");
			
			org.setName(name);
			org.setDescription(description);
			org.setCount(Integer.parseInt(count));
			org.setPrincipal(principal);
			this.odao.update(org);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除方法
	 * @param request
	 * @param response
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response) {
		try {
			String id = request.getParameter("id");
			deleteNodes(Integer.parseInt(id));
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	private void deleteNodes(int id) throws Exception{
		
		List<Org> olist = this.odao.getChildren(id);
		for (Iterator iterator = olist.iterator(); iterator.hasNext();) {
			Org org = (Org) iterator.next();
			int cid = org.getId();
			this.odao.delete(cid);
			deleteNodes(cid);
		}
		this.odao.delete(id);
	}
	
	





	
	
	
	
	
	
	
	
	
	
	
	
}
