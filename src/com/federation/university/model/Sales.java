package com.federation.university.model;

import com.federation.university.enums.CreditCardType;
import com.federation.university.enums.PaymentType;

public class Sales {

    private Long id;

    private Double salesAmount;

    private Double cardCharges;

    private Double deliverySurCharges;

    private PaymentType paymentType;

    private CreditCardType creditCardType;

    private Customer customer;

    private Double total;

    public Sales() {

    }

    public Sales(Long id, Double salesAmount, Double cardCharges, Double deliverySurCharges, PaymentType paymentType, CreditCardType creditCardType, Customer customer, Double total) {
        this.id = id;
        this.salesAmount = salesAmount;
        this.cardCharges = cardCharges;
        this.deliverySurCharges = deliverySurCharges;
        this.paymentType = paymentType;
        this.creditCardType = creditCardType;
        this.customer = customer;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getSalesAmount() {
        return salesAmount;
    }

    public void setSalesAmount(Double salesAmount) {
        this.salesAmount = salesAmount;
    }

    public Double getCardCharges() {
        return cardCharges;
    }

    public void setCardCharges(Double cardCharges) {
        this.cardCharges = cardCharges;
    }

    public Double getDeliverySurCharges() {
        return deliverySurCharges;
    }

    public void setDeliverySurCharges(Double deliverySurCharges) {
        this.deliverySurCharges = deliverySurCharges;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public CreditCardType getCreditCardType() {
        return creditCardType;
    }

    public void setCreditCardType(CreditCardType creditCardType) {
        this.creditCardType = creditCardType;
    }

    @Override
    public String toString() {
        return "Sales{" +
                "salesAmount=" + salesAmount +
                ", cardCharges=" + cardCharges +
                ", deliverySurCharges=" + deliverySurCharges +
                ", paymentType=" + paymentType +
                ", total=" + total +
                ", customer=" + customer +
                '}';
    }

}
