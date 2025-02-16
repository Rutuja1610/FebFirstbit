package com.services;

import com.exception.InvalidInputException;
import com.exception.PoorPasswordStrengthException;

//menu card
public interface LoginServices 
{
	
	public void signUp(String u,String p,String q,String a);
	public boolean signIn(String u,String p)throws PoorPasswordStrengthException,InvalidInputException;
	public String forgetPassword(String u,String q,String a);
	public void updatePassword(String u,String p,String newpPass);
	

}
