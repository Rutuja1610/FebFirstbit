package comA.serviceImpl;//yes


import java.util.Random;

import comA.aspects.AccObjectProvider;
import comA.bean.Account;
import comA.dao.AccountDAOInterface;
import comA.daoimpl.AccountDAOImpl;
import comA.exception.InvalidAccNumberException;
import comA.services.AccountServices;

public class AccountServiceImpl implements AccountServices{
	
	 Account account;
	 
	 //interaction with dao layer
	 AccountDAOInterface dao=AccObjectProvider.createDAOject(); //yes
	 

	 public int OpenAccount(String Acctype, float amount) {
		    System.out.println("Opening account...");

		    int accNo = generateUniqueAccNo(); // Get a bank-generated unique number
		    if (accNo == -1) {
		        System.out.println("Failed to generate account number.");
		        return -1;
		    }

		    // Create an account object with the assigned number
		    account = new Account(accNo, Acctype, amount);

		    // Insert the account into the database
		    int insertStatus = dao.insertRecord(account);
		    if (insertStatus > 0) {
		        System.out.println("Account successfully opened with Account No: " + accNo);
		    } else {
		        System.out.println("Failed to insert account into database.");
		        return -1;
		    }

		    return accNo;  // Return the assigned account number
		}


	
	 public int generateUniqueAccNo() {
		    int accNo = -1; // Default invalid account number
		    
		    if (dao == null) {
		        System.out.println("DAO is not initialized!");
		        return -1; // Indicate failure
		    }

		    try {
		        accNo = dao.getNextAccountNumber(); // Fetch the next available account number
		        System.out.println("Bank provided Account Number: " + accNo);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }

		    return accNo;
		}



	
	
	public float deposit(int accNo,float amount) throws InvalidAccNumberException
	{
		float bal=0.0f;
		Account account=dao.retrieveRecord(accNo);
		if(account!=null) {
			synchronized(this) {
				bal = account.getAccBal() + amount; 
			   account.setAccBal(bal);
			   dao.update(account);
		}
		}
		
		else {
			throw new InvalidAccNumberException("invalid ");
		}
		return bal;
	}
	
	
	public float balEnq(int AccNo) {
	    Account account = dao.retrieveRecord(AccNo);

	    if (account == null) {
	        System.out.println("Account not found. Returning balance as 0.");
	        return 0;  // Return 0 if the account doesn't exist
	    }

	    return account.getAccBal();
	}

	
	public String printAccDetails(int AccNo) {
		Account account=dao.retrieveRecord(AccNo);
		return toString();
		
	}
	
	public void closeAccount(int accNo) {
	    // Check if the given account number matches
		Account account=dao.retrieveRecord(accNo);
	    if (account!=null) {
	 
	        // Close the account
	        account.setClosed(true); 
	        dao.deleteAcc(account);
	        System.out.println("Account closed successfully.");
	    } else {
	        System.out.println("Account not found.");
	    }
	}


	
	
	

}
