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

public class StudentDAO{

	public static List getstudentdetails( int userid, String password)
	{
		ConnectionHolder ch=null;
		Connection con=null;
		List studentdetails=null;
		try
		{
			ch=ConnectionHolder.getInstance();
			con=ch.getConnection();
			 final ParamMapper STUDENTDETAILPMAPPER=new ParamMapper()  // select id, name  from user where id=? password=? 
					 {

						public void mapParam(PreparedStatement preStmt) throws SQLException {
						preStmt.setInt(1,userid);
						preStmt.setString(2,password);						
						}
						 
					};//ananymous class
				studentdetails=DBHelp.executeSelect(con,SQLMapper.FetchStudentLogin,SQLMapper.STUDENTDETAILMAPPER, STUDENTDETAILPMAPPER );	
					
		} catch (DBCException e) {
			
			e.printStackTrace();
		}
		return 	studentdetails;
					
		}
	public static int studentInsert(Student s) throws DBException 
	{
		int result=0;
		ConnectionHolder ch=null;
		Connection con=null;
		List studentdetails=null;
		try
		{
			ch=ConnectionHolder.getInstance();
			con=ch.getConnection();
			 final ParamMapper INSERTPSTUDENT=new ParamMapper()  // select id, name  from user where id=? password=? 
					 {

						public void mapParam(PreparedStatement preStmt) throws SQLException {
						preStmt.setInt(1,s.getS_userid());
						preStmt.setInt(2,s.getS_rollid());
						preStmt.setString(3, s.getS_email());
						preStmt.setString(4,s.getS_mobile());
						preStmt.setString(5, s.getS_password().toString());
												
						}
						
					};//ananymous class
					result=DBHelp.executeUpdate(con,SQLMapper.InsertStudent,INSERTPSTUDENT );	
					
		} catch (DBCException e) {
			
			e.printStackTrace();
		}
		
					
		return result;
		
		
	}
		

}
