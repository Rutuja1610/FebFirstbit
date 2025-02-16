package comA.bean;

public class Account {
	
	//private static int nextAccNo = 1001; 
	
	private int AccNo;
	private String AccType;
	private float AccBal;
	private boolean closed; // Field to track if the account is closed

	
	public Account(int AccNo, String  AccType,float AccBal) {
		this.AccNo=AccNo;
		this.AccType= AccType;
		this.AccBal=AccBal;
		 this.closed = false; // By default, account is open
	
	}

	
  
	public void setAccNo(int accNo) {
		AccNo = accNo;
	}



	public int  getAccNo() {
		return AccNo;
	}
	
	public String getAccType() {
		return AccType;
	}
	
	public float getAccBal() {
		return AccBal;
	}
	
	 public String toString() {
	        return "AccountNo:"+AccNo+"/"+"AccountType:"+AccType;
	    }

	public void setAccBal(float bal) {
		this.AccBal=bal;
		
	}



	public void setClosed(boolean b) {
	    this.closed = b;
	}

	public boolean isClosed() {
	    return closed;
	}
	
}
