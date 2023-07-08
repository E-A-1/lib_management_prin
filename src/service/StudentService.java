package service;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import dao.AdminDAO;
import dao.BookListDAO;
import dao.DAOException;
import dao.StudentDAO;
import dbc.DBCException;
import dbfw.DBException;
import domain.BookHireList;
import domain.Student;

public class StudentService {
    public static void createStudent() throws DBException

    {
        int result = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter student id");
        int studentId = sc.nextInt();
        System.out.println("Enter student name");
        String studentName = sc.next();
        System.out.println("Enter email");
        String email = sc.next();
        System.out.println("Enter contact number");
        String contactNumber = sc.next();
        System.out.println("Enter password");
        String password = sc.next();
        Student s = new Student(studentId, studentName, password, email, contactNumber);

        result = StudentDAO.createNewStudent(s);
        if (result != 0) {
            System.out.println("Create new student successfully");
        } else {
            System.out.println("Failed to create the new admin,give details was wrong");
        }

        sc.close();
    }

    public static void deleteStudent() throws DAOException, DBException, DBCException {
        int result = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the student id to be deleted");
        int studentId = scanner.nextInt();

        List booklist = BookListDAO.UserHireBooks(studentId);
        if (!(booklist.isEmpty())) {

            for (Iterator it = booklist.iterator(); it.hasNext();) {
                BookHireList brl = (BookHireList) it.next();
            }
            System.out.println("please return the book to library to delete your credentials");
        } else {
            result = AdminDAO.deletestudent(studentId);
            if (result != 0) {
                System.out.println("Deleted the student");
            } else
                System.out.println("Not deleted the student");
        }

        scanner.close();
    }
}
