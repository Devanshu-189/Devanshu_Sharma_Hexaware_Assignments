package com.hexaware.cms.main;

import java.sql.SQLException;
import java.util.Scanner;

import com.hexaware.cms.dao.*;
import com.hexaware.cms.entities.Courier;
import com.hexaware.cms.exceptions.InvalidEmployeeIdException;
import com.hexaware.cms.exceptions.TrackingNumberNotFoundException;
 
public class MainModule {

	public static void main(String[] args) throws SQLException, TrackingNumberNotFoundException, InvalidEmployeeIdException {
		
		Scanner scanner = new Scanner(System.in);
        CourierDAO courierDAO = new CourierDAO();
        CourierUserServiceCollectionImpl cusci = new CourierUserServiceCollectionImpl();
        
        while (true) {
            System.out.println("\n--- Courier Management System ---");
            System.out.println("1. Insert a new courier");
            System.out.println("2. Update courier status");
            System.out.println("3. Delete a courier by ID");
            System.out.println("4. Show courier details by tracking number");
            System.out.println("6. Place an order");
            System.out.println("7. Get order status");
            System.out.println("8. Cancel order");
            System.out.println("9. Get employee by ID");
            System.out.println("10. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            
            System.out.println();

            switch (choice) {
                case 1:
                	courierDAO.insertCourier();
                    break;
                case 2:
                	System.out.print("Enter tracking number for courier: ");
                	long value = scanner.nextLong();
                    courierDAO.updateCourierStatus(value);
                    break;
                case 3:
                	System.out.print("Enter Courier ID for courier: ");
                	int val = scanner.nextInt();
                    courierDAO.deleteCourier(val);
                    break;
                case 4:
                	System.out.print("Enter tracking number for courier: ");
                	int tn = scanner.nextInt();
                    courierDAO.showCourierByTracking(tn);
                    break;
                case 6:
                	Courier newCourier = new Courier();
                	newCourier.setSenderName("Farhan");
                	newCourier.setSenderAddress("Bhopal, Madhya Pradesh");
                	newCourier.setReceiverName("Raju Rastogi");
                	newCourier.setReceiverAddress("Pune, Maharashtra");
                	newCourier.setWeight(30);
                	newCourier.setTrackingNumber(1008);
                	newCourier.setStatus("In-Transit");
                	newCourier.setDeliveryDate(new java.util.Date());
                    cusci.placeOrder(newCourier);
                    break;
                case 7:
                	System.out.print("Enter tracking number for courier to get status: ");
                	int n = scanner.nextInt();
                    cusci.getOrderStatus(n);
                    break;
                case 8:
                	System.out.print("Enter tracking number to cancel order: ");
                	Long  t = scanner.nextLong();
                    cusci.cancelOrder(t);
                    break;
                case 9:
                	System.out.print("Enter Employee ID: ");
                	int empid = scanner.nextInt();
                    courierDAO.getEmployee(empid);
                    break;
                case 10:
                    System.out.println("Exiting the application...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }

	}

}
