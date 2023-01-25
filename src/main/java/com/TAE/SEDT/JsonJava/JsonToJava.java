package com.TAE.SEDT.JsonJava;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.json.simple.JSONObject;

import com.TAE.SEDT.JsonJava.JavaObjectClass.CustomerDetails;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.protocol.Resultset;

public class JsonToJava {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, StreamWriteException, DatabindException, IOException {
		//Database Connection
		
		//jdbc driver for connection with database
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		//declare CustomerDetails cd equal null and inside while rs has next create a new object of cd;
		CustomerDetails cd = null;
		
		//arraylist to store the java objects from customerDetails (cd) 
		ArrayList<CustomerDetails> cdList = new ArrayList<CustomerDetails>();
		
		//connection is null at this point
		Connection conn = null;
		
		//connection object with data for connection 'url' + user + password
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Business", "root", "admin" );
		
		//object of the statement class will help us to execute the queries, conn creat the connection statement
		Statement st = conn.createStatement();
		
		//resultset object receive statement.executequery after connect to the database
		ResultSet rs = st.executeQuery("select *  from CustomerInfo where purchasedDate=CURDATE() and Location ='Asia';");
		
		
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
			//convert the resultset into a java object with POJO class implementation
			
			cd = new CustomerDetails();
			cd.setCourseName(rs.getString(1));
			cd.setLocation(rs.getString(2));
			cd.setAmount(rs.getInt(3));
			cd.setPurchasedDate(rs.getString(4));
			//add the cd object into cdList (arraylist) 
			cdList.add(cd);
			
			/*System.out.println("Printing data from the java object");
			System.out.println("-----------");
			System.out.println(cd.getCourseName());
			System.out.println(cd.getLocation());
			System.out.println(cd.getAmount());
			System.out.println(cd.getPurchasedDate());
			System.out.println("-----------");*/
		}
		
		//create objects from each element of list
		for(int i = 0; i < cdList.size(); i++) {
			//convert java object into json file using jackson api
			ObjectMapper obj = new ObjectMapper();
			//args = jsonfile localtion , java object
			String varNomeArquivo = "customerInfo";
			obj.writeValue(new File("../SDET/" + varNomeArquivo + i + ".json"), cdList.get(i));
			System.out.println("Arquivo " + varNomeArquivo + i + ".json, gerado com sucesso.");
		}
		
	
		JSONObject JsObj = new JSONObject
		JsObj.put("data", " javaobject "")args;
		
		
		//send json file as input to the api automation test
		
		
		
		
		
	}

}
