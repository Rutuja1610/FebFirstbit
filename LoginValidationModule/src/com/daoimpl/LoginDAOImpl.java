package com.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.aspects.DbConnectionProvider;
import com.bean.User;
import com.dao.LoginDAOInterface;

import comA.bean.Account;

public class LoginDAOImpl implements LoginDAOInterface{

	Connection con=DbConnectionProvider.provideConnection();
	PreparedStatement pst;
	ResultSet rs;
	
	public int insertRecord(User u) {
		int updateCount=0;
		try {
			pst=con.prepareStatement("insert into Login values(?,?,?,?)");
			pst.setString(1, u.getUsername());
			pst.setString(2, u.getPassword());
			pst.setString(3, u.getSqn());
			pst.setString(4,u.getSqa());
			updateCount=pst.executeUpdate();
			 if (updateCount > 0) {
				 System.out.println("Record inserted successfully.");
				 } else {
	                System.out.println("failed");
	            }
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		 System.out.println("update count: "+updateCount);

		return updateCount ;
	}
	
	 // Fetch user details
	 public User retrieveRecord(String username) {
				User u=null;
				
				try {
					pst=con.prepareStatement("select * from Login where Username=?");
					pst.setString(1, username);
					rs=pst.executeQuery();
					if(rs.next()) {
						String Username=rs.getString(1);
						String Password=rs.getString(2);
						String SQn=rs.getString(3);
						String SQa=rs.getString(4);
						
						u=new User( username,Password,SQn,SQa);
						u.setUsername(username);
						System.out.println("Retrieved Login Data is..."+username+"/"+Password+"/"+SQn+"/"+SQa);
						
					}
					else {
						u=null;
						System.out.println("Login not found"+username);		
					}	
				}
		           catch (Exception e) {
				
				e.printStackTrace();
			}
				return u;
}


//Check password for sign-in
		
    public boolean verifyPassword(String username, String password)  {
    	try {
			pst=con.prepareStatement("SELECT password FROM Login WHERE Username = ?");
           pst.setString(1, username);
            rs = pst.executeQuery();
        if (rs.next()) {
            return rs.getString("Password").equals(password);
        }
    } 
    	catch (Exception e) {
    		
    		e.printStackTrace();
    	}
    return false;
}

    public boolean verifySecurityAnswer(String username, String answer) {
        try {
            pst = con.prepareStatement("SELECT SQa FROM Login WHERE Username = ?");
            pst.setString(1, username);
            rs = pst.executeQuery();
            
            if (rs.next()) {
                return rs.getString("SQa").equals(answer);  // Compare answer instead of question
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

	    	
	    	
    public void updatePassword(String username, String newPassword) {
        try {
            // Prepare SQL update query
            pst = con.prepareStatement("UPDATE Login SET Password = ? WHERE Username = ?");
            pst.setString(1, newPassword); // Directly storing the user-provided password
            pst.setString(2, username);
            
            int updatedRows = pst.executeUpdate();
            if (updatedRows > 0) {
                System.out.println("Password updated successfully.");
            } else {
                System.out.println("User not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }	    
	    
	    
}
