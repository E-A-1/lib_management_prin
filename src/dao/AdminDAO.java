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
	public static List getadmindetails(int userid, String password) {
		ConnectionHolder ch = null;
		Connection con = null;
		List admindetails = null;
		try {
			ch = ConnectionHolder.getInstance();
			con = ch.getConnection();
			final ParamMapper ADMINDETAILPMAPPER = new ParamMapper() // select id, name from user where id=? password=?
			{

				public void mapParam(PreparedStatement preStmt) throws SQLException {
					preStmt.setInt(1, userid);
					preStmt.setString(2, password);
				}

			};// ananymous class
			admindetails = DBHelp.executeSelect(con, SQLMapper.FetchAdminLogin, SQLMapper.ADMINDETAILMAPPER,
					ADMINDETAILPMAPPER);

		} catch (DBCException e) {

			e.printStackTrace();
		}
		return admindetails;

	}

	public static int adminInsert(Admin a) throws DAOException, DBException {
		int result = 0;
		ConnectionHolder ch = null;
		Connection con = null;
		List admindetails = null;
		try {
			ch = ConnectionHolder.getInstance();
			con = ch.getConnection();
			final ParamMapper INSERTPADMIN = new ParamMapper() // select id, name from user where id=? password=?
			{

				public void mapParam(PreparedStatement preStmt) throws SQLException {
					preStmt.setInt(1, a.getA_userid());
					preStmt.setInt(2, a.getA_rollid());
					preStmt.setString(3, a.getA_email());
					preStmt.setString(4, a.getA_mobile());
					preStmt.setString(5, a.getA_password());

				}

			};// ananymous class
			result = DBHelp.executeUpdate(con, SQLMapper.InsertAdmin, INSERTPADMIN);

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
