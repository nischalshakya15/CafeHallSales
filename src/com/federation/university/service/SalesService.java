package com.federation.university.service;

import com.federation.university.enums.CreditCardType;
import com.federation.university.enums.PaymentType;
import com.federation.university.model.Customer;
import com.federation.university.model.Sales;
import java.util.List;

public interface SalesService {

    void saveCashTransaction(Double orderAmount, Customer customer);

    void saveCardTransaction(Double orderAmount, Customer customer, CreditCardType creditCardType);

    List<Sales> findAll();

    Double getTotalOnBasicsOfPaymentType(PaymentType paymentType);

    Double getTotalSales();

    Double getTotalDeliverySubCharges();

}
