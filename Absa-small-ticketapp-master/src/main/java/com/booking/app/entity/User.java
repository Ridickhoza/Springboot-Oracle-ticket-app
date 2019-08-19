package com.booking.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {
   @Id
   @Column(name="user_ID")
   @GeneratedValue(strategy = GenerationType.AUTO)
	private Long user_id;
	
    @Column(name="email")
	private String email;
	
    @Column(name="password")
	private String password;

	public Long getId() {
		return user_id;
	}

	public void setId(Long user_id) {
		this.user_id = user_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
	
}
