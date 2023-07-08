package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import dbfw.ResultMapper;


import domain.Admin;
import domain.BookHireList;
import domain.BookList;
import domain.BookReqList;
import domain.Student;

public class SQLMapper 
{
	public static final String InsertAdmin="insert into Admin1 values(?,?,?,?,?)";
	public static final String InsertStudent="insert into student values(?,?,?,?,?)";
	public static final String InsertBook="insert into BooksList values(?,?,?,?,?,?,?,?,?)";
	public static final String InsertBookReq="insert into Bookreqlist(BookID,Req_date,userid) values(?,?,?)";
	public static final String InsertReqConformation="insert into BookHireDetails(HireId,Bookid,bookName,ISBN,author,publisher,edition,price,quantity,returndate,userid) values(?,?,?,?,?,?,?,?,?,?,?)";
	public static final String UpdateBookList="update BooksList set Quantity=? where BookID=?";	
	public static final String FetchAdminLogin="select * from admin1 where userid=? and password=?";
	public static final String FetchStudentLogin="select * from student where userid=? and password=?";
	public static final String FetchBookDetails="select * from bookslist where bookName=? ";
	public static final String FetchBookReqDetails="select * from bookreqlist where userid=?";	
	public static final String RetriveBookReqDetails="select * from bookhiredetails where bookid=?";	
	public static final String FetchReqBook="select * from bookslist where BookID=? and quantity>0";
	public static final String FetchAdmin="select * from admin1";
	public static final String FetchStudent="select * from student";
	public static final String FetchBookList="select * from bookslist";
	public static final String DeleteStudent="Delete from student where userid=?";
	public static final String DeleteBook="Delete from BooksList where Bookid=?";
	public static final String RetriveBookHireDetails="select * from bookhiredetails where userid=?";	
	public static final String CancelHire="Delete from BookHireDetails where hireid=? ";
	public static final String RetriveBookList="select * from bookslist where bookid=?";
		
	public static final ResultMapper ADMINDETAILMAPPER = new ResultMapper()
	{
		
		
		public Object mapRow(ResultSet rs) throws SQLException 
        {
			
		int userid=	rs.getInt(1);
		int rollid=	rs.getInt(2);
		String email=rs.getString(3);
		String mobile=	rs.getString(4);
		String password=rs.getString(5).toString();
		
		Admin a=new Admin(userid,rollid,email,mobile,password);  
		
		
			return a;
		}
		
		//mapRow
		
	};
	//Anonymous class
	public static final ResultMapper STUDENTDETAILMAPPER = new ResultMapper()
	{
		
		
		public Object mapRow(ResultSet rs) throws SQLException
        {
			
		int userid=	rs.getInt(1);
		int rollid=	rs.getInt(2);
		String email=rs.getString(3);
		String mobile=	rs.getString(4);
		String password=rs.getString(5).toString();
		
		Student s=new Student(userid,rollid,email,mobile,password);  
		
		
			return s;
		}
		
		//mapRow
		
	};
	//Anonymous class
	public static final ResultMapper BOOKLISTMAPPER = new ResultMapper()
	{
		
		
		public Object mapRow(ResultSet rs) throws SQLException
        {
			int b_id=rs.getInt(1);
			String b_name=rs.getString(2);
			int b_isbn=rs.getInt(3);
			String b_author=rs.getString(4);
			String b_publisher=rs.getString(5);
			String b_edition=rs.getString(6);
			double price=rs.getDouble(7);
			int quantity=rs.getInt(8);
			int categoryid=rs.getInt(9);
		BookList bl=new BookList(b_id,b_name,b_isbn,b_author,b_publisher,b_edition,price,quantity,categoryid); 
		
		
			return bl;
		}
		
		//mapRow
		
	};
	//Anonymous class
	public static final ResultMapper BOOKREQLISTMAPPER = new ResultMapper()
	{
		
		
		public Object mapRow(ResultSet rs) throws SQLException
        {
			
			int book_id=rs.getInt(1);
			String date=rs.getString(2);
			int userid=rs.getInt(3);
		
		BookReqList brl=new BookReqList(book_id,date,userid); 
		
		
			return brl;
		}
		
		//mapRow
		
	};
	public static final ResultMapper BOOKHIRELISTMAPPER = new ResultMapper()
	{
		
		
		public Object mapRow(ResultSet rs) throws SQLException
        {
			int hireid=rs.getInt(1);
			int bookid=rs.getInt(2);
			String name=rs.getString(3);
			int isbn=rs.getInt(4);
			String author=rs.getString(5);
			String publisher=rs.getString(6);
			String edition=rs.getString(7);
			double price=rs.getDouble(8);
			int quantity=rs.getInt(9);
			String returndate=rs.getString(10);
			int userid=rs.getInt(11);
		BookHireList bhl=new BookHireList(hireid,bookid,name,isbn,author,publisher,edition,price,quantity,returndate,userid); 
		
		
			return bhl;
		}
		
		//mapRow
		
	};

}
