package com.federation.university.view;

import com.federation.university.view.customer.CustomerVIew;
import com.federation.university.view.sales.SalesView;
import java.util.Scanner;

public class IndexView {

    private static final Scanner scan = new Scanner(System.in);

    public static void display() {
        char ch;
        do {
            System.out.println("1. View Sales Information :- ");
            System.out.println("2. Record customer order :- ");
            int choice = scan.nextInt();

            switch (choice) {
                case 1:
                    SalesView.displaySalesUI();
                    break;
                case 2:
                    CustomerVIew.displayCustomerUI();
                    break;
                default:
                    System.out.println("Invalid choice");
            }
            System.out.println("Do you want to continue (y/Y)");
            ch = scan.next().charAt(0);
        } while (ch == 'Y' || ch == 'y');
    }

}
