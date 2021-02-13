package com.microshop.accountservice.services;

import com.microshop.accountservice.dto.PaymentDTO;

import java.util.List;

public interface PaymentService {

    List<PaymentDTO> findByCustomerId(Long customerId);
}
