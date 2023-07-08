package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import dbc.ConnectionHolder;
import dbc.DBCException;
import dbfw.DBException;
import dbfw.DBHelp;
import dbfw.ParamMapper;
import domain.Student;

public class StudentDAO {

	public static List<Student> getStudentDetails(int studentId, String password) {
		ConnectionHolder connectionHolder = null;
		Connection connection = null;
		List<Student> studentDetails = null;
		try {
			connectionHolder = ConnectionHolder.getInstance();
			connection = connectionHolder.getConnection();
			final ParamMapper STUDENT_DETAIL_MAPPER = new ParamMapper() // select id, name from user where id=?
																		// password=?
			{

				public void mapParam(PreparedStatement preStmt) throws SQLException {
					preStmt.setInt(1, studentId);
					preStmt.setString(2, password);
				}

			};
			studentDetails = DBHelp.executeSelect(connection, SQLMapper.FetchStudentDetail,
					SQLMapper.STUDENT_MAPPER,
					STUDENT_DETAIL_MAPPER);

		} catch (DBCException e) {

			e.printStackTrace();
		}

		return studentDetails;

	}

	public static int createNewStudent(Student student) throws DBException {
		int result = 0;
		ConnectionHolder connectionHolder = null;
		Connection connection = null;
		try {
			connectionHolder = ConnectionHolder.getInstance();
			connection = connectionHolder.getConnection();
			final ParamMapper INSERT_NEW_STUDENT = new ParamMapper() // select id, name from user where id=? password=?
			{

				public void mapParam(PreparedStatement preStmt) throws SQLException {
					preStmt.setInt(1, student.getStudentId());
					preStmt.setString(2, student.getStudentName());
					preStmt.setString(3, student.getPassword());
					preStmt.setString(4, student.getEmail());
					preStmt.setString(5, student.getContactNumber());

				}

			};

			result = DBHelp.executeUpdate(connection, SQLMapper.InsertStudent, INSERT_NEW_STUDENT);

		} catch (DBCException e) {

			e.printStackTrace();
		}

		return result;

	}

}
