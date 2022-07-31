package com.gl.capstoneproject.doconnect.ServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gl.capstoneproject.doconnect.Entity.User;
import com.gl.capstoneproject.doconnect.Model.UserLogin;
import com.gl.capstoneproject.doconnect.Repository.UserRepository;
import com.gl.capstoneproject.doconnect.Service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepo;

	public String login(String body) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			UserLogin login = mapper.readValue(body, UserLogin.class);
			User user = userRepo.findByEmailAndPassword(login.getEmail(), login.getPassword());
			
			if (null != user) {
				if (user.getEmail().equals(login.getEmail()) && user.getPassword().equals(login.getPassword())) {
					return "Login Success";
				} else {
					return "User does not exist! Please register :)";
				}
			} else {
				return "User does not exist! Please register :)";
			}
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return null;
		}
		
	}

	public String register(String body) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			UserLogin register = mapper.readValue(body, UserLogin.class);
			User user1 = new User();
			user1.setId(register.getId());
			user1.setName(register.getName());
			user1.setDateOfBirth(register.getDateOfBirth());
			user1.setGender(register.getGender());
			user1.setPassword(register.getPassword());
			user1.setEmail(register.getEmail());
			
			userRepo.save(user1);
			return "registered successfully";
		} catch (Exception e) {
			return null;
		}
	}	
}