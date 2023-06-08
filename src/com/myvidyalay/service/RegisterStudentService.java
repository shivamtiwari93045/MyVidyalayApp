package com.myvidyalay.service;

import java.sql.SQLException;
import java.util.List;

import com.myvidyalay.modal.Student;

public interface RegisterStudentService {

	public String registerStudent(Student std) throws ClassNotFoundException, SQLException;

	public String loginStudent(Student stud) throws ClassNotFoundException, SQLException;
	
	public List<Student> getAllStudent() throws ClassNotFoundException, SQLException;
}
