package com.logIn.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myvidyalay.dao.RegisterStudentDaoImpl;
import com.myvidyalay.modal.Student;
import com.myvidyalay.service.PropertyConfig;
import com.myvidyalay.service.RegisterStudentService;
import com.myvidyalay.service.RegisterStudentServiceImpl;

public class LoginServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// general setting

		PrintWriter writer = resp.getWriter();
		String userName, password,loginType;
        
		// set content

		resp.setContentType("text/html");
		HttpSession httpSession = req.getSession();
		// read data

		userName = req.getParameter("eml");
		password = req.getParameter("psw");
        loginType = req.getParameter("loginType");
        
		Student stud = new Student();

		stud.setEmail(userName);
		stud.setPassword(password);
		httpSession.setAttribute("userEmail", userName);

		RegisterStudentService service = new RegisterStudentServiceImpl();

		try {
			
			String result = service.loginStudent(stud);
			//writer.println(result);
			PropertyConfig config = new PropertyConfig();
			if(result.equalsIgnoreCase("Login fail")){
				writer.println(config.getKeyValue("Email_Incorect"));
				writer.println("<a href='login.html'>Back</a>");	
			}
			
			else if(loginType.equalsIgnoreCase("Admin")){
				
				List<Student> listStudent = service.getAllStudent();
				System.out.println(listStudent.toString());
				httpSession.setAttribute("list", listStudent);
				//req.setAttribute("listStudent", listStudent);
				resp.sendRedirect(config.getKeyValue("Admin_Dashboard_Url"));
			}
			
			else if(loginType.equalsIgnoreCase("Student")){
				
				resp.sendRedirect(config.getKeyValue("Student_Dashboard_Url"));
			}
			else if(loginType.equalsIgnoreCase("Teacher")){
				resp.sendRedirect(config.getKeyValue("Faculty_Dashboard_Url"));
			}
			
			else{
			   resp.sendRedirect(config.getKeyValue("Main_Dashboard_Url"));
			}
		} catch (ClassNotFoundException e) {
			   
			writer.println(e.getMessage());
			
		} catch (SQLException e) {
			if(e.getMessage().contains("IO Error")){
				//IO Error: The Network Adapter could not establish the connection
				writer.println("<b>Database is not working of an application kindly contact with Admin..</b>");
				writer.print("<a href='login.html'>Back</a>");
			
			}
			
		}

	}

}
