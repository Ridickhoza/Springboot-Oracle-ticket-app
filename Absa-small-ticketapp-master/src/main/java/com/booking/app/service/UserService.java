package com.booking.app.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.app.entity.User;
import com.booking.app.repository.UserRepository;



@Service
public class UserService {
     
	@Autowired
	private UserRepository userRepository;
	
	public User createUser(User user)
	{
		return this.userRepository.save(user);
	}
	
	
	public  User returnUser(String email,String password)
	{
		
		List<User> user = this.userRepository.findAll();
		//boolean valid = false;
		for(User u: user)
		{
			
			try {
				if(u.getEmail().equals(email) && u.getPassword().equals(password))
				{
					System.out.println("Email " + u.getEmail() + " " + "Pass" +  u.getPassword());
					//valid = true;
					return u;
				}
					
							
				/*else {
					valid = false;
					//return null;
				}*/
				
				
				
			}catch(NullPointerException e) {
				
				System.out.print("NullPointerException caught...please enter login credentials");
			}
			
		}
		
	/*	if(valid==false) {
			System.out.println("Invalid credentials");
		}*/
		
		return null;
	}
	
	
}
