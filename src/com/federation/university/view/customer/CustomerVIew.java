package com.federation.university.view.customer;

import com.federation.university.enums.CreditCardType;
import com.federation.university.enums.PaymentType;
import com.federation.university.model.Customer;
import com.federation.university.service.SalesService;
import com.federation.university.service.impl.SalesServiceImpl;
import java.util.Scanner;

public class CustomerVIew {

    private static final Scanner scan = new Scanner(System.in);

    private static final SalesService salesService = new SalesServiceImpl();

    public static void displayCustomerUI() {
        System.out.println("Select the mode of payment :- ");
        System.out.println("1. Cash");
        System.out.println("2. Card");

        int choice = scan.nextInt();

        switch (choice) {
            case 1:
                handleCashPayment();
                break;
            case 2:
                handleCardPayment();
                break;
            default:
                System.out.println("Invalid choice");
        }
    }

    /**
     * if payment mode is {@link PaymentType} Cash
     */
    private static void handleCashPayment() {
        double orderAmount = getOrderAmount();
        Customer customer = getCustomerInfo();
        salesService.saveCashTransaction(orderAmount, customer);

        System.out.println("Information successfully saved");
    }

    /**
     * if payment mode is {@link PaymentType} Card
     */
    private static void handleCardPayment() {
        Double orderAmount = getOrderAmount();
        Customer customer = getCustomerInfo();
        CreditCardType creditCardType = getCreditCardType();

        salesService.saveCardTransaction(orderAmount, customer, creditCardType);

        System.out.println("Information successfully saved");
    }

    /**
     * Get the orderAmount from user
     *
     * @return Double
     */
    private static double getOrderAmount() {
        System.out.println("Enter order amount($) :- ");
        return scan.nextDouble();
    }

    /**
     * Get the customerInfo from user
     *
     * @return Customer
     */
    private static Customer getCustomerInfo() {
        System.out.println("Enter first name :- ");
        String firstName = scan.next();
        System.out.println("Enter last name :- ");
        String lastName = scan.next();
        System.out.println("Enter address :- ");
        String address = scan.next();
        System.out.println("Enter contact number :- ");
        String contactNumber = scan.next();
        return new Customer(firstName, lastName, address, contactNumber);
    }

    /**
     * Get creditCardType {@link CreditCardType}
     *
     * @return CreditCardType
     */
    private static CreditCardType getCreditCardType() {
        System.out.println("Enter card type :- ");
        System.out.println("1. Master Card");
        System.out.println("2. Amex Card");
        System.out.println("3. Gift Card");

        int choice = scan.nextInt();

        switch (choice) {
            case 1:
                return CreditCardType.MASTER_CARD;
            case 2:
                return CreditCardType.AMEX_CARD;
            case 3:
                return CreditCardType.GIFT_CARD;
            default:
                System.out.println("Invalid choice");
        }
        return null;
    }

}
