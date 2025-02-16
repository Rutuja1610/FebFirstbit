package com.dao;

import com.bean.User;

import comA.bean.Account;

public interface LoginDAOInterface {

	    public int insertRecord(User u); // Sign up
	    public User retrieveRecord(String username); // Fetch user details
	    public boolean verifyPassword(String username, String password); // Check password for sign-in
	    public boolean verifySecurityAnswer(String username, String answer); // Security check
	    public void updatePassword(String username, String newPassword); // Change password
	    
	    
	  
}
