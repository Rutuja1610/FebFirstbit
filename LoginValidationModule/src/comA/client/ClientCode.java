package comA.client;

import com.aspects.ObjectProvider;
import com.services.LoginServices;

import comA.aspects.AccObjectProvider;
import comA.exception.InvalidAccNumberException;
import comA.services.AccountServices;

public class ClientCode {

	public static void main(String[] args) { //  yes thankyou mam yess sure
		// TODO Auto-generated method stub
	
//		 LoginServices service=ObjectProvider.provideLoginServiceObject();
//		    service.signUp("rutu","java","fav colour", "violet");
		    AccountServices acc=AccObjectProvider.provideAccountServiceObject();
		  acc.balEnq(1001);
		    
		    
            
		   

	}

}
