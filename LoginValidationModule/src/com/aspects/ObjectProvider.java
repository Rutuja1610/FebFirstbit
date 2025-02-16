package com.aspects;

import java.io.FileInputStream;
import java.util.Properties;

import com.dao.LoginDAOInterface;
import com.serviceImpln.LoginServiceImpln;
import com.services.LoginServices;

import comA.dao.AccountDAOInterface;
import comA.services.AccountServices;

//service of object creation --bussiness logic
public class ObjectProvider {

	public static LoginServices provideLoginServiceObject() {
		// TODO Auto-generated method stub
		return new LoginServiceImpln();
	}
   
	public static LoginServices createObject() {
		FileInputStream fis=null;
		LoginServices ls=null;
		try {
			//reading middleware
			fis=new FileInputStream (".//Resource//info.properties");
			Properties p=new Properties();
			p.load(fis);
			String bClass=p.getProperty("LoginBClassName");//yes
			//build the object of bussiness logic class
		   ls=(LoginServices)Class.forName(bClass).newInstance();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return ls;
	}

	public static LoginDAOInterface createDAOject() {
		LoginDAOInterface l=null;
		try {
			FileInputStream fis=null;
			LoginServices ls=null;
			fis=new FileInputStream (".//Resource//info.properties");

			Properties p=new Properties();
			p.load(fis);
			String DAO=p.getProperty("LoginDAOClass");
			//create DAO object...
			l=(LoginDAOInterface)Class.forName(DAO).newInstance();//yes
			
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
		return l;
		
	}
}

	

