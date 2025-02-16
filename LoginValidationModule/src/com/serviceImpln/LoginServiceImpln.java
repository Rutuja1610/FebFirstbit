package com.serviceImpln;

import com.aspects.ObjectProvider;
import com.bean.User;
import com.dao.LoginDAOInterface;
import com.exception.InvalidInputException;
import com.exception.PoorPasswordStrengthException;
import com.services.LoginServices;



public class LoginServiceImpln implements LoginServices{
	
	//data is required for bussiness logic ---has with user
	User user;
	LoginDAOInterface dao=ObjectProvider.createDAOject(); //y
	public void signUp(String u,String p,String q,String a) {
		user=new User(u,p,q,a);
		 dao.insertRecord(user);
	}
	public boolean signIn(String u,String p)throws PoorPasswordStrengthException,InvalidInputException{
		boolean outcome=false;
		if(p.length()<=8) {
			throw new PoorPasswordStrengthException("password len should be 8 char");
		}
		if(u==" "||u=="Null") {
			throw new InvalidInputException("Username should contain characters");
		}
		
		  User user = dao.retrieveRecord(u);
	        if (user != null && user.getPassword().equals(p)) {
	            return true; // Successful login
	        }
	        return false; // Invalid credentials
	
	
}
	public String forgetPassword(String u, String q, String a) {
		User user = dao.retrieveRecord(u);
		 if (user != null && user.getSqn().equals(q) && dao.verifySecurityAnswer(u, a)) {
	            String newPassword = "newPass123"; // In real cases, generate a secure random password
	            dao.updatePassword(u, newPassword);
	            return "Your new password is: " + newPassword;
	        }
	        return "Invalid credentials!";
	    }

    // Update Password Method
	public void updatePassword(String u, String oldPassword, String newPassword) {
        User user = dao.retrieveRecord(u);
        
        if (user != null && dao.verifyPassword(u, oldPassword)) {
            dao.updatePassword(u, newPassword);
            System.out.println("Password updated successfully!");
        } else {
            System.out.println("Incorrect username or password!");
        }
    }

}
