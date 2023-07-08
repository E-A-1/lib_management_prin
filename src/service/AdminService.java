package service;

import java.util.Scanner;

import dao.AdminDAO;
import dao.DAOException;
import dbfw.DBException;
import domain.Admin;

public class AdminService {
    /*
     * For creating new admin :)
     */
    public static void createNewAdmin() throws DBException, DAOException {
        int result = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter admin id");
        int adminId = sc.nextInt();
        System.out.println("Enter admin name");
        String adminName = sc.next();
        System.out.println("Enter email");
        String email = sc.next();
        System.out.println("Enter password");
        String password = sc.next();
        System.out.println("Enter contactNumber");
        String contactNumber = sc.next();
        System.out.println();
        System.out.println("----------------------------------------------------------------------------------------");

        Admin newAdmin = new Admin(adminId, adminName, password, email, contactNumber);

        result = AdminDAO.insertAdmin(newAdmin);
        if (result != 0) {
            System.out.println("New Admin created successfully .");
        } else {
            System.out.println("Failed to create new Admin");
        }
        sc.close();
    }

}
