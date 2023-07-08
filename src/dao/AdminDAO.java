package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import dbfw.DBException;
import dbfw.DBException;

import dbc.ConnectionHolder;
import dbc.DBCException;
import dbfw.DBHelp;
import dbfw.ParamMapper;
import dbfw.ResultMapper;
import domain.Admin;

public class AdminDAO

{
	public static List<Admin> getAdminDetails(int adminId, String password) {
		ConnectionHolder connectionHolder = null;
		Connection connection = null;
		List<Admin> adminDetails = null;
		try {
			connectionHolder = ConnectionHolder.getInstance();
			connection = connectionHolder.getConnection();
			final ParamMapper READ_ADMIN_MAPPER = new ParamMapper() // select id, name from user where id=? password=?
			{

				public void mapParam(PreparedStatement preStmt) throws SQLException {
					preStmt.setInt(1, adminId);
					preStmt.setString(2, password);
				}

			};

			adminDetails = DBHelp.executeSelect(connection, SQLMapper.FetchAdminLogin, SQLMapper.ADMIN_MAPPER,
					READ_ADMIN_MAPPER);

		} catch (DBCException e) {

			e.printStackTrace();
		}
		return adminDetails;

	}

	public static int insertAdmin(Admin admin) throws DAOException, DBException {
		int result = 0;
		ConnectionHolder connectionHolder = null;
		Connection connection = null;
		try {
			connectionHolder = ConnectionHolder.getInstance();
			connection = connectionHolder.getConnection();
			final ParamMapper INSERT_ADMIN = new ParamMapper() // select id, name from user where id=? password=?
			{

				public void mapParam(PreparedStatement preStmt) throws SQLException {
					preStmt.setInt(1, admin.getAdminId());
					preStmt.setString(2, admin.getAdminName());
					preStmt.setString(3, admin.getEmail());
					preStmt.setString(4, admin.getPassword());
					preStmt.setString(5, admin.getContactNumber());

				}

			};

			result = DBHelp.executeUpdate(connection, SQLMapper.InsertAdmin, INSERT_ADMIN);

		} catch (DBCException e) {

			e.printStackTrace();
		}
		return result;
	}

	public static List requestbook(int BookID) {

		ConnectionHolder ch = null;
		Connection con = null;
		List reqbookdetails = null;
		try {
			ch = ConnectionHolder.getInstance();
			con = ch.getConnection();
			final ParamMapper REQBOOKPMAPPER = new ParamMapper() // select id, name from user where id=? password=?
			{

				public void mapParam(PreparedStatement preStmt) throws SQLException {
					preStmt.setInt(1, BookID);

				}

			};// ananymous class
			reqbookdetails = DBHelp.executeSelect(con, SQLMapper.FetchReqBook, SQLMapper.BOOKLISTMAPPER,
					REQBOOKPMAPPER);

		} catch (DBCException e) {

			e.printStackTrace();
		}
		return reqbookdetails;

	}

	public static int updateBookList(int id, int quantity) throws DAOException, DBException {
		int result = 0;
		ConnectionHolder ch = null;
		Connection con = null;

		try {
			ch = ConnectionHolder.getInstance();
			con = ch.getConnection();

			final ParamMapper UPDATEPABOOKLIST = new ParamMapper() // select id, name from user where id=? password=?
			{

				public void mapParam(PreparedStatement preStmt) throws SQLException {
					preStmt.setInt(1, quantity);
					preStmt.setInt(2, id);

				}

			};// ananymous class
			result = DBHelp.executeUpdate(con, SQLMapper.UpdateBookList, UPDATEPABOOKLIST);

		} catch (DBCException e) {

			e.printStackTrace();
		}
		return result;
	}

	public static int deletestudent(int id) throws DAOException, DBException {
		int result = 0;
		ConnectionHolder ch = null;
		Connection con = null;

		try {
			ch = ConnectionHolder.getInstance();
			con = ch.getConnection();

			final ParamMapper DELETEPSTUDENT = new ParamMapper() // select id, name from user where id=? password=?
			{

				public void mapParam(PreparedStatement preStmt) throws SQLException {

					preStmt.setInt(1, id);

				}

			};// ananymous class
			result = DBHelp.executeUpdate(con, SQLMapper.DeleteStudent, DELETEPSTUDENT);

		} catch (DBCException e) {

			e.printStackTrace();
		}
		return result;
	}

	public static int deletebook(int id) throws DAOException, DBException {
		int result = 0;
		ConnectionHolder ch = null;
		Connection con = null;

		try {
			ch = ConnectionHolder.getInstance();
			con = ch.getConnection();

			final ParamMapper DELETEPSTUDENT = new ParamMapper() // select id, name from user where id=? password=?
			{

				public void mapParam(PreparedStatement preStmt) throws SQLException {

					preStmt.setInt(1, id);

				}

			};// ananymous class
			result = DBHelp.executeUpdate(con, SQLMapper.DeleteBook, DELETEPSTUDENT);

		} catch (DBCException e) {

			e.printStackTrace();
		}
		return result;
	}

	public static int deletehiredbook(int hireid) throws DBException {
		int result = 0;
		ConnectionHolder ch = null;
		Connection con = null;

		try {
			ch = ConnectionHolder.getInstance();
			con = ch.getConnection();

			final ParamMapper CANCELPREQ = new ParamMapper() // select id, name from user where id=? password=?
			{

				public void mapParam(PreparedStatement preStmt) throws SQLException {

					preStmt.setInt(1, hireid);

				}

			};// ananymous class
			result = DBHelp.executeUpdate(con, SQLMapper.CancelHire, CANCELPREQ);

		} catch (DBCException e) {

			e.printStackTrace();
		}
		return result;

	}

}
