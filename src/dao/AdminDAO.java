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

	// for admin login

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

	// To create a admin

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

}
