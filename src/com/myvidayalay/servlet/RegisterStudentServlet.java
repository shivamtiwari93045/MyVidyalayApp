package com.myvidayalay.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myvidyalay.modal.Student;
import com.myvidyalay.service.RegisterStudentService;
import com.myvidyalay.service.RegisterStudentServiceImpl;

public class RegisterStudentServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		PrintWriter writer = resp.getWriter();
		String firstName, lastName, email, password;
		RegisterStudentService registerStudentService = null;
		String result = null;
		resp.setContentType("text/html");
  
		firstName = req.getParameter("fName");
		lastName = req.getParameter("lName");
		email = req.getParameter("eml");
		password = req.getParameter("psw");

		Student student = new Student();

		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setEmail(email);
		student.setPassword(password);

		registerStudentService = new RegisterStudentServiceImpl();

		try {

			result = registerStudentService.registerStudent(student);
			resp.sendRedirect("http://localhost:9191/MyVidyalay/login.html");

		} catch (ClassNotFoundException e) {
			writer.println(e.getMessage());
		} catch (SQLException e) {

			if (e.getMessage().contains("ORA-00001: unique constraint")) {
				writer.println("<b>Email Id is already exist. Please register with another email</b>");
				writer.print("<a href='register.html'>Back</a>");
			}
			else if(e.getMessage().contains("IO Error")){
				//IO Error: The Network Adapter could not establish the connection
				writer.println("<b>Database is not working of an application kindly contact with Admin..</b>");
				writer.print("<a href='register.html'>Back</a>");
			
			}
			else {
				writer.println(e.getMessage());
			}
		}

		// writer.println(result);

	}

}
