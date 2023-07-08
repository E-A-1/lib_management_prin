package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import dbc.ConnectionHolder;
import dbc.DBCException;
import dbfw.DBException;
import dbfw.DBHelp;
import dbfw.ParamMapper;
import domain.BookList;


public class BookListDAO
{
	
static Logger log=Logger.getLogger(BookListDAO.class);

public static List getBooks() throws DBException, DAOException, DBCException
{
	List books=null;
	ConnectionHolder ch=null;
	Connection con=null;
	{
	try {
		ch=ConnectionHolder.getInstance();
		con=ch.getConnection();
		log.debug("fetching"); //
		
		books=DBHelp.executeSelect(con,SQLMapper.FetchBookList,SQLMapper.BOOKLISTMAPPER);
				
	} catch (DBCException e) {
		throw new DBCException("Unable to connect to db"+e); 
	
	}
	finally {

		try {

			if (con != null)
				con.close();

		} catch (SQLException e) {
		}
	}
	
	
	return books;
	
	}
}
public static int insertbooks(BookList bl) throws DAOException, DBException 
{
	int result=0;
	ConnectionHolder ch=null;
	Connection con=null;
	List BookDetails=null;
	try
	{
		ch=ConnectionHolder.getInstance();
		con=ch.getConnection();
		 final ParamMapper INSERTPBOOK=new ParamMapper()  // select id, name  from user where id=? password=? 
				 {

					public void mapParam(PreparedStatement preStmt) throws SQLException {
					preStmt.setInt(1,bl.getB_id());
					preStmt.setString(2, bl.getB_name());
					preStmt.setInt(3,bl.getB_isbn());
					preStmt.setString(4, bl.getB_author());
					preStmt.setString(5, bl.getB_publisher());
					preStmt.setString(6,bl.getB_edition());
					preStmt.setDouble(7,bl.getPrice());
					preStmt.setInt(8,bl.getQuantity());
					preStmt.setInt(9,bl.getCategoryid());
											
					}
					
				};//ananymous class
				result=DBHelp.executeUpdate(con,SQLMapper.InsertBook,INSERTPBOOK);	
				
	} catch (DBCException e) {
		
		e.printStackTrace();
	}
	
				
	return result;
	
	
}
public static List getBook(final String name) // 22 INDIA// 
{
	
	ConnectionHolder ch=null;
	Connection con=null;
	List Book=null;
	
	try {
			ch=ConnectionHolder.getInstance();
			con=ch.getConnection();
			
		 final ParamMapper BOOKPMAPPER=new ParamMapper()  
		 {

			public void mapParam(PreparedStatement preStmt) throws SQLException {
			preStmt.setString(1,name);
									
			}
			
		};//ananymous class
		
	Book=DBHelp.executeSelect(con,SQLMapper.FetchBookDetails,SQLMapper.BOOKLISTMAPPER, BOOKPMAPPER );		

	}
	catch (DBCException e)
	{
		
		e.printStackTrace();
	}
	return Book;
	
}
public static int insertrequest(int bookid, int userid) throws DAOException, DBException 
{
	int result=0;
	ConnectionHolder ch=null;
	Connection con=null;
	List admindetails=null;
	
;
	try
	{
		ch=ConnectionHolder.getInstance();
		con=ch.getConnection();
		 final ParamMapper INSERTPBOOKREQ=new ParamMapper()  // select id, name  from user where id=? password=? 
				 {

					public void mapParam(PreparedStatement preStmt) throws SQLException {
					preStmt.setInt(1,bookid);
					
					System.out.println("Enter the date on requested in yy/mm/dd");
				Scanner sc=new Scanner(System.in);
					String date=sc.next();
					
					preStmt.setString(2, date);
					
					preStmt.setInt(3,userid);
					
					
					
											
					}
					
				};//ananymous class
				result=DBHelp.executeUpdate(con,SQLMapper.InsertBookReq, INSERTPBOOKREQ );	
				
	} catch (DBCException e) {
		
		e.printStackTrace();
	}
	
				
	return result;
	
	
}
public static List UserHireBooks(int userid) 
{
	ConnectionHolder ch=null;
	Connection con=null;
	List BookReq=null;
	
	try {
			ch=ConnectionHolder.getInstance();
			con=ch.getConnection();
			
		 final ParamMapper BOOKREQPMAPPER=new ParamMapper()  // select id, name  from user where id=? password=? 
		 {

			public void mapParam(PreparedStatement preStmt) throws SQLException {
				preStmt.setInt(1,userid);
									
			}
			
		};//ananymous class
		
	BookReq=DBHelp.executeSelect(con,SQLMapper.RetriveBookHireDetails,SQLMapper.BOOKHIRELISTMAPPER, BOOKREQPMAPPER );		

	}
	catch (DBCException e)
	{
		
		e.printStackTrace();
	}
	return BookReq;
	
	
	
}
public static List UserReqBooks(int userid) 
{
	ConnectionHolder ch=null;
	Connection con=null;
	List BookReq=null;
	
	try {
			ch=ConnectionHolder.getInstance();
			con=ch.getConnection();
			
		 final ParamMapper BOOKREQPMAPPER=new ParamMapper()  // select id, name  from user where id=? password=? 
		 {

			public void mapParam(PreparedStatement preStmt) throws SQLException {
			preStmt.setInt(1,userid);
									
			}
			
		};//ananymous class
		
	BookReq=DBHelp.executeSelect(con,SQLMapper.FetchBookReqDetails,SQLMapper.BOOKREQLISTMAPPER, BOOKREQPMAPPER );		

	}
	catch (DBCException e)
	{
		
		e.printStackTrace();
	}
	return BookReq;
	
	
	
}
public static List getReqBooks(int bookid) 
{
	ConnectionHolder ch=null;
	Connection con=null;
	List BookReq=null;
	
	try {
			ch=ConnectionHolder.getInstance();
			con=ch.getConnection();
			
		 final ParamMapper BOOKREQPMAPPER=new ParamMapper()  // select id, name  from user where id=? password=? 
		 {

			public void mapParam(PreparedStatement preStmt) throws SQLException {
			preStmt.setInt(1,bookid);
									
			}
			
		};//ananymous class
		
	BookReq=DBHelp.executeSelect(con,SQLMapper.RetriveBookReqDetails,SQLMapper.BOOKREQLISTMAPPER, BOOKREQPMAPPER );		

	}
	catch (DBCException e)
	{
		
		e.printStackTrace();
	}
	return BookReq;
	
}
public static int insertconformation(int bookid,String Name,int ISBN,String Author,String Publisher,String Edition, double price,int quantity,int userid,String returndate, int hireid) throws DAOException, DBException 
{
	int result=0;
	//int quantity=1;
	ConnectionHolder ch=null;
	Connection con=null;
	List admindetails=null;
	
;
	try
	{
		ch=ConnectionHolder.getInstance();
		con=ch.getConnection();
		 final ParamMapper INSERTCONFORMATIONREQ=new ParamMapper()  // select id, name  from user where id=? password=? 
				 {

					public void mapParam(PreparedStatement preStmt) throws SQLException {
					preStmt.setInt(1,bookid);
					preStmt.setString(2,Name);
					preStmt.setInt(3,ISBN);
					preStmt.setString(4,Author);
					preStmt.setString(5,Publisher);
					preStmt.setString(6,Edition);
					preStmt.setDouble(7,price);
					preStmt.setInt(8,quantity);
					
					
					Scanner sc=new Scanner(System.in);
					System.out.println("Enter the date that book to be return in  yy/mm/dd");
					String returndate=sc.next();
					preStmt.setString(9,returndate);		
					preStmt.setInt(10,userid);	
					preStmt.setInt(11, hireid);
					}
					
				};//ananymous class
				result=DBHelp.executeUpdate(con,SQLMapper.InsertReqConformation, INSERTCONFORMATIONREQ );	
				
	} catch (DBCException e) {
		
		e.printStackTrace();
	}
	
				
	return result;
	
	
}
public static List Books(int Bookid) throws DBException, DAOException, DBCException
{
	List books=null;
	ConnectionHolder ch=null;
	Connection con=null;
	{
	try {
		ch=ConnectionHolder.getInstance();
		con=ch.getConnection();
		log.debug("fetching"); //
		final ParamMapper INSERTCONFORMATIONREQ=new ParamMapper()  // select id, name  from user where id=? password=? 
				 {

					public void mapParam(PreparedStatement preStmt) throws SQLException
					{
						preStmt.setInt(1,Bookid);
					}
				 };
					
		
		books=DBHelp.executeSelect(con,SQLMapper.RetriveBookList,SQLMapper.BOOKLISTMAPPER,INSERTCONFORMATIONREQ);
				 
				
	} catch (DBCException e) {
		throw new DBCException("Unable to connect to db"+e); 
	
	}
	finally {

		try {

			if (con != null)
				con.close();

		} catch (SQLException e) {
		}
	}
	
	
	return books;
	
	}
}
public static void insertconformation(int bookid, String name, int iSBN, String author, String publisher,
		String edition, Double price, int quantity, String returndate, int hireid, int userid) {
	
	
}

}



