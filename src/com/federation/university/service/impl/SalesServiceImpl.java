package com.federation.university.service.impl;

import com.federation.university.dao.SalesDao;
import com.federation.university.dao.impl.SalesDaoImpl;
import com.federation.university.enums.CreditCardType;
import com.federation.university.enums.PaymentType;
import com.federation.university.model.Customer;
import com.federation.university.model.Sales;
import com.federation.university.service.SalesService;
import java.util.List;

public class SalesServiceImpl implements SalesService {

    private static final SalesDao salesDao = new SalesDaoImpl();

    /**
     * Save the cash transaction
     *
     * @param orderAmount Actual order amount
     * @param customer    {@link Customer} Customer information
     */
    @Override
    public void saveCashTransaction(Double orderAmount, Customer customer) {
        Double deliverySubCharge = getDeliverySubCharges(orderAmount);
        Double total = orderAmount + deliverySubCharge;

        Sales sales = new Sales();
        sales.setCustomer(customer);
        sales.setPaymentType(PaymentType.CASH);
        sales.setCreditCardType(CreditCardType.NONE);
        sales.setCardCharges(0.0);
        sales.setSalesAmount(orderAmount);
        sales.setDeliverySurCharges(deliverySubCharge);
        sales.setTotal(total);

        salesDao.save(sales);
    }

    /**
     * Save the card transaction
     *
     * @param orderAmount    Actual order amount
     * @param customer       {@link Customer} Customer information
     * @param creditCardType {@link CreditCardType} Credit Card Type
     */
    @Override
    public void saveCardTransaction(Double orderAmount, Customer customer, CreditCardType creditCardType) {
        Double cardCharge = getCardCharges(creditCardType);
        Double deliverySubCharge = getDeliverySubCharges(orderAmount);
        Double orderAmountWithDeliverySubCharge = orderAmount + deliverySubCharge;
        Double total = orderAmountWithDeliverySubCharge + (orderAmountWithDeliverySubCharge * cardCharge);

        Sales sales = new Sales();
        sales.setCustomer(customer);
        sales.setPaymentType(PaymentType.CARD);
        sales.setCreditCardType(creditCardType);
        sales.setSalesAmount(orderAmount);
        sales.setDeliverySurCharges(deliverySubCharge);
        sales.setCardCharges(cardCharge);
        sales.setTotal(total);

        salesDao.save(sales);
    }

    /**
     * Retrieve all Sales
     *
     * @return List<Sales>
     */
    @Override
    public List<Sales> findAll() {
        return salesDao.findAll();
    }

    /**
     * Get sales on basics of payment type
     *
     * @param paymentType {@link PaymentType}
     * @return Double totalSales on basics of payment type
     */
    @Override
    public Double getTotalOnBasicsOfPaymentType(PaymentType paymentType) {
        return salesDao.getTotalOnBasicsOfPaymentType(paymentType);
    }

    /**
     * Get total sales
     *
     * @return Double totalSales
     */
    @Override
    public Double getTotalSales() {
        return salesDao.getTotalSales();
    }

    /**
     * Get total delivery sub charge
     *
     * @return Double totalDeliverySubCharge
     */
    @Override
    public Double getTotalDeliverySubCharges() {
        return salesDao.getTotalDeliverySubCharges();
    }

    /**
     * Get delivery sub charges
     *
     * @param orderAmount Actual amount of order
     * @return Double
     */
    private Double getDeliverySubCharges(Double orderAmount) {
        if (orderAmount < 50) {
            return 10.0;
        } else if (orderAmount >= 50 && orderAmount <= 100) {
            return 5.0;
        }
        return 0.0;
    }

    /**
     * Get card charges
     *
     * @param creditCardType {@link CreditCardType}
     * @return Double
     */
    private Double getCardCharges(CreditCardType creditCardType) {
        if (CreditCardType.MASTER_CARD == creditCardType) {
            return 0.015;
        } else if (CreditCardType.AMEX_CARD == creditCardType) {
            return 0.03;
        } else {
            return 0.0;
        }
    }

}
