package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import dbc.ConnectionHolder;
import dbc.DBCException;
import dbfw.DBException;
import dbfw.DBHelp;
import dbfw.ParamMapper;
import domain.BookDetails;

public class BookListDAO {

	// To list all the books
	public static List<BookDetails> getBooks() throws DBException, DAOException, DBCException {
		List<BookDetails> books = null;
		ConnectionHolder connectionHolder = null;
		Connection connection = null;
		{
			try {
				connectionHolder = ConnectionHolder.getInstance();
				connection = connectionHolder.getConnection();

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

	// To create new book
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

	// To search book
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

	// To get the book using book id
	public static List<BookDetails> getBookUsingBookId(int bookId) throws DBException, DAOException, DBCException {
		List<BookDetails> books = null;
		ConnectionHolder connectionHolder = null;
		Connection connection = null;
		{
			try {
				connectionHolder = ConnectionHolder.getInstance();
				connection = connectionHolder.getConnection();
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

	// to update the book
	public static int updateBookList(int id, int quantity) throws DAOException, DBException {
		int result = 0;
		ConnectionHolder ch = null;
		Connection con = null;

		try {
			ch = ConnectionHolder.getInstance();
			con = ch.getConnection();

			final ParamMapper UPDATE_BOOK_LIST_PARAM_MAPPER = new ParamMapper() // select id, name from user where id=?
			// password=?
			{

				public void mapParam(PreparedStatement preStmt) throws SQLException {
					preStmt.setInt(1, quantity);
					preStmt.setInt(2, id);

				}

			};

			result = DBHelp.executeUpdate(con, SQLMapper.UpdateBook, UPDATE_BOOK_LIST_PARAM_MAPPER);

		} catch (DBCException e) {

			e.printStackTrace();
		}
		return result;
	}

	public static int deleteBook(int bookId) throws DAOException, DBException {
		int result = 0;
		ConnectionHolder ch = null;
		Connection con = null;

		try {
			ch = ConnectionHolder.getInstance();
			con = ch.getConnection();

			final ParamMapper DELETE_BOOK_PARAM_MAPPER = new ParamMapper() // select id, name from user where id=?
			// password=?
			{

				public void mapParam(PreparedStatement preStmt) throws SQLException {
					preStmt.setInt(1, bookId);

				}

			};

			result = DBHelp.executeUpdate(con, SQLMapper.DeleteBook, DELETE_BOOK_PARAM_MAPPER);

		} catch (DBCException e) {

			e.printStackTrace();
		}
		return result;
	}

}
