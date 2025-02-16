package comA.client;

import java.util.Scanner;

import comA.aspects.AccObjectProvider;
import comA.exception.InvalidAccNumberException;
import comA.services.AccountServices;

public class ClientThread extends Thread {
	
	AccountServices acc;
	String type;
	float amount;
	int accNo;
	Thread newClient,oldClient;
	
	 public ClientThread(AccountServices acc) { 
	        this.acc = acc; // Ensure a valid object is passed
	    }

	
	public ClientThread(String type, float amount) {
		
		this.amount = amount;
		this.type = type;
		newClient = new Thread(this,"newClient");//yes yes y yes ok yes that will be the solution 
		newClient.start();
	
	} 
	
     public ClientThread(int accNo , float amount) {
		
		this.accNo = accNo;
		this.amount = amount;
		oldClient = new Thread(this,"oldClient");
		oldClient.start();//ok mam 
	
	}
     
     public void setAcc(AccountServices acc) {
   		this.acc = acc;
   	}
 	
    
    
     public void run() {
    	 if(Thread.currentThread()==newClient) {
    		 int accNo=acc.OpenAccount(type, amount);
    		 System.out.println("Account opened with Acc number.."+accNo);
    	 }
    	 if(Thread.currentThread()==oldClient) {
    		 try {
    			 float currentBal=acc.deposit(accNo, amount);
	    		 System.out.println("Balance deposit is .."+currentBal);
    		 }catch(InvalidAccNumberException e) {
    			 e.printStackTrace();
    		 }
    		 
    		 catch(Exception e) {
    			 e.printStackTrace();
    		 }
    	 }
    	 
    	 
     }
     


public static void main(String[] args) {
	AccountServices menu=AccObjectProvider.createObject();
	
	Scanner sc=new Scanner(System.in);
	System.out.println("New user or Existing?");
	String category=sc.next();
	
	if(category.equals("new")) {
		System.out.println("enter type of account want to open");
		String type=sc.next();
		System.out.println("please enter account opening amount");
		float amount=sc.nextFloat();
		ClientThread c1=new ClientThread(type,amount);
		c1.setAcc(menu);
	}
  
	else {
		System.out.println("enter account num");
		int no=sc.nextInt();
		System.out.println("please enter account amount ");
		float amount=sc.nextFloat();
		ClientThread c1=new ClientThread(no,amount);
		c1.setAcc(menu);
		
	}
	}

}
