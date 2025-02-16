package comA.services;


import comA.exception.InvalidAccNumberException;

public interface AccountServices {
	
	public int OpenAccount(String Acctype,float amount);
	public float deposit(int accNo,float amount)throws InvalidAccNumberException;
	public float balEnq(int AccNo);
	public String printAccDetails(int AccNo);
	public void closeAccount(int AccNo);
	

}
