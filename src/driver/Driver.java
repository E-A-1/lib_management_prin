package driver;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import dbfw.DBException;
import dao.AdminDAO;
import dao.BookListDAO;
import dao.DAOException;
import dao.StudentDAO;
import dbc.DBCException;
import domain.Admin;
import domain.BookHireList;
import domain.BookList;
import domain.BookReqList;
import domain.Student;

 public class Driver {
	public static void main(String[] args) throws DBException, DAOException, DBCException

	{
		int n = 0;
		int in = 0;

		System.out.println("Welcome to our library");
		System.out.println(
				"------------------------------------------------------");
		do {
			System.out.println();
			System.out.println("For Admin click 1 ");
			System.out.println("For Student click 2");
			Scanner sc = new Scanner(System.in);
			int choose = sc.nextInt();
			System.out.println();
			System.out.println(
					"-----------------------------------------------------");
			System.out.println();

			switch (choose) {
			case 1: // admin login
				List list = null;
				System.out.println("Provide your userid");
				int userid = sc.nextInt();
				System.out.println("Provide your password");

				String password = sc.next();

				list = AdminDAO.getadmindetails(userid, password);
				if (!(list.isEmpty())) {
					System.out.println();
					System.out.println("Login successfull");
					System.out.println("  ");

					for (Iterator it = list.iterator(); it.hasNext();) {
						Admin a = (Admin) it.next();

					}
					do {
						System.out.println("Select 1 for new Admin details to create ");
						System.out.println("Select 2 for new Student details to create ");
						System.out.println("Select 3 for new Book details to create ");
						System.out.println("Select 4 to search for the Book details");
						System.out.println("Select 5 to delete for the Student details");
						System.out.println("Select 6 to delete for the Book details");
					    //System.out.println("Select 7 to update Book details");
						
						System.out.println();
						System.out.println("---------------------------------------------");
						System.out.println();
						int select = sc.nextInt();
						switch (select) {

						case 1:
							createadmin();
							break;
						case 2:
							createstudent();
							break;
						case 3:
							createbook();
							break;
						case 4:
							getbooklist();
							break;
						case 5:
							deletestudent();
							break;
						case 6:
							deletebook();
							break;
							
//						case 7:
//							updateBookList();
//							break;

						default:
							System.out.println("Please select the value with in the options");
							System.out.println();
						}
						System.out.println("Enter 0 for logout, else press 1 for continue");
						in = sc.nextInt();
					} while (in != 0);
				}

				else {
					System.out.println();
					System.out.println("Please provide correct password or userid");
					System.out.println();
				}
				break;

			case 2: // student login
				List list1 = null;
				System.out.println("Enter Loginid of student");

				int Loginid = sc.nextInt();
				System.out.println("Enter  password of student");
				String password1 = sc.next();

				list1 = StudentDAO.getstudentdetails(Loginid, password1);
				if (!(list1.isEmpty())) {
					System.out.println("Login sucessfull");
					System.out.println(
							"----------------------------------------------");
					for (Iterator it1 = list1.iterator(); it1.hasNext();)

					{
						Student st = (Student) it1.next();
					}
					do {
						System.out.println("Please select operation from below");
						System.out.println("Select 1 to know the list of books present in the library");
						System.out.println("Select 2 for search the book");
						System.out.println("Select 3 for request the book from library");
						System.out.println("Select 4 for Confirm the Request for a selected book ");
						System.out.println("Select 5 to Return the book to the library");
						System.out.println();
						System.out.println("------------------------------------------------------");
						System.out.println();
						switch (sc.nextInt()) {
						case 1:
							getbooklist();
							break;
						case 2:
							searchbook(Loginid);
							break;
						case 3:
							getbooklist();
							System.out.println("Enter book id");
							int bookid = sc.nextInt();
							requestbook(bookid, Loginid);
							break;
						case 4:
							confirmreq(Loginid);
							break;
						case 5:
							returnbook(Loginid);
							break;
						default:
							System.out.println("Please select the value with in the given options");
							System.out.println();
						}
						System.out.println("Enter 0 for logout, else press 1 for continue");
						in = sc.nextInt();
					} while (in != 0);

				} else {
					System.out.println();
					System.out.println("Please provide correct credentials for password or userid");
					System.out.println();
				}
				break;

			}
			System.out.println("Please press any number except 0 to continue");
			n = sc.nextInt();
			System.out.println(
					"------------------------------------------------------------------------------------------------------------------------");
		} while (n != 0);
		System.out.println();
		System.out.println(" 							Thank you and visit again !...");
		System.out.println(
				"----------------------------------------------------------------------------------------------------------------------");

	}

	// For admin insert;
	public static void createadmin() throws DBException, DAOException {
		int result = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("Provide userid");
		int userid = sc.nextInt();
		System.out.println("Provide roleid");
		int roleid = sc.nextInt();
		System.out.println("Provide email");
		String email = sc.next();
		System.out.println("Provide mobile");
		String mobile = sc.next();
		System.out.println("Provide password");
		String password = sc.next();
		System.out.println();
		System.out.println("----------------------------------------------------------------------------------------");

		Admin a = new Admin(userid, roleid, email, mobile, password);

		result = AdminDAO.adminInsert(a);
		if (result != 0) {
			System.out.println("New admin created successfully");
		} else {
			System.out.println("Failed to create new admin");
		}
	}

	// for student insert;
	public static void createstudent() throws DBException

	{
		int result = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("Provide userid");
		int userid = sc.nextInt();
		System.out.println("Provide roleid");
		int roleid = sc.nextInt();
		System.out.println("Provide email");
		String email = sc.next();
		System.out.println("Provide mobile");
		String mobile = sc.next();
		System.out.println("Provide password");
		String password = sc.next();
		Student s = new Student(userid, roleid, email, mobile, password);

		result = StudentDAO.studentInsert(s);
		if (result != 0) {
			System.out.println("new details for the student was created successfully");
		} else {
			System.out.println("Failed to create the new admin,give details was wrong");
		}
	}

	// to create new book
	public static void createbook() throws DBException, DAOException {

		int result = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("Give the value of bookid");
		int bookid = sc.nextInt();
		System.out.println("Give the value of bookname");
		String bookname = sc.next();
		System.out.println("Give the value of isbn");
		int isbn = sc.nextInt();
		System.out.println("Give the name of author");
		String author = sc.next();
		System.out.println("Give the name of publisher");
		String publisher = sc.next();
		System.out.println("Give the value of edition");
		String edition = sc.next();
		System.out.println("Give the value of price");
		double price = sc.nextDouble();
		System.out.println("Give the value of quantity");
		int quantity = sc.nextInt();

		System.out.println("Give the value of categoryid");
		int categoryid = sc.nextInt();

		BookList bl = new BookList(bookid, bookname, isbn, author, publisher, edition, price, quantity, categoryid);

		result = BookListDAO.insertbooks(bl);
		if (result != 0) {
			System.out.println("new book created successfully");
		} else {
			System.out.println("Failed to create the new book");
		}
	}

	// to delete student
	public static void deletestudent() throws DAOException, DBException, DBCException {
		int result = 0;
		boolean pendingbooks = false;
		Scanner sc = new Scanner(System.in);
		System.out.println("Provide the student userid to be deleted");
		int userid = sc.nextInt();
		List booklist = null;
		booklist = BookListDAO.UserHireBooks(userid);
		if (!(booklist.isEmpty())) {

			for (Iterator it = booklist.iterator(); it.hasNext();) {
				BookHireList brl = (BookHireList) it.next();
			}
			System.out.println("please return the book to library to delete your credentials");
		} else {
			result = AdminDAO.deletestudent(userid);
			if (result != 0) {
				System.out.println("Deleted the student");
			} else
				System.out.println("not deleted the student");
		}
	}

	// to delete book
	public static void deletebook() throws DAOException, DBException, DBCException {
		int result = 0;
		boolean pendingbooks = false;
		Scanner sc = new Scanner(System.in);
		System.out.println("Provide the bookid to be deleted");
		int bookid = sc.nextInt();
		List booklist = null;
		booklist = BookListDAO.getReqBooks(bookid);
		if (!(booklist.isEmpty())) {

			for (Iterator it = booklist.iterator(); it.hasNext();) {
				BookReqList brl = (BookReqList) it.next();
			}
			System.out.println("some books are still at students so unable to delete the books");
		}

		else {
			result = AdminDAO.deletebook(bookid);
			if (result != 0) {
				System.out.println("deleted the book");
			} else
				System.out.println("not deleted the book");
		}
	}

	// to get booklist
	public static void getbooklist() throws DBException, DAOException, DBCException {
		List booklist = null;
		booklist = BookListDAO.getBooks();
		System.out.println("ID	 Name		isbn	Author	Edition	 Publisher  categoryid");
		if (!(booklist.isEmpty())) {
			for (Iterator it = booklist.iterator(); it.hasNext();) {
				BookList bl = (BookList) it.next();
				System.out.println(bl.getB_id() + "	" + bl.getB_name() + "	" + bl.getB_isbn() + "	" + bl.getB_author()
						+ "	 " + bl.getB_edition() + "	 " + bl.getB_publisher() + "  " + bl.getCategoryid());
			}
		} else
			System.out.println(" Books are unavailable this time, so please search some other time");

	}

	// to search for a book
	public static void searchbook(int userid) throws DBException, DAOException, DBCException {

		// getbooklist();
		List list = null;
		System.out.println("enter NAME of the book from the list you want");
		Scanner sc = new Scanner(System.in);
		String name = sc.next();
		int id = 0;

		list = BookListDAO.getBook(name);
		if (!(list.isEmpty())) {

			for (Iterator it = list.iterator(); it.hasNext();) {
				BookList bl = (BookList) it.next();
				id = bl.getB_id();
			}

		} else {
			System.out.println("The book you are searching was not available");
		}

		System.out.println(
				"Yes, the book is present if you want to request the book please press any number other than 0 ");
		if (sc.nextInt() != 0) {
			requestbook(id, userid);

		}
	}

	public static void requestbook(int Bookid, int userid) throws DAOException, DBException {
		BookListDAO.insertrequest(Bookid, userid);

		System.out.println(
				"To Complete your request please confirm your request in confirm request option from login menu");

	}

	// to confirm requested book
	public static void confirmreq(int userid) throws DAOException, DBException, DBCException {
		Scanner sc = new Scanner(System.in);
		boolean bool = false;
		int quantity = 0;
		int hireid=0;
		int Bookid = 0;
		int Reqid = 0;
		String Name = "";
		String Edition = "";
		String Publisher = "";
		String Author = "";
		Double price = 0.0;
		int ISBN = 0;
		List list2 = null;
		String returndate="";
		int result = 0;
		List booklist = null;
		booklist = BookListDAO.UserReqBooks(userid);
		if (!(booklist.isEmpty())) {
			for (Iterator it = booklist.iterator(); it.hasNext();) {
				BookReqList brl = (BookReqList) it.next();
				System.out.println(brl.getUserid() + " " + brl.getBook_id() + "  " + brl.getDate());

			}

			System.out.println(" enter the user id to be confirmed and that is based on the requested date");
			userid = sc.nextInt();
			System.out.println(" enter the book id to be confirmed and that is based on your requested date");
			Bookid = sc.nextInt();
			list2 = AdminDAO.requestbook(Bookid);
			if (!(list2.isEmpty())) {
				bool = true;
				for (Iterator it = list2.iterator(); it.hasNext();) {
					BookList b = (BookList) it.next();
					quantity = b.getQuantity();
				}
				int updated_quantity = quantity - 1;
				if (bool) {
					booklist = BookListDAO.Books(Bookid);
					if (!(booklist.isEmpty())) {
						for (Iterator it = booklist.iterator(); it.hasNext();) {
							BookList bl = (BookList) it.next();
							Name = bl.getB_name();
							ISBN = bl.getB_isbn();
							Author = bl.getB_author();
							Edition = bl.getB_edition();
							Publisher = bl.getB_publisher();
							price = bl.getPrice();

						}
					}
					System.out.println("confirmed the request for requestedbook .. waiting for admin approval");
					BookListDAO.insertconformation(Bookid, Name, ISBN, Author, Publisher, Edition, price,quantity,returndate,hireid,
							userid);

					AdminDAO.updateBookList(Bookid, updated_quantity);
					System.out.println("Request for book is accepted ,,Please collect it from the Library Incharge");

				}

			} else {
				System.out.println("Sorry!! book is not available for now. You can request it on some other day");
			}
		} else
			System.out.println("you have not requested any book yet");

	}

	// to return book
	public static void returnbook(int userid) throws DAOException, DBException, DBCException

	{

		int result = 0;
		int quantity = 0;
		List booklist = null;

		booklist = BookListDAO.UserHireBooks(userid);
		if (!(booklist.isEmpty())) {

			for (Iterator it = booklist.iterator(); it.hasNext();) {
				BookHireList bhl = (BookHireList) it.next();
				System.out.println(bhl.getHireid() + " " + bhl.getB_id() + "  " + bhl.getReturndate());

			}
			Scanner sc = new Scanner(System.in);
			System.out.println("Press enter the hire id you want to return");
			int hireid = sc.nextInt();
			System.out.println("Press enter the book id you want to return");
			int bookid = sc.nextInt();
			result = AdminDAO.deletehiredbook(hireid);

			List list2 = null;
			list2 = AdminDAO.requestbook(bookid);

			if (!(list2.isEmpty())) {

				for (Iterator it = list2.iterator(); it.hasNext();) {
					BookList b = (BookList) it.next();
					quantity = b.getQuantity();
				}
			}
			int updated_quantity = quantity + 1;
			AdminDAO.updateBookList(bookid, updated_quantity);
			if (result != 0) {
				System.out.println("returned");
			} else
				System.out.println("not returned");

		} else {
			System.out.println("Sorry!! No books has taken by the user with user id" + userid);
		}
	}
}
