package com.gl.capstoneproject.doconnect.Service;

import com.gl.capstoneproject.doconnect.Model.Admins;

public interface AdminService {
	
	public String adminLogin(Admins admin);
	public String adminRegister(Admins admin);
}
