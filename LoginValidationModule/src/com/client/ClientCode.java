package com.client;

import com.aspects.ObjectProvider;
import com.exception.InvalidInputException;
import com.exception.PoorPasswordStrengthException;
import com.services.LoginServices;
	
public class ClientCode {
	

	public static void main(String[] args) {
		 LoginServices service=ObjectProvider.provideLoginServiceObject();

		 service.forgetPassword("john_doe","Whats your favourite colour?", "blue");
		 
	}
}
