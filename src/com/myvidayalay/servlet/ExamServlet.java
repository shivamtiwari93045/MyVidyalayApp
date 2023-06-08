package com.myvidayalay.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExamServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("aaa");
		String button = req.getParameter("examBtn");
		String assignment = req.getParameter("assignment");
		System.out.println(assignment);
		System.out.println(button);
		System.out.println("bbb");
		System.out.println("ccc");
		if (null != button && button.equalsIgnoreCase("takeTest"))
			resp.sendRedirect("ExamDashboard.jsp");
		else if(null != assignment && assignment.equalsIgnoreCase("assignmentSubmission"))
			resp.sendRedirect("assignment.jsp");
		else
			resp.sendRedirect("studentDashboard.jsp");

	}

}
