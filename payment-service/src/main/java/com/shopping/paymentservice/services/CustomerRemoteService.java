package com.shopping.paymentservice.services;

import com.shopping.paymentservice.entity.Customer;
import com.shopping.paymentservice.entity.Payment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerRemoteService implements ICustomerRemoteService {

    private RestTemplate restTemplate;
    private IPaymentService paymentService;

    public CustomerRemoteService(final RestTemplate restTemplate, final IPaymentService paymentService) {
        this.restTemplate = restTemplate;
        this.paymentService = paymentService;
    }

    @Override
    public Customer getCustomerOfPayment(final String paymentId) {
        Payment payment = paymentService.getPaymentById(paymentId);
        if (payment != null) {
            return restTemplate.getForObject("http://localhost:8081/customers/" + payment.getCustomerId(), Customer.class);
        }
        return null;
    }
}
