package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DemoJdbc {
	
	    Connection con;
	    Statement st;
	    ResultSet rs;
	
	public void connect() {
		try {
			//1.load driver class
			Class.forName("com.mysql.cj.jdbc.Driver");//package in small and class in capital
			//2.Establish connection
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/account","root","root");
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void dMLOperations() {
		try {
			st=con.createStatement();
		    int updateCount=st.executeUpdate("insert into Account values(106,'savings',5000)");
		    if(updateCount>0) {
		    	System.out.println("query is successful");
		    }
		    else {
		    	System.out.println("query failed");
		    }
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void retrieveData() {
		try {
			rs=st.executeQuery("select * from Account");
			while(rs.next()) {
				int accNo=rs.getInt(1);
				String accType=rs.getString(2);
				float Bal=rs.getFloat(3);
				System.out.println("Account Data is..."+accNo+"/"+accType+"/"+Bal);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {

		DemoJdbc j=new DemoJdbc();
		j.connect();
		j.dMLOperations();
		j.retrieveData();
	}

}
