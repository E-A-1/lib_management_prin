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
import domain.BookDetails;
import domain.BookReqList;
import domain.Student;
import service.AdminService;
import service.BookDetailsService;
import service.StudentService;

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
			System.out.println("Are you a admin or user ? Please enter");
			Scanner scanner = new Scanner(System.in);
			String userType = scanner.nextLine();
			System.out.println();

			switch (userType) {
				case "admin": // admin login
					List list = null;
					System.out.println("Enter your userid");
					int adminId = scanner.nextInt();
					System.out.println("Enter your password");

					String password = scanner.next();

					list = AdminDAO.getAdminDetails(adminId, password);
					if (!(list.isEmpty())) {
						System.out.println();
						System.out.println("Login successfull");
						System.out.println("  ");

						for (Iterator it = list.iterator(); it.hasNext();) {
							Admin a = (Admin) it.next();

						}
						do {
							System.out.println("Enter 1 to create new Admin ");
							System.out.println("Enter 2 to create new Student ");
							System.out.println("Enter 3 to add new Book ");
							System.out.println("Enter 4 to search for the Book details");
							System.out.println("Enter 5 to delete for the Student details");
							System.out.println("Enter 6 to delete for the Book details");
							// System.out.println("Select 7 to update Book details");

							System.out.println();
							System.out.println("---------------------------------------------");
							System.out.println();
							int select = scanner.nextInt();
							switch (select) {

								case 1:
									AdminService.createNewAdmin();
									break;
								case 2:
									StudentService.createStudent();

									break;
								case 3:
									createbook();
									break;
								case 4:
									getbooklist();
									break;
								case 5:
									StudentService.deleteStudent();
									break;
								case 6:
									deletebook();
									break;

								// case 7:
								// updateBookList();
								// break;

								default:
									System.out.println("Please select the value with in the options");
									System.out.println();
							}
							System.out.println("Enter 0 for logout, else press 1 for continue");
							in = scanner.nextInt();
						} while (in != 0);
					}

					else {
						System.out.println();
						System.out.println("Please provide correct password or userid");
						System.out.println();
					}
					break;

				case "student": // student login
					List list1 = null;
					System.out.println("Enter Loginid of student");

					int Loginid = scanner.nextInt();
					System.out.println("Enter  password of student");
					String password1 = scanner.next();

					list1 = StudentDAO.getStudentDetails(Loginid, password1);
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
							switch (scanner.nextInt()) {
								case 1:
									BookDetailsService.getBookList();
									break;
								case 2:
									BookDetailsService.searchBook(Loginid);
									break;
								case 3:
									BookDetailsService.getBookList();
									System.out.println("Enter book id");
									int bookid = scanner.nextInt();
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
							in = scanner.nextInt();
						} while (in != 0);

					} else {
						System.out.println();
						System.out.println("Please provide correct credentials for password or userid");
						System.out.println();
					}
					break;

			}
			System.out.println("Please press any number except 0 to continue");
			n = scanner.nextInt();
			System.out.println(
					"------------------------------------------------------------------------------------------------------------------------");
		} while (n != 0);
		System.out.println();
		System.out.println(" 							Thank you and visit again !...");
		System.out.println(
				"----------------------------------------------------------------------------------------------------------------------");

	}

	// for student insert;

	// to create new book

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
		int hireid = 0;
		int Bookid = 0;
		int Reqid = 0;
		String Name = "";
		String Edition = "";
		String Publisher = "";
		String Author = "";
		Double price = 0.0;
		int ISBN = 0;
		List list2 = null;
		String returndate = "";
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
					BookDetails b = (BookDetails) it.next();
					quantity = b.getQuantity();
				}
				int updated_quantity = quantity - 1;
				if (bool) {
					booklist = BookListDAO.Books(Bookid);
					if (!(booklist.isEmpty())) {
						for (Iterator it = booklist.iterator(); it.hasNext();) {
							BookDetails bl = (BookDetails) it.next();
							Name = bl.getB_name();
							ISBN = bl.getB_isbn();
							Author = bl.getB_author();
							Edition = bl.getB_edition();
							Publisher = bl.getB_publisher();
							price = bl.getPrice();

						}
					}
					System.out.println("confirmed the request for requestedbook .. waiting for admin approval");
					BookListDAO.insertconformation(Bookid, Name, ISBN, Author, Publisher, Edition, price, quantity,
							returndate, hireid,
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
					BookDetails b = (BookDetails) it.next();
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
