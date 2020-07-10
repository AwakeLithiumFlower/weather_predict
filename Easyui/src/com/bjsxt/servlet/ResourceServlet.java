package com.bjsxt.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.bjsxt.dao.ResourceDao;
import com.bjsxt.dao.impl.ResourceDaoImpl;
import com.bjsxt.dto.TreeDTO;
import com.bjsxt.model.Resource;

public class ResourceServlet extends HttpServlet {

	private ResourceDao rdao = new ResourceDaoImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			request.setCharacterEncoding("utf-8");
			String method = request.getParameter("method");
			
			if("loadTree".equals(method)){
				loadTree(request , response);
			} else if("changeLevel".equals(method)){
				changeLevel(request , response);
			} else if("save".equals(method)){
				save(request , response);
			} else if("update".equals(method)){
				update(request,response);
			} else if("delete".equals(method)){
				delete(request ,response);
			}

	}


	/**
	 * 加载tree的数据方法 
	 * @param request
	 * @param response
	 */
	private void loadTree(HttpServletRequest request,
			HttpServletResponse response) {
		try {
				//获取当前展开的节点id 
				String id = request.getParameter("id");
				List<TreeDTO> treelist = this.rdao.getChildrenByParentId(id);
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().write(JSONArray.fromObject(treelist).toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * 改变节点的层次结构方法  
	 * @param request
	 * @param response
	 */
	private void changeLevel(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			
			String targetId = request.getParameter("targetId");
			String sourceId = request.getParameter("sourceId");
			String point    = request.getParameter("point");
			//得到目标对象
			Resource  target = this.rdao.findById(Integer.parseInt(targetId));
			//得到操作的对象(源对象)
			Resource  source = this.rdao.findById(Integer.parseInt(sourceId));
			// append  top bottom
			if("append".equals(point)){
				source.setParent_id(target.getId());
			} else {
				source.setParent_id(target.getParent_id());
			}
			this.rdao.update(source);
			
		} catch (Exception e) {
				e.printStackTrace();
		}
	}

	/**
	 * 保存节点的方法
	 * @param request
	 * @param response
	 */
	private void save(HttpServletRequest request, HttpServletResponse response) {
			try {
				String parentId = request.getParameter("parentId");
				String name = request.getParameter("name");
				String url  = request.getParameter("url");
				
				Resource r = new Resource();
				r.setName(name);
				r.setUrl(url);
				r.setParent_id(Integer.parseInt(parentId));
				this.rdao.save(r);
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	/**
	 * 更新节点
	 * @param request
	 * @param response
	 */
	private void update(HttpServletRequest request, HttpServletResponse response) {
			try{
				String id = request.getParameter("id");
				Resource r = this.rdao.findById(Integer.parseInt(id));
				
				String name = request.getParameter("name");
				String url  = request.getParameter("url");
				
				r.setName(name);
				r.setUrl(url);
				this.rdao.update(r);
								
			}catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	/**
	 * 删除节点
	 * @param request
	 * @param response
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response) {
		try {
			String id = request.getParameter("id");
			deletenodes(Integer.parseInt(id));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * 递归删除节点们
	 * @param id
	 */
	private void deletenodes(int id) throws Exception{
		//获取当前要删除的节点下面所有的孩子 
		List<Resource> rlist = this.rdao.getChildren(id);
		for(int i = 0 ; i < rlist.size();i++){
					int cid = rlist.get(i).getId();
					this.rdao.delete(cid);	//删除操作 
					deletenodes(cid);
		}
		this.rdao.delete(id);
	}
	
	
	





	
	
	
	

}
