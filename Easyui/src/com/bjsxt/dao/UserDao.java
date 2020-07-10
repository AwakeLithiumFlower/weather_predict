package com.bjsxt.dao;

import java.util.List;
import java.util.Map;

import com.bjsxt.base.BaseDao;
import com.bjsxt.model.Resource;
import com.bjsxt.model.User;


public interface UserDao extends BaseDao<User> {

	List<User> findByPagination(int currentPage, int pageSize , Map<String ,Object> m ) throws Exception;

	public int getTotal(Map<String ,Object> m) throws Exception ;

	List<User> searchByName(String q) throws Exception;
	
}
