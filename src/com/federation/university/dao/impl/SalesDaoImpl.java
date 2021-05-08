package com.federation.university.dao.impl;

import com.federation.university.dao.SalesDao;
import com.federation.university.enums.CreditCardType;
import com.federation.university.enums.PaymentType;
import com.federation.university.model.Customer;
import com.federation.university.model.Sales;
import java.util.LinkedList;
import java.util.List;

public class SalesDaoImpl implements SalesDao {

    private static final List<Sales> sales = new LinkedList<>();

    static {
        loadData();
    }

    private static void loadData() {
        Customer customerOne = new Customer("Chris", "Bane", "Queensland", "(08) 8329 5540");

        Customer customerTwo = new Customer("Gerry", "Brooks", "Tasmania", "(03) 6294 6675");
        Customer customerThree = new Customer("Jaylee", "Mccoy", "Victoria", " (03) 8440 3386");
        Customer customerFour = new Customer("Shana", "Daniels", "Sydney", "(02) 4999 2299");

        Sales saleOne = new Sales(1L, 40.0, 0.0, 10.0, PaymentType.CASH, CreditCardType.NONE, customerOne, 50.0);
        Sales saleTwo = new Sales(2L, 70.0, 1.125, 5.0, PaymentType.CARD, CreditCardType.MASTER_CARD, customerTwo, 76.125);
        Sales saleThree = new Sales(3L, 100.0, 3.15, 5.0, PaymentType.CARD, CreditCardType.AMEX_CARD, customerThree, 108.15);
        Sales saleFour = new Sales(4L, 120.0, 0.0, 0.0, PaymentType.CARD, CreditCardType.GIFT_CARD, customerFour, 120.0);

        sales.add(saleOne);
        sales.add(saleTwo);
        sales.add(saleThree);
        sales.add(saleFour);
    }

    /**
     * Save sale to the list
     *
     * @param sale {@link Sales}
     */
    @Override
    public void save(Sales sale) {
        Long previousId = sales.get(sales.size() - 1).getId();
        sale.setId(previousId + 1L);
        sales.add(sale);
    }

    /**
     * Return all sales
     *
     * @return List<Sales>
     */
    @Override
    public List<Sales> findAll() {
        return sales;
    }

    /**
     * Get sales on basics of payment type
     *
     * @param paymentType {@link PaymentType}
     * @return Double totalSales on basics of payment type
     */
    @Override
    public Double getTotalOnBasicsOfPaymentType(PaymentType paymentType) {
        return sales.stream()
                .filter(s -> s.getPaymentType().equals(paymentType))
                .mapToDouble(Sales::getTotal)
                .sum();
    }

    /**
     * Get total sales
     *
     * @return Double totalSales
     */
    @Override
    public Double getTotalSales() {
        return sales.stream()
                .mapToDouble(Sales::getTotal)
                .sum();
    }

    /**
     * Get total delivery sub charge
     *
     * @return Double totalDeliverySubCharge
     */
    @Override
    public Double getTotalDeliverySubCharges() {
        return sales.stream()
                .mapToDouble(Sales::getDeliverySurCharges)
                .sum();
    }
}
