package com.federation.university.dao;

import com.federation.university.enums.PaymentType;
import com.federation.university.model.Sales;
import java.util.List;

public interface SalesDao {

    void save(Sales sale);

    List<Sales> findAll();

    Double getTotalOnBasicsOfPaymentType(PaymentType paymentType);

    Double getTotalSales();

    Double getTotalDeliverySubCharges();

}
