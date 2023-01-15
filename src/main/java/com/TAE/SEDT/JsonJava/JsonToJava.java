package com.TAE.SEDT.JsonJava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.TAE.SEDT.JsonJava.JavaObjectClass.CustomerDetails;
import com.mysql.cj.protocol.Resultset;

public class JsonToJava {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		//Database Connection
		
		//jdbc driver for connection with database
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		//connection is null at this point
		Connection conn = null;
		
		//connection object with data for connection 'url' + user + password
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Business", "root", "admin" );
		
		//object of the statement class will help us to execute the queries, conn creat the connection statement
		Statement st = conn.createStatement();
		
		//resultset object receive statement.executequery after connect to the database
		ResultSet rs = st.executeQuery("select *  from CustomerInfo where purchasedDate=CURDATE() and Location ='Asia' Limit 1;");
		
		
		//while rs has next value then print the values (assuming that it has customerInfo has 4 columns)
		/*while(rs.next()) {
			System.out.println("Printing data from the database");
			System.out.println("-----------");
			System.out.println(rs.getString(1));
			System.out.println(rs.getString(2));
			System.out.println(rs.getInt(3));
			System.out.println(rs.getString(4));
			System.out.println("-----------");
		}*/
		
	
		//while rs has next, will create each time an customerdetails object and will set the values from bd to the java object
		while(rs.next()) {
			CustomerDetails cd = new CustomerDetails();
			cd.setCourseName(rs.getString(1));
			cd.setLocation(rs.getString(2));
			cd.setAmount(rs.getInt(3));
			cd.setPurchasedDate(rs.getString(4));
			
			System.out.println("Printing data from the java object");
			System.out.println("-----------");
			System.out.println(cd.getCourseName());
			System.out.println(cd.getLocation());
			System.out.println(cd.getAmount());
			System.out.println(cd.getLocation());
			System.out.println("-----------");
		}
		
		
		
		//convert the resultset into a java object with POJO class implementation
		
		
		//build arraylist to store multiple results from db table
		
		
		//create objects from each element of list
		
		
		//convert java object into json file using jackson api
		
		
		//send json file as input to the api automation test
		
		
		
		
		
	}

}
