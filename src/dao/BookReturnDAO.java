package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import dbc.ConnectionHolder;
import dbfw.DBHelp;
import dbfw.ParamMapper;
import domain.BookDetails;
import domain.BookRequest;

public class BookReturnDAO {

    public static void returnBook(int requestId) throws Exception {
        ConnectionHolder connectionHolder = ConnectionHolder.getInstance();
        Connection connection = connectionHolder.getConnection();
        BookRequest bookDetail = null;

        List<BookRequest> bookRequest = BookRequestDAO.getRequestsUsingRequestId(requestId);
        if (bookRequest.isEmpty()) {
            throw new Exception(" -- Coudnt find the book request ");

        } else {

            bookDetail = bookRequest.get(0);
            BookRequestDAO.updateStatus("closed", requestId);

            ParamMapper paramMapper = new ParamMapper() {
                @Override
                public void mapParam(PreparedStatement preStmt) throws SQLException {
                    preStmt.setInt(1, requestId);
                    preStmt.setString(2, "returned");
                    preStmt.setDate(3, (java.sql.Date) new Date());
                }
            };

            DBHelp.executeUpdate(connection, SQLMapper.INSERT_INTO_BOOK_RETURN, paramMapper);

            System.out.println(" Book returned successfully");
        }
    }

}
