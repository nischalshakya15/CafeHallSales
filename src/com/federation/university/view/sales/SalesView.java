package com.federation.university.view.sales;

import com.federation.university.enums.PaymentType;
import com.federation.university.service.SalesService;
import com.federation.university.service.impl.SalesServiceImpl;
import com.federation.university.utils.CommandLineTable;
import java.util.Scanner;

public class SalesView {

    private static final Scanner scan = new Scanner(System.in);

    private static final SalesService salesService = new SalesServiceImpl();

    /**
     * Display sales record choice
     */
    public static void displaySalesUI() {
        System.out.println("1. View All Records :- ");
        System.out.println("2. View total sales :- ");
        System.out.println("3. View total delivery sub charges :- ");
        int choice = scan.nextInt();
        switch (choice) {
            case 1:
                displayAllRecords();
                break;
            case 2:
                displayTotalSales();
                break;
            case 3:
                displayTotalDeliverySubCharges();
                break;
            default:
                System.out.println("Invalid choice");
        }
    }

    /**
     * Display all Sales Record
     */
    private static void displayAllRecords() {
        CommandLineTable commandLineTable = new CommandLineTable();
        commandLineTable.setShowVerticalLines(true);

        commandLineTable.setHeaders("ID", "CUSTOMER", "ORDER AMOUNT ($)", "DELIVERY SUB CHARGES ($)", "CARD CHARGES ($)", "PAYMENT TYPE", "CARD TYPE", "TOTAL ($)");
        salesService.findAll().forEach(sale -> {
            commandLineTable.addRow(
                    String.valueOf(sale.getId()),
                    sale.getCustomer().getFirstName() + " " + sale.getCustomer().getLastName(),
                    String.valueOf(sale.getSalesAmount()),
                    String.valueOf(sale.getDeliverySurCharges()),
                    String.valueOf(sale.getCardCharges()),
                    sale.getPaymentType().name(),
                    sale.getCreditCardType().name(),
                    String.valueOf(sale.getTotal())
            );
        });

        commandLineTable.print();
    }

    /**
     * Display total Sales on basics of {@link PaymentType}
     */
    private static void displayTotalSales() {
        CommandLineTable commandLineTable = new CommandLineTable();
        commandLineTable.setShowVerticalLines(true);

        commandLineTable.setHeaders("CASH ($)", "CREDIT CARD ($)", "TOTAL ($)");
        commandLineTable.addRow(
                String.valueOf(salesService.getTotalOnBasicsOfPaymentType(PaymentType.CASH)),
                String.valueOf(salesService.getTotalOnBasicsOfPaymentType(PaymentType.CARD)),
                String.valueOf(salesService.getTotalSales())
        );

        commandLineTable.print();
    }

    /**
     * Display total Delivery Sub Charges
     */
    private static void displayTotalDeliverySubCharges() {
        CommandLineTable commandLineTable = new CommandLineTable();
        commandLineTable.setShowVerticalLines(true);

        commandLineTable.setHeaders("TOTAL SUR CHARGE($)");
        commandLineTable.addRow(
                String.valueOf(salesService.getTotalDeliverySubCharges())
        );

        commandLineTable.print();
    }

}
