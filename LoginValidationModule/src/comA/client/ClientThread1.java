package comA.client;
	
	import java.util.Scanner;

	import comA.aspects.AccObjectProvider;
	import comA.exception.InvalidAccNumberException;
	import comA.services.AccountServices;

	public class ClientThread1 extends Thread {
		
		AccountServices Service;
		String type;
		float amount;
		int accNo;
		Thread newClient,oldClient;
		

		public ClientThread1(AccountServices Service,String cat) {
			
			this.Service = Service;
			if(cat.equals("old")){
				oldClient = new Thread(this,"oldClient");
				oldClient.start();
			}
			
	
		} 
		
	     public ClientThread1(int accNo , float amount) {
			
			this.accNo = accNo;
			this.amount = amount;
			newClient = new Thread(this,"newClient");
			newClient.start();
		
		}
	     
	 	
	     public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public float getAmount() {
			return amount;
		}

		public void setAmount(float amount) {
			this.amount = amount;
		}

		public int getAccNo() {
			return accNo;
		}

		public void setAccNo(int accNo) {
			this.accNo = accNo;
		}

		public Thread getNewClient() {
			return newClient;
		}

		public void setNewClient(Thread newClient) {
			this.newClient = newClient;
		}

		public Thread getOldClient() {
			return oldClient;
		}

		public void setOldClient(Thread oldClient) {
			this.oldClient = oldClient;
		}

		public AccountServices getService() {
			return Service;
		}

		public void setAcc(AccountServices Service) {
	     		this.Service = Service;
	     	}
	    
	     public void run() {
	    	 System.out.println("Run Method");
	    	 if(Thread.currentThread()==newClient) {
	    		 
	    		 int accNo=Service.OpenAccount(type, amount);
	    		 System.out.println("Account opened with Acc number.."+accNo);
	    	 }
	    	 if(Thread.currentThread()==oldClient) {
	    		 try {
	    			 
	    			 float currentBal=Service.deposit(accNo, amount);
		    		 System.out.println("Balance deposit is .."+currentBal);

	    			 
	    		 }catch(InvalidAccNumberException e) {
	    			 e.printStackTrace();
	    		 }
	    		 
	    		 catch(Exception e) {
	    			 e.printStackTrace();
	    		 }// mam i am not getting the msg  yes
	    	 }
	    	 
	    	 
	     }
	     


	public static void main(String[] args) {
		
		ClientThread1  c1=null;
		AccountServices service=AccObjectProvider.createObject();
		
		Scanner sc=new Scanner(System.in);
		System.out.println("New user or Existing?");
		String category=sc.next();
		
		c1=new ClientThread1(service,category);
		
		if(category.equals("new")) {
			
			System.out.println("enter type of account want to open");
			String type=sc.next();
			System.out.println("please enter account opening amount");
			float amount=sc.nextFloat();
		   //c1=new ClientThread1(menu);
		   c1.setType(type);
		   c1.setAmount(amount);//ok mam
			
		}
	  
		else {
	
			System.out.println("enter account num");
			int no=sc.nextInt();
			System.out.println("please enter account ");
			float amount=sc.nextFloat();
			//ClientThread1 c1=new ClientThread1(no,amount);
			c1.setAccNo(no);
			c1.setAmount(amount);
			
		}
		}

	}
