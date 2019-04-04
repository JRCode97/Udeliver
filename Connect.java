package drivingInterface;
import java.sql.*;

public class Connect {
	public static Connection connection;
	public static void main(String[] args)throws Exception 
	{
		//getConnection();
		 CreateTable();
	}
	public static void CreateTable() throws Exception{
		try
		{
			Connection con = getConnection();
			PreparedStatement create = con.prepareStatement("CREATE TABLE IF NOT EXISTS tablename(id int NOT NULL AUTO_INCREMENT,first varchar(255),last varchar(255),PRIMARY KEY(id))");
			create.executeUpdate();
			System.out.println("went through");
		}
		catch(Exception e){System.out.println(e);
			
		}
		finally{System.out.println("function complete");}
	}
	public static Connection getConnection()
	{
		String DBName = "udeliver";
		String userName = "root";
		String password="";
	
		try{
			
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/udeliver",userName,password);
			System.out.println("Connected");
			return connection;
		}
		catch(SQLException e)
		{
			System.err.print(e);
		}
		return null;
		
	}
}
