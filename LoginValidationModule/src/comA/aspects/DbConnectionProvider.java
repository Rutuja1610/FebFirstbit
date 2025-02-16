package comA.aspects;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DbConnectionProvider {
	
	public static Connection provideConnection() 
	{
		Connection con=null;
		
		//Read the middleware
		try {
			FileInputStream fis=
					new FileInputStream(".//Resource/Dbinfo.properties");
			Properties p=new Properties();
			p.load(fis);
			String driverClass=p.getProperty("driver");
			String url=p.getProperty("url");
			String username=p.getProperty("username");
			String password=p.getProperty("password");
			
			//1.load driver class
			Class.forName(driverClass);//package in small and class in capital
			//2.Establish connection
			con=DriverManager.getConnection(url,username,password);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return con;
		
		
	}

}
