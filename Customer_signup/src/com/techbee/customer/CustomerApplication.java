package com.techbee.customer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/CustomerApplication")
public class CustomerApplication extends HttpServlet {
	@Override
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 	String ValidateCustomerFirstName = request.getParameter("first_name");
	 	String ValidateCustomerLastName = request.getParameter("last_name");
	 	String ValidateCustomerID = request.getParameter("customer_id");
	 	String ValidateCustomerEmail = request.getParameter("EMail");
	 	String ValidateCustomerNumber = request.getParameter("customer_number");
	 	String ValidateCustomerAddress = request.getParameter("Address");
	 	String ValidateCustomerState = request.getParameter("State");
	 	String ValidateCustomerPincode = request.getParameter("Pincode");
	 	String ValidateCustomerPassword = request.getParameter("user_password");
	 	try {
	 		Class.forName("oracle.jdbc.driver.OracleDriver");
	 		System.out.println("Driver loaded successfully!");
	 		//Get the connection
	 		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");  
	 		System.out.println("Connection Established!");
	 		Statement statement = connection.createStatement();
	 		
	 		int flag=0;
	 		ResultSet resultSet = statement.executeQuery("select * from CUSTOMER");
	 		while(resultSet.next()) 
	 		{
				String first_name = resultSet.getString("FIRST_NAME");
				String last_name = resultSet.getString("LAST_NAME");
				String customer_id = resultSet.getString("CUSTOMER_ID");
				String email_address = resultSet.getString("EMAIL_ADDRESS");
				String contact_number = resultSet.getString("CONTACT_NUMBER");
				String address = resultSet.getString("ADDRESS");
				String state = resultSet.getString("STATE");
				String pincode = resultSet.getString("PINCODE");
				String UserPassword = resultSet.getString("USER_PASSWORD");
				
				if(ValidateCustomerFirstName.equals(first_name))
				{
				
					if(ValidateCustomerLastName.equals(last_name))
					{
						if(ValidateCustomerID.equals(customer_id)) {
							if(ValidateCustomerEmail.equals(email_address)){
								if(ValidateCustomerNumber.equals(contact_number)) {
									if(ValidateCustomerAddress.equals(address)) {
										if(ValidateCustomerState.equals(state)) {
											if(ValidateCustomerPincode.equals(pincode)) {
												if(ValidateCustomerPassword.equals(UserPassword)) {
													flag=1;
													break;
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
	 		
	 		if(flag==1) {
	 			
	 			RequestDispatcher requestDispatcher = request.getRequestDispatcher("registrationform.html");
	 			requestDispatcher.forward(request, response);
	 		}
	 		else {
	 			
	 			RequestDispatcher requestDispatcher = request.getRequestDispatcher("error.html");
	 			requestDispatcher.forward(request, response);
	 		}
	 	} catch (Exception e) {
	 		System.out.println(e);
	 		RequestDispatcher requestDispatcher = request.getRequestDispatcher("error.html");
	 		requestDispatcher.forward(request, response);
	 	}	
	 
	   
}

}
