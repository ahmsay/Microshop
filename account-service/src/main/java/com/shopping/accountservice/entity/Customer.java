package com.shopping.accountservice.entity;

import java.util.Set;

public class Customer {

    private int id;
    private String name;
    private Set<Integer> paymentIds;

    public Customer(final int id, final String name, final Set<Integer> paymentIds) {
        this.id = id;
        this.name = name;
        this.paymentIds = paymentIds;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Set<Integer> getPaymentIds() {
        return paymentIds;
    }

    public void setPaymentIds(final Set<Integer> paymentIds) {
        this.paymentIds = paymentIds;
    }
}
