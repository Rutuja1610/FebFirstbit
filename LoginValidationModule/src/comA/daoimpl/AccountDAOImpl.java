package comA.daoimpl;

import java.sql.Connection;//
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.aspects.DbConnectionProvider;

import comA.bean.Account;
import comA.dao.AccountDAOInterface;//yes mam yes

//CRUD operations implementation
public class AccountDAOImpl implements AccountDAOInterface
{
	//get db connection from provider class
	Connection con=DbConnectionProvider.provideConnection();
	PreparedStatement pst;
	ResultSet rs;
	
	public int insertRecord(Account a) {
		int updateCount=0;
		try {
			pst=con.prepareStatement("insert into Account values(?,?,?)");
			pst.setInt(1, a.getAccNo());
			pst.setString(2, a.getAccType());
			pst.setFloat(3, a.getAccBal());
			updateCount=pst.executeUpdate();
			 if (updateCount > 0) {
				 System.out.println("Account inserted successfully.");
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
	
	public Account retrieveRecord(int accNo) {
		Account a=null;
		
		try {
			pst=con.prepareStatement("select * from Account where accNo=?");
			pst.setInt(1, accNo);
			rs=pst.executeQuery();
			if(rs.next()) {
				int No=rs.getInt(1);
				String type=rs.getString(2);
				float bal=rs.getFloat(3);
				a=new Account(accNo,type,bal);
				a.setAccNo(accNo);
				System.out.println("Retrieved Account Data is..."+No+"/"+type+"/"+bal);
				
			}
			else {
				a=null;
				System.out.println("Account not found"+accNo);

				
			}	
				
		}
           catch (Exception e) {
		
		e.printStackTrace();
	}
		return a;
	}
	
	
	public int update(Account a) 
	{
	    int rowsUpdated = 0;
	    try {
	        // Prepare update statement
	        pst = con.prepareStatement("UPDATE Account SET balance = ? WHERE accNo = ?");
	        pst.setFloat(1, a.getAccBal()); 
	        pst.setInt(2, a.getAccNo());      
	        // Execute update
	        rowsUpdated = pst.executeUpdate();

	        if (rowsUpdated > 0) {
	            System.out.println("Account updated successfully. New Balance: " + a.getAccBal());
	        } else {
	            System.out.println("Account not found or balance unchanged.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		 System.out.println("update count: "+rowsUpdated);

	    return rowsUpdated;
	}
   
	
	public int deleteAcc(Account a) {
	
		int delaccount=0;
		
		try {
	        // Prepare update statement
			
	        pst = con.prepareStatement("DELETE from Account WHERE accNo = ?");
	        if (!rs.next()) {
	            System.out.println("Account not found. Cannot delete.");
	            return 0; 
	        }
	        pst.setInt(1, a.getAccNo());      
	        // Execute update
	        delaccount = pst.executeUpdate();

	        if ( delaccount > 0) {
	            System.out.println("Account deleted successfully.");
	        } else {
	            System.out.println("Account not found.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (pst != null) pst.close(); 
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
		 System.out.println("delete count: "+delaccount);
	    return delaccount; 
	}

	public int getNextAccountNumber() {
	    int nextAccNo = 1001; // Default starting number

	    try {
	        pst = con.prepareStatement("SELECT MAX(accNo) FROM Account");
	        rs = pst.executeQuery();

	        if (rs.next()) {
	            int lastAccNo = rs.getInt(1);
	            if (lastAccNo > 0) {
	                nextAccNo = lastAccNo + 1; // Generate the next sequential number
	            }
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return nextAccNo;
	}




}