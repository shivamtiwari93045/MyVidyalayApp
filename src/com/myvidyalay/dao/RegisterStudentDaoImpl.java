package com.myvidayalay.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.myvidayalay.modal.Student;

public class RegisterStudentDaoImpl implements RegisterStudentDao {

	@Override
	public int registerStudent(Student student) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

		String url = "";
		String driver = "";
		String user = "";
		String password = "";
		
		try {
			
			Properties properties = new Properties();
						
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			InputStream fis = classLoader.getResourceAsStream("applicationDb.properties");
			
			properties.load(fis);
			
			driver = properties.getProperty("driver");
			url = properties.getProperty("url");
			user = properties.getProperty("user");
			password = properties.getProperty("password");
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String INSERT_STUDENT = "INSERT INTO STUDENT_TABLE VALUES(studid_seq.nextval,?,?,?,?,?,?,?,?)";
		// load driver class
		Class.forName(driver);

		// create connection
		Connection connection = DriverManager.getConnection(url, user, password);

		PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT);
		preparedStatement.setString(1, student.getFirstName());
		preparedStatement.setString(2, student.getLastName());
		preparedStatement.setString(3, student.getEmail());
		preparedStatement.setString(4, student.getPassword());
		preparedStatement.setString(5, student.getFirstName() + " " + student.getLastName());
		preparedStatement.setDate(6, new Date(1640097005));
		preparedStatement.setString(7, student.getFirstName() + " " + student.getLastName());
		preparedStatement.setDate(8, new Date(1640097005));

		int res = preparedStatement.executeUpdate();

		return res;
	}

	@Override
	public int login(Student std) throws ClassNotFoundException, SQLException {

		// load
		Class.forName("oracle.jdbc.driver.OracleDriver");

		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");

		String qry = "select * from student_table where email=? and password=?";

		PreparedStatement stmt = connection.prepareStatement(qry);
		stmt.setString(1, std.getEmail());
		stmt.setString(2, std.getPassword());

		ResultSet result = stmt.executeQuery();

		if (result.next())
			return 1;
		else
			return 0;
	}

	@Override
	public List<Student> getAllStudent() throws ClassNotFoundException, SQLException {
		// prepare query
		String qry = "select * from student_table";
		// load driver class
		Class.forName("oracle.jdbc.driver.OracleDriver");
		// create connection
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");

		PreparedStatement stmt = connection.prepareStatement(qry);
		ResultSet resultSet = stmt.executeQuery();
		List<Student> listStd = new ArrayList<Student>();
		
		while (resultSet.next()) {
			Student student = new Student();
			student.setStudentId(resultSet.getInt(1));
			student.setFirstName(resultSet.getString(2));
			student.setLastName(resultSet.getString(3));
			student.setEmail(resultSet.getString(4));
			student.setPassword(resultSet.getString(5));
			student.setCreatedBy(resultSet.getString(6));
			student.setCreatedDate(resultSet.getDate(7));
			listStd.add(student);
		}

		return listStd;
	}

}
