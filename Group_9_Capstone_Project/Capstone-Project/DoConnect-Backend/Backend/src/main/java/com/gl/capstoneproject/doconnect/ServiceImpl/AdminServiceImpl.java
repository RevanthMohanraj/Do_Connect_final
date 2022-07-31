package com.gl.capstoneproject.doconnect.ServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.capstoneproject.doconnect.Entity.Admin;

import com.gl.capstoneproject.doconnect.Model.Admins;

import com.gl.capstoneproject.doconnect.Repository.AdminRepository;
import com.gl.capstoneproject.doconnect.Service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private AdminRepository adminRepo;

	public String adminLogin(Admins admin) {
		try {
			Admin adminlogin = adminRepo.findByEmailAndPassword(admin.getEmail(), admin.getPassword());
			if (adminlogin.getEmail().equals(adminlogin.getEmail())
					&& adminlogin.getPassword().equals(adminlogin.getPassword())) {
				return "Login Success";
			} else {
				return "User does not exist! Please register :)";
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return "User does not exist! Please register :)";
		}

	}

	public String adminRegister(Admins admin) {
		try {
			Admin adminRegister = new Admin();
			adminRegister.setId(admin.getId());
			adminRegister.setName(admin.getName());
			adminRegister.setDateOfBirth(admin.getDateOfBirth());
			adminRegister.setGender(admin.getGender());
			adminRegister.setPassword(admin.getPassword());
			adminRegister.setEmail(admin.getEmail());

			adminRepo.save(adminRegister);
			return "registered successfully";
		} catch (Exception e) {
			return null;
		}
	}
}
