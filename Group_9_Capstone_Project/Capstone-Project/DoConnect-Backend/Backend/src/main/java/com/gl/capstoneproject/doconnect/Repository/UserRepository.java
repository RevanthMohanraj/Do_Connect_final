package com.gl.capstoneproject.doconnect.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gl.capstoneproject.doconnect.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	User findByEmailAndPassword(String email, String password);
	
	

}
