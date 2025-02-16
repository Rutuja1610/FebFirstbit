package comA.aspects;

import java.io.FileInputStream;
import java.util.Properties;

import comA.dao.AccountDAOInterface;
import comA.daoimpl.AccountDAOImpl;
import comA.serviceImpl.AccountServiceImpl;
import comA.services.AccountServices;

public class AccObjectProvider {

	
	public static AccountServices provideAccountServiceObject() {
		// TODO Auto-generated method stub
		return new AccountServiceImpl();
	}

	public static AccountServices  createObject() {
		FileInputStream fis=null;
		AccountServices acc=null;
		try {
			//reading middleware
			fis=new FileInputStream (".//Resources//info.properties");
			Properties p=new Properties();
			p.load(fis);
			String bClass=p.getProperty("BClassName");//yes
			//build the object of bussiness logic class
			acc=(AccountServices)Class.forName(bClass).newInstance();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return acc;
	}

	public static AccountDAOInterface createDAOject() {
		 AccountDAOInterface a=null;
		try {
			FileInputStream fis=null;
			AccountServices acc=null;
			fis=new FileInputStream (".//Resources//info.properties");

			Properties p=new Properties();
			p.load(fis);
			String DAO=p.getProperty("DAOClass");
			//create DAO object...
			a=(AccountDAOInterface)Class.forName(DAO).newInstance();
			
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
		return a;
		
	}
}


