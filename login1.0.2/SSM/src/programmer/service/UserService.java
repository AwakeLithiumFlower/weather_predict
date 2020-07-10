package programmer.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import programmer.entity.User;

@Service
public interface UserService {
	public User findByUsername(String username);
	//ÃÌº””√ªß
	public int add(User user);
	public java.util.List<User> findList(Map<String,Object> queryMap);
}
