package com.jdbc;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class Demo2JDBC {

	Connection con;
    Statement st;
    ResultSet rs;
    PreparedStatement pst;

public void connect() {
	try {
		FileInputStream fis=new FileInputStream(".//Resource/Dbinfo.properties");
		Properties p=new Properties();
		p.load(fis);
		String driverClass=p.getProperty("driver");
		String url=p.getProperty("url");
		String username=p.getProperty("username");
		String password=p.getProperty("password");
		
		//1.load driver class
		Class.forName(driverClass);//package in small and class in capital
		//2.Establish connection
		con=DriverManager.getConnection(url,username,password);
		
	}
	catch(Exception e) {
		e.printStackTrace();
	}
}

public void dMLOperations1(int no,String type,float bal) {
	int updateCount=0;
	try {
		//st=con.createStatement();
	   // int updateCount=st.executeUpdate("insert into Account values(106,'savings',5000)");
	    pst=con.prepareStatement("insert into Account values(?,?,?)");
	    pst.setInt(1,no);
	    pst.setString(2, type);
	    pst.setFloat(3,bal);
	    updateCount=pst.executeUpdate();
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

//task- update query where accbal>40000 change type=current
public void dMLOperations2(int no, float bal) {
    int updateCount = 0;
    try {
      
        if (bal > 40000) {
            String type = "current"; 

            pst = con.prepareStatement("Update Account SET accType = ? WHERE accNo = ?");
            pst.setString(1, type);
            pst.setInt(2, no);

            updateCount = pst.executeUpdate();
            if (updateCount > 0) {
                System.out.println("Account type updated to 'current' successfully.");
            } else {
                System.out.println("Account update failed.");
            }
        } else {
            System.out.println("Balance is not greater than 40000. No update performed.");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}

public void retrieveData(int no) {
	try {
		
		//rs=st.executeQuery("select * from Account");
		pst=con.prepareStatement("select * from Account where accNo=?");
		pst.setInt(1, no);
		rs=pst.executeQuery();
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


public void deleteAccounts(float bal) {
    int updateCount = 0;
    try {
        pst = con.prepareStatement("Delete from Account WHERE balance < ?");
        pst.setFloat(1, bal);
        
        updateCount = pst.executeUpdate();
        if (updateCount > 0) {
            System.out.println(updateCount + " account(s) with balance < 5000 deleted successfully.");
        } else {
            System.out.println("No accounts found with balance < 5000.");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}

public static void main(String[] args) {
	
	Scanner sc=new Scanner(System.in);
	System.out.println("Enter acc number");
	int no=sc.nextInt();
	System.out.println("Enter acc type");
	String type=sc.next();
	System.out.println("Enter acc bal");
	float bal=sc.nextFloat();
	
	Demo2JDBC j=new Demo2JDBC();
	j.connect();
	j.dMLOperations1(no,type,bal);
	
	//j.dMLOperations2(no, bal);
	j.deleteAccounts(bal);
	j.retrieveData(no);
	
}

}



