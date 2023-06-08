package com.myvidayalay.servlet;

import java.io.File;
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
import java.util.*;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;



@WebServlet(name = "UploadServlet", urlPatterns = { "/upload.php" })
@MultipartConfig(
  fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
  maxFileSize = 1024 * 1024 * 10,      // 10 MB
  maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class UploadServlet extends HttpServlet{

	
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
		
		   boolean isMultipart;
		   String filePath;
		   int maxFileSize = 50 * 1024;
		   int maxMemSize = 4 * 1024;
		   File file ;
		
		   isMultipart = ServletFileUpload.isMultipartContent(req);
		   filePath = getServletContext().getInitParameter("fileToUpload"); 
		PrintWriter printWriter = resp.getWriter();
		HttpSession session = req.getSession();
		
		
		
		String userName = (String) session.getAttribute("userEmail");
		userName = userName.replaceAll("@gmail.com", "");
		
		System.out.println("userName:::::::::::"+userName);
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		   
	      // maximum size that will be stored in memory
	      factory.setSizeThreshold(maxMemSize);
	   
	      // Location to save data that is larger than maxMemSize.
	      factory.setRepository(new File("F:\\upload"));

	      // Create a new file upload handler
	      ServletFileUpload upload = new ServletFileUpload(factory);
	   
	      // maximum file size to be uploaded.
	      upload.setSizeMax( maxFileSize );
		
	      try { 
	          // Parse the request to get file items.
	          List fileItems = upload.parseRequest(req);
	 	
	          // Process the uploaded file items
	          Iterator i = fileItems.iterator();

	          
	    
	          while ( i.hasNext () ) {
	             FileItem fi = (FileItem)i.next();
	             if ( !fi.isFormField () ) {
	                // Get the uploaded file parameters
	                String fieldName = fi.getFieldName();
	                String fileName = fi.getName();
	                String contentType = fi.getContentType();
	                boolean isInMemory = fi.isInMemory();
	                long sizeInBytes = fi.getSize();
	             // Write the file
	                if( fileName.lastIndexOf("\\") >= 0 ) {
	                   file = new File( "F:\\upload" + fileName.substring( fileName.lastIndexOf("\\"))) ;
	                } else {
	                   file = new File( "F:\\upload" + fileName.substring(fileName.lastIndexOf("\\")+1)) ;
	                }
	                fi.write( file ) ;
	                printWriter.println("Your file successfully uploaded");
	             }
	          }
	          
	          } catch(Exception ex) {
	             System.out.println(ex);
	          }
		
		
		
		
	}
		
		
	
	
}
