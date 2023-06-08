package com.myvidayalay.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;



@WebServlet(name = "UploadServlet", urlPatterns = { "/upload.php" })
@MultipartConfig(
  fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
  maxFileSize = 1024 * 1024 * 10,      // 10 MB
  maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class UploadServlet_Bckup extends HttpServlet{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}
	
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter printWriter = resp.getWriter();
		HttpSession session = req.getSession();
		String userName = (String) session.getAttribute("userEmail");
		userName = userName.replaceAll("@gmail.com", "");
		
		System.out.println("userName:::::::::::"+userName);
		/* Receive file uploaded to the Servlet from the HTML5 form */
	    Part filePart = req.getPart("fileToUpload");
	    String fileName = filePart.getSubmittedFileName();
	    for (Part part : req.getParts()) {
	      //part.write("F:\\upload\\" +userName+"_"+Instant.now().getEpochSecond()+""+fileName);
	      
	      part.write("F:\\upload\\"+fileName);
	    }
	    
		printWriter.println("Your file successfully uploaded");
	}
	
}
