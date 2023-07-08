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
import domain.BookDetails;

public class BookListDAO {

	static Logger log = Logger.getLogger(BookListDAO.class);

	public static List<BookDetails> getBooks() throws DBException, DAOException, DBCException {
		List<BookDetails> books = null;
		ConnectionHolder connectionHolder = null;
		Connection connection = null;
		{
			try {
				connectionHolder = ConnectionHolder.getInstance();
				connection = connectionHolder.getConnection();
				log.debug("fetching"); //

				books = DBHelp.executeSelect(connection, SQLMapper.FetchBookList, SQLMapper.BOOK_MAPPER);

			} catch (DBCException e) {
				throw new DBCException("Unable to connect to db" + e);

			} finally {

				try {

					if (connection != null)
						connection.close();

				} catch (SQLException e) {
				}
			}

			return books;

		}
	}

	public static int createNewBook(BookDetails book) throws DAOException, DBException {
		int result = 0;
		ConnectionHolder connectionHolder = null;
		Connection connection = null;
		List<BookDetails> BookDetails = null;
		try {
			connectionHolder = ConnectionHolder.getInstance();
			connection = connectionHolder.getConnection();
			final ParamMapper INSERT_BOOK_MAPPER = new ParamMapper() // select id, name from user where id=? password=?
			{

				public void mapParam(PreparedStatement preStmt) throws SQLException {
					preStmt.setInt(1, book.getBookId());
					preStmt.setString(2, book.getBookName());
					preStmt.setInt(3, book.getIsbn());
					preStmt.setString(4, book.getAuthor());
					preStmt.setString(5, book.getPublisher());
					preStmt.setString(6, book.getEdition());
					preStmt.setInt(7, book.getPrice());
					preStmt.setInt(8, book.getQuantity());
					preStmt.setString(9, book.getCategoryName());

				}

			};

			result = DBHelp.executeUpdate(connection, SQLMapper.InsertBook, INSERT_BOOK_MAPPER);

		} catch (DBCException e) {

			e.printStackTrace();
		}

		return result;

	}

	public static List<BookDetails> searchBook(String name) {

		ConnectionHolder connectionHolder = null;
		Connection connection = null;
		List<BookDetails> book = null;

		try {
			connectionHolder = ConnectionHolder.getInstance();
			connection = connectionHolder.getConnection();

			final ParamMapper BOOK_PARAM_MAPPER = new ParamMapper() {

				public void mapParam(PreparedStatement preStmt) throws SQLException {
					preStmt.setString(1, name);

				}

			};

			book = DBHelp.executeSelect(connection, SQLMapper.SearchBookUsingBookName, SQLMapper.BOOK_MAPPER,
					BOOK_PARAM_MAPPER);

		} catch (DBCException e) {

			e.printStackTrace();
		}
		return book;

	}

	public static int insertrequest(int bookid, int userid) throws DAOException, DBException {
		int result = 0;
		ConnectionHolder connectionHolder = null;
		Connection connection = null;
		List admindetails = null;

		;
		try {
			connectionHolder = ConnectionHolder.getInstance();
			connection = connectionHolder.getConnection();
			final ParamMapper INSERTPBOOKREQ = new ParamMapper() // select id, name from user where id=? password=?
			{

				public void mapParam(PreparedStatement preStmt) throws SQLException {
					preStmt.setInt(1, bookid);

					System.out.println("Enter the date on requested in yy/mm/dd");
					Scanner sc = new Scanner(System.in);
					String date = sc.next();

					preStmt.setString(2, date);

					preStmt.setInt(3, userid);

				}

			};// ananymous class
			result = DBHelp.executeUpdate(connection, SQLMapper.InsertBookReq, INSERTPBOOKREQ);

		} catch (DBCException e) {

			e.printStackTrace();
		}

		return result;

	}

	public static List UserHireBooks(int userid) {
		ConnectionHolder connectionHolder = null;
		Connection connection = null;
		List BookReq = null;

		try {
			connectionHolder = ConnectionHolder.getInstance();
			connection = connectionHolder.getConnection();

			final ParamMapper BOOKREQPMAPPER = new ParamMapper() // select id, name from user where id=? password=?
			{

				public void mapParam(PreparedStatement preStmt) throws SQLException {
					preStmt.setInt(1, userid);

				}

			};// ananymous class

			BookReq = DBHelp.executeSelect(connection, SQLMapper.RetriveBookHireDetails, SQLMapper.BOOKHIRELISTMAPPER,
					BOOKREQPMAPPER);

		} catch (DBCException e) {

			e.printStackTrace();
		}
		return BookReq;

	}

	public static List UserReqBooks(int userid) {
		ConnectionHolder connectionHolder = null;
		Connection connection = null;
		List BookReq = null;

		try {
			connectionHolder = ConnectionHolder.getInstance();
			connection = connectionHolder.getConnection();

			final ParamMapper BOOKREQPMAPPER = new ParamMapper() // select id, name from user where id=? password=?
			{

				public void mapParam(PreparedStatement preStmt) throws SQLException {
					preStmt.setInt(1, userid);

				}

			};// ananymous class

			BookReq = DBHelp.executeSelect(connection, SQLMapper.FetchBookReqDetails, SQLMapper.BOOKREQLISTMAPPER,
					BOOKREQPMAPPER);

		} catch (DBCException e) {

			e.printStackTrace();
		}
		return BookReq;

	}

	public static List getReqBooks(int bookid) {
		ConnectionHolder connectionHolder = null;
		Connection connection = null;
		List BookReq = null;

		try {
			connectionHolder = ConnectionHolder.getInstance();
			connection = connectionHolder.getConnection();

			final ParamMapper BOOKREQPMAPPER = new ParamMapper() // select id, name from user where id=? password=?
			{

				public void mapParam(PreparedStatement preStmt) throws SQLException {
					preStmt.setInt(1, bookid);

				}

			};// ananymous class

			BookReq = DBHelp.executeSelect(connection, SQLMapper.RetriveBookReqDetails, SQLMapper.BOOKREQLISTMAPPER,
					BOOKREQPMAPPER);

		} catch (DBCException e) {

			e.printStackTrace();
		}
		return BookReq;

	}

	public static int insertconformation(int bookid, String Name, int ISBN, String Author, String Publisher,
			String Edition, double price, int quantity, int userid, String returndate, int hireid)
			throws DAOException, DBException {
		int result = 0;
		// int quantity=1;
		ConnectionHolder connectionHolder = null;
		Connection connection = null;
		List admindetails = null;

		;
		try {
			connectionHolder = ConnectionHolder.getInstance();
			connection = connectionHolder.getConnection();
			final ParamMapper INSERTCONFORMATIONREQ = new ParamMapper() // select id, name from user where id=?
																		// password=?
			{

				public void mapParam(PreparedStatement preStmt) throws SQLException {
					preStmt.setInt(1, bookid);
					preStmt.setString(2, Name);
					preStmt.setInt(3, ISBN);
					preStmt.setString(4, Author);
					preStmt.setString(5, Publisher);
					preStmt.setString(6, Edition);
					preStmt.setDouble(7, price);
					preStmt.setInt(8, quantity);

					Scanner sc = new Scanner(System.in);
					System.out.println("Enter the date that book to be return in  yy/mm/dd");
					String returndate = sc.next();
					preStmt.setString(9, returndate);
					preStmt.setInt(10, userid);
					preStmt.setInt(11, hireid);
				}

			};// ananymous class
			result = DBHelp.executeUpdate(connection, SQLMapper.InsertReqConformation, INSERTCONFORMATIONREQ);

		} catch (DBCException e) {

			e.printStackTrace();
		}

		return result;

	}

	public static List<BookDetails> getBookUsingBookId(int bookId) throws DBException, DAOException, DBCException {
		List<BookDetails> books = null;
		ConnectionHolder connectionHolder = null;
		Connection connection = null;
		{
			try {
				connectionHolder = ConnectionHolder.getInstance();
				connection = connectionHolder.getConnection();
				log.debug("fetching"); //
				final ParamMapper GET_BOOK_PARAM_MAPPER = new ParamMapper() // select id, name from user where id=?
																			// password=?
				{

					public void mapParam(PreparedStatement preStmt) throws SQLException {
						preStmt.setInt(1, bookId);
					}
				};

				books = DBHelp.executeSelect(connection, SQLMapper.GetBookByBookId, SQLMapper.BOOK_MAPPER,
						GET_BOOK_PARAM_MAPPER);

			} catch (DBCException e) {
				throw new DBCException("Unable to connect to db" + e);

			} finally {

				try {

					if (connection != null)
						connection.close();

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
