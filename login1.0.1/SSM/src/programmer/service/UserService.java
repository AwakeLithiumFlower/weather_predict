package programmer.service;

import org.springframework.stereotype.Service;

import programmer.entity.User;

@Service
public interface UserService {
	public User findByUsername(String username);
}
