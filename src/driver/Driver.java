package driver;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import dbfw.DBException;
import dao.AdminDAO;
import dao.BookListDAO;
import dao.BookRequestDAO;
import dao.BookReturnDAO;
import dao.DAOException;
import dao.StudentDAO;
import dbc.DBCException;
import domain.Admin;
import domain.BookHireList;
import domain.BookDetails;
import domain.BookReqList;
import domain.BookRequest;
import domain.Student;
import service.AdminService;
import service.BookDetailsService;
import service.StudentService;

public class Driver {
	public static void main(String[] args) throws Exception

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
							System.out.println("Select 7 to confirm Book requests");

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
									BookDetailsService.createNewBook();
									;
									break;
								case 4:
									BookDetailsService.getBookList();
									break;
								case 5:
									StudentService.deleteStudent();
									break;
								case 6:
									BookDetailsService.deleteBook();
									break;

								case 7:
									System.out.println("Requests available to confirm");
									BookRequestDAO.getAllRequestToBeConfirmedByTheAdmin();
									System.out.println(" ------------ ");
									int requestId = scanner.nextInt();
									BookRequestDAO.updateStatus("confirmed", requestId);
									break;

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

					int studentId = scanner.nextInt();
					System.out.println("Enter  password of student");
					String password1 = scanner.next();

					list1 = StudentDAO.getStudentDetails(studentId, password1);
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
							System.out.println("Select 4 to see the open request ");
							System.out.println("Select 5 to Return the book to the library");
							System.out.println("Select 6 to see the books returned to the library");

							System.out.println();
							System.out.println("------------------------------------------------------");
							System.out.println();
							switch (scanner.nextInt()) {
								case 1:
									BookDetailsService.getBookList();
									break;
								case 2:
									BookDetailsService.searchBook(studentId);
									break;
								case 3:
									BookDetailsService.getBookList();
									System.out.println("Enter book id");
									int bookid = scanner.nextInt();
									Random random = new Random();
									int maxDigits = 100000;
									int randomNumber = random.nextInt(maxDigits);
									BookRequest bookRequest = new BookRequest(randomNumber, studentId, bookid,
											"open");
									BookRequestDAO.addNewBookRequest(bookRequest);
									break;
								case 4:
									System.out.println("----- The open book request are below ------------");
									BookRequestDAO.getBooksRequestByUser(studentId, "open");

									break;
								case 5:
									System.out.println("----- The books borrowed -----");
									System.out.println(" ");
									BookRequestDAO.getBooksRequestByUser(studentId, "confirmed");
									System.out.println(" ");
									System.out.println("Enter the requestId to return the book");
									int requestId = scanner.nextInt();
									BookReturnDAO.returnBook(requestId);

									break;
								case 6:
									System.out.println("----- The returned book requests are below ------------");
									BookRequestDAO.getBooksRequestByUser(studentId, "closed");

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

}
