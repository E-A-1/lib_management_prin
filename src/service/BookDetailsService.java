package service;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import dao.BookListDAO;
import dao.DAOException;
import dbc.DBCException;
import dbfw.DBException;
import domain.BookDetails;

public class BookDetailsService {
    public static void getBookList() throws DBException, DAOException, DBCException {
        List<BookDetails> bookList = BookListDAO.getBooks();

        System.out.println("ID	 Name		isbn	Author	Edition	 Publisher  categoryid");
        if (!(bookList.isEmpty())) {
            for (Iterator it = bookList.iterator(); it.hasNext();) {

                BookDetails bookDetail = (BookDetails) it.next();

                System.out.println(
                        bookDetail.getBookId() + "	" + bookDetail.getBookName() + "	" + bookDetail.getIsbn()
                                + "	"
                                + bookDetail.getAuthor()
                                + "	 " + bookDetail.getEdition() + "	 " + bookDetail.getPublisher() + "  "
                                + bookDetail.getCategoryName());
            }
        } else
            System.out.println(" Books are unavailable this time, so please search some other time");

    }

    // to search for a book
    public static void searchBook(int userid) throws DBException, DAOException, DBCException {

        // getbooklist();
        List list = null;
        System.out.println("enter NAME of the book from the list you want");
        Scanner sc = new Scanner(System.in);
        String name = sc.next();
        int id = 0;

        list = BookListDAO.searchBook(name);
        if (!(list.isEmpty())) {

            for (Iterator it = list.iterator(); it.hasNext();) {
                BookDetails bl = (BookDetails) it.next();
                id = bl.getBookId();
            }

        } else {
            System.out.println("The book you are searching was not available");
        }

        System.out.println(
                "Yes, the book is present if you want to request the book please press any number other than 0 ");
        if (sc.nextInt() != 0) {
            //
            // requestbook(id, userid);

        }
    }

    // to create new book
    public static void createNewBook() throws DBException, DAOException {

        int result = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the book id");
        int bookId = sc.nextInt();
        System.out.println("Enter the book name");
        String bookName = sc.next();
        System.out.println("Enter the isbn");
        int isbn = sc.nextInt();
        System.out.println("Enter the author");
        String author = sc.next();
        System.out.println("Enter the publisher");
        String publisher = sc.next();
        System.out.println("Enter the edition");
        String edition = sc.next();
        System.out.println("Enter the price");
        int price = sc.nextInt();
        System.out.println("Enter the quantity");
        int quantity = sc.nextInt();

        System.out.println("Enter the category name");
        String categoryName = sc.next();

        BookDetails book = new BookDetails(bookId, bookName, isbn, author, publisher, edition, price, quantity,
                categoryName);

        result = BookListDAO.createNewBook(book);
        if (result != 0) {
            System.out.println("new book created successfully");
        } else {
            System.out.println("Failed to create the new book");
        }
    }

}
