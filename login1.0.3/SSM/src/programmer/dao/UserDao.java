package programmer.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import programmer.entity.User;

@Repository
public interface UserDao {
	public User findByUsername(String username);
	public int add(User user);
	public java.util.List<User> findList(Map<String,Object> queryMap);
	public int edit(User user);
	public int delete(String ids);
}
