package comA.dao;

import comA.bean.Account;

//CRUD operations summary
public interface AccountDAOInterface {
	
	public int insertRecord(Account a);
	public Account retrieveRecord(int accNo);
	public int update(Account a);
   public int deleteAcc(Account a);
   public int getNextAccountNumber();
}