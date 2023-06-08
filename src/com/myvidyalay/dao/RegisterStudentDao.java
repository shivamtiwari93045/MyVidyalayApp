package com.myvidyalay.dao;

import java.sql.SQLException;
import java.util.List;

import com.myvidyalay.modal.Student;

public interface RegisterStudentDao {

	public int registerStudent(Student student) throws ClassNotFoundException, SQLException;

	public int login(Student std) throws ClassNotFoundException, SQLException;

	public List<Student> getAllStudent() throws ClassNotFoundException, SQLException;
}
