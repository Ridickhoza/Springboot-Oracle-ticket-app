package com.booking.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.booking.app.entity.User;




@Repository
public interface UserRepository extends JpaRepository<User,Long>{

	    
	
}
