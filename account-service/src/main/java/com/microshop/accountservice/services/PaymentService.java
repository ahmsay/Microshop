package com.microshop.accountservice.services;

import com.microshop.accountservice.configuration.remote.IRequestService;
import com.microshop.accountservice.configuration.remote.URLs;
import com.microshop.accountservice.dto.PaymentDTO;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class PaymentService implements IPaymentService {

    private final IRequestService requestService;
    private final URLs urls;

    public PaymentService(final IRequestService requestService, final URLs urls) {
        this.requestService = requestService;
        this. urls = urls;
    }

    @Override
    public List<PaymentDTO> findByCustomerId(final Long customerId) {
        PaymentDTO[] payments = requestService.createRequest(urls.getPayment())
                .toPath("/payments/ofCustomer/" + customerId)
                .withHttpMethod(HttpMethod.GET)
                .withResponseType(PaymentDTO[].class)
                .send();
        return Arrays.asList(payments);
    }
}
