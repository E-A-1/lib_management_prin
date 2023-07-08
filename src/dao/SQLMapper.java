package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import dbfw.ResultMapper;

import domain.Admin;
import domain.BookHireList;
import domain.BookDetails;
import domain.BookReqList;
import domain.Student;

public class SQLMapper {
	public static final String InsertAdmin = "insert into admin values(?,?,?,?,?)";
	public static final String InsertStudent = "insert into student values(?,?,?,?,?)";
	public static final String InsertBook = "insert into bookdetails values(?,?,?,?,?,?,?,?,?)";
	public static final String InsertBookReq = "insert into Bookreqlist(BookID,Req_date,userid) values(?,?,?)";
	public static final String InsertReqConformation = "insert into BookHireDetails(HireId,Bookid,bookName,ISBN,author,publisher,edition,price,quantity,returndate,userid) values(?,?,?,?,?,?,?,?,?,?,?)";
	public static final String UpdateBook = "update bookdetails set quantity=? where book_id=?";
	public static final String FetchAdminLogin = "select * from Admins where admin_id=? and password=?";
	public static final String FetchStudentDetail = "select * from student where student_id=? and password=?";
	public static final String SearchBookUsingBookName = "select * from bookdetails where book_name=? ";
	public static final String FetchBookReqDetails = "select * from bookreqlist where userid=?";
	public static final String RetriveBookReqDetails = "select * from bookhiredetails where bookid=?";
	public static final String FetchReqBook = "select * from bookslist where BookID=? and quantity>0";
	public static final String FetchAdmin = "select * from admin";
	public static final String GetAllStudents = "select * from student";
	public static final String FetchBookList = "select * from bookdetails";
	public static final String DeleteStudent = "delete from student where student_id=?";
	public static final String DeleteBook = "delete from bookdetails where book_id=?";
	public static final String RetriveBookHireDetails = "select * from bookhiredetails where userid=?";
	public static final String CancelHire = "Delete from BookHireDetails where hireid=? ";
	public static final String GetBookByBookId = "select * from bookdetails where book_id=?";

	public static final ResultMapper ADMIN_MAPPER = new ResultMapper() {

		public Object mapRow(ResultSet rs) throws SQLException {

			int adminId = rs.getInt(1);
			String adminName = rs.getString(2);
			String email = rs.getString(3);
			String password = rs.getString(4);
			String contactNumber = rs.getString(5);

			Admin admin = new Admin(adminId, adminName, email, password, contactNumber);

			return admin;
		}

		// mapRow

	};
	// Anonymous class
	public static final ResultMapper STUDENT_MAPPER = new ResultMapper() {

		public Object mapRow(ResultSet rs) throws SQLException {

			int studentId = rs.getInt(1);
			String studentName = rs.getString(2);
			String password = rs.getString(3);
			String email = rs.getString(4);
			String contactNumber = rs.getString(5).toString();

			Student s = new Student(studentId, studentName, password, email, contactNumber);

			return s;
		}

		// mapRow

	};
	// Anonymous class
	public static final ResultMapper BOOK_MAPPER = new ResultMapper() {

		public Object mapRow(ResultSet rs) throws SQLException {
			int bookId = rs.getInt(1);
			String bookName = rs.getString(2);
			int isbn = rs.getInt(3);
			String author = rs.getString(4);
			String publisher = rs.getString(5);
			String edition = rs.getString(6);
			int price = rs.getInt(7);
			int quantity = rs.getInt(8);
			String categoryName = rs.getString(9);
			BookDetails bookDetail = new BookDetails(bookId, bookName, isbn, author, publisher, edition, price,
					quantity, categoryName);
			return bookDetail;
		}

		// mapRow

	};
	// Anonymous class
	public static final ResultMapper BOOKREQLISTMAPPER = new ResultMapper() {

		public Object mapRow(ResultSet rs) throws SQLException {

			int book_id = rs.getInt(1);
			String date = rs.getString(2);
			int userid = rs.getInt(3);

			BookReqList brl = new BookReqList(book_id, date, userid);

			return brl;
		}

		// mapRow

	};
	public static final ResultMapper BOOKHIRELISTMAPPER = new ResultMapper() {

		public Object mapRow(ResultSet rs) throws SQLException {
			int hireid = rs.getInt(1);
			int bookid = rs.getInt(2);
			String name = rs.getString(3);
			int isbn = rs.getInt(4);
			String author = rs.getString(5);
			String publisher = rs.getString(6);
			String edition = rs.getString(7);
			double price = rs.getDouble(8);
			int quantity = rs.getInt(9);
			String returndate = rs.getString(10);
			int userid = rs.getInt(11);
			BookHireList bhl = new BookHireList(hireid, bookid, name, isbn, author, publisher, edition, price, quantity,
					returndate, userid);

			return bhl;
		}

		// mapRow

	};

}
