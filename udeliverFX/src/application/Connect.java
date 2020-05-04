package application;

import java.sql.*;

public class Connect {
	public static Connection connection;
	public static void main(String[] args)throws Exception 
	{
		getConnection();
	}

	public static com.mysql.jdbc.Connection getConnection()
	{
	
		String userName = "root";
		String password="";
	
		try{
			
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/udeliver",userName,password);
			System.out.println("Connected");
			return (com.mysql.jdbc.Connection) connection;
		}
		catch(SQLException e)
		{
			System.err.print(e);
		}
		return null;
		
	}
}
