package com.myvidyalay.service;

import java.sql.SQLException;
import java.util.List;

import com.myvidyalay.dao.RegisterStudentDao;
import com.myvidyalay.dao.RegisterStudentDaoImpl;
import com.myvidyalay.modal.Student;

public class RegisterStudentServiceImpl implements RegisterStudentService {

	RegisterStudentDao registerStudentDao = null;

	@Override
	public String registerStudent(Student std) throws ClassNotFoundException, SQLException {

		registerStudentDao = new RegisterStudentDaoImpl();
		int res = registerStudentDao.registerStudent(std);

		if (res == 0)
			return "fail";
		else
			return "success";
	}

	@Override
	public String loginStudent(Student stud) throws ClassNotFoundException, SQLException {
		RegisterStudentDao dao = new RegisterStudentDaoImpl();

		int result = dao.login(stud);

		if (result == 0)
			return "Login fail";
		else
			return "Login success";

	}

	@Override
	public List<Student> getAllStudent() throws ClassNotFoundException, SQLException {
		RegisterStudentDao studentDao = new RegisterStudentDaoImpl();
		
		return studentDao.getAllStudent();
	}

}
