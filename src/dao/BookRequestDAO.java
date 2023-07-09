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
import domain.BookDetails;
import domain.BookRequest;

public class BookRequestDAO {
    // to create a book request
    public static void addNewBookRequest(BookRequest bookRequest) {
        ConnectionHolder connectionHolder = null;
        Connection connection = null;
        int result = 0;
        try {
            connectionHolder = ConnectionHolder.getInstance();
            connection = connectionHolder.getConnection();

            ParamMapper insertBookRequestParamMapper = new ParamMapper() {
                @Override
                public void mapParam(PreparedStatement preStmt) throws SQLException {
                    preStmt.setInt(1, bookRequest.getRequestId());
                    preStmt.setInt(2, bookRequest.getStudentId());
                    preStmt.setInt(3, bookRequest.getBookId());
                    preStmt.setString(4, bookRequest.getStatus());

                }
            };

            DBHelp.executeUpdate(connection, SQLMapper.INSERT_BOOK_REQUEST, insertBookRequestParamMapper);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // delete the book request
    public static void deleteBookRequest(int requestId) {

        Connection connection = null;
        ConnectionHolder connectionHolder = null;

        try {

            connectionHolder = ConnectionHolder.getInstance();

            connection = connectionHolder.getConnection();

            // Mapping the input parameter to the sql using method in the below interface

            ParamMapper paramMapper = new ParamMapper() {
                @Override
                public void mapParam(PreparedStatement preparedStatement) throws SQLException {
                    preparedStatement.setInt(1, requestId);

                }
            };

            DBHelp.executeUpdate(connection, SQLMapper.DELETE_BOOK_REQUEST, paramMapper);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // To be confirmed by the admin
    public static void updateStatus(String status, int requestId) {
        Connection connection = null;
        ConnectionHolder connectionHolder = null;
        try {

            connectionHolder = ConnectionHolder.getInstance();

            connection = connectionHolder.getConnection();

            ParamMapper partialUpdateInputParam = new ParamMapper() {
                @Override
                public void mapParam(PreparedStatement preStmt) throws SQLException {
                    preStmt.setString(1, status);
                    preStmt.setInt(2, requestId);

                }
            };

            DBHelp.executeUpdate(connection, SQLMapper.UPDATE_BOOK_REQUEST, partialUpdateInputParam);
            System.out.println("------- The book request is confirmed by admin -----------");
        } catch (Exception exception) {
            exception.printStackTrace();

        }
    }

    // to list request made by the student based on the status

    public static List<BookRequest> getBooksRequestByUser(int userid, String status) {
        ConnectionHolder connectionHolder = null;
        Connection connection = null;
        List<BookRequest> reqBooks = null;

        try {
            connectionHolder = ConnectionHolder.getInstance();
            connection = connectionHolder.getConnection();

            final ParamMapper BOOK_REQ_PARAM_MAPPER = new ParamMapper() {

                public void mapParam(PreparedStatement preStmt) throws SQLException {
                    preStmt.setInt(1, userid);
                    preStmt.setString(2, status);

                }

            };
            reqBooks = DBHelp.executeSelect(connection, SQLMapper.FetchBookReqDetailsUsingUserId,
                    SQLMapper.BOOK_REQUEST_LIST_MAPPER,
                    BOOK_REQ_PARAM_MAPPER);

            for (BookRequest bookRequest : reqBooks) {

                System.out.println(
                        "Request Id ->" + bookRequest.getRequestId() + ", Status ->" + bookRequest.getStatus());
                System.out.println(" --------- ");
            }

        } catch (DBCException e) {

            e.printStackTrace();
        }
        return reqBooks;

    }

    public static List<BookRequest> getRequestsUsingBookId(int bookId) {
        ConnectionHolder connectionHolder = null;
        Connection connection = null;
        List<BookRequest> reqBooks = null;

        try {
            connectionHolder = ConnectionHolder.getInstance();
            connection = connectionHolder.getConnection();

            final ParamMapper BOOK_REQ_PARAM_MAPPER = new ParamMapper() // select id, name from user where id=?
                                                                        // password=?
            {

                public void mapParam(PreparedStatement preStmt) throws SQLException {
                    preStmt.setInt(1, bookId);

                }

            };
            reqBooks = DBHelp.executeSelect(connection, SQLMapper.RetriveBookReqDetails,
                    SQLMapper.BOOK_REQUEST_LIST_MAPPER,
                    BOOK_REQ_PARAM_MAPPER);

        } catch (DBCException e) {

            e.printStackTrace();
        }
        return reqBooks;

    }

    public static List<BookRequest> getRequestsUsingRequestId(int requestId) {
        ConnectionHolder connectionHolder = null;
        Connection connection = null;
        List<BookRequest> reqBooks = null;

        try {
            connectionHolder = ConnectionHolder.getInstance();
            connection = connectionHolder.getConnection();

            final ParamMapper BOOK_REQ_PARAM_MAPPER = new ParamMapper() // select id, name from user where id=?
                                                                        // password=?
            {

                public void mapParam(PreparedStatement preStmt) throws SQLException {
                    preStmt.setInt(1, requestId);

                }

            };
            reqBooks = DBHelp.executeSelect(connection, SQLMapper.RetriveBookReqDetailsUsingRequestId,
                    SQLMapper.BOOK_REQUEST_LIST_MAPPER,
                    BOOK_REQ_PARAM_MAPPER);

        } catch (DBCException e) {

            e.printStackTrace();
        }
        return reqBooks;

    }

    public static void confirmStatus(String status, int requestId, int bookId)
            throws Exception {
        int quantity = 0;
        List<BookRequest> requestedBookList = getRequestsUsingBookId(bookId);
        List<BookDetails> bookDetail = BookListDAO.getBookUsingBookId(bookId);
        if (bookDetail.isEmpty()) {
            throw new Exception("Couldn't find the book");
        }
        quantity = bookDetail.get(0).getQuantity();
        if (quantity == (requestedBookList.size() - 1)) {
            System.out.println(" The requested book is not available now ");
        } else {

            updateStatus("confirmed", requestId);
            System.out.println(" ");
            System.out.println(" -------------------------- ");
        }
    }

    public static List<BookRequest> getAllRequestToBeConfirmedByTheAdmin() throws DBException {
        ConnectionHolder connectionHolder = null;
        Connection connection = null;
        List<BookRequest> bookRequestsToConfirmedByAdmin = null;

        try {
            connectionHolder = ConnectionHolder.getInstance();
            connection = connectionHolder.getConnection();

            bookRequestsToConfirmedByAdmin = DBHelp.executeSelect(connection,
                    SQLMapper.RetrieveRequestsToBeConfirmedByAdmin,
                    SQLMapper.BOOK_REQUEST_LIST_MAPPER);

        } catch (DBCException e) {

            e.printStackTrace();
        }
        return bookRequestsToConfirmedByAdmin;
    }

}
