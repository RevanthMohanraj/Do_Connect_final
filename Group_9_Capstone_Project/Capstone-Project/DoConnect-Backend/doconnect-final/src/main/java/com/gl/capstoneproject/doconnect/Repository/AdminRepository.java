package com.gl.capstoneproject.doconnect.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gl.capstoneproject.doconnect.Entity.Admin;


@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer>{
	
	Admin findByEmailAndPassword(String email, String password);
	
	@Query(nativeQuery = true, value = "select email from admindetails")
	List<String> FindAllAdminEmails();
}
