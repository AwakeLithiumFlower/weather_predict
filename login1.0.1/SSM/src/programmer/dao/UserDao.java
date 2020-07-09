package programmer.dao;

import org.springframework.stereotype.Repository;

import programmer.entity.User;

@Repository
public interface UserDao {
	public User findByUsername(String username);
}
