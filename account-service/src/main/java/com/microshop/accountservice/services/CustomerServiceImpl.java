package com.microshop.accountservice.services;

import com.microshop.accountservice.dto.CustomerDTO;
import com.microshop.accountservice.dto.OrderDTO;
import com.microshop.accountservice.dto.PaymentDTO;
import com.microshop.accountservice.entity.Customer;
import com.microshop.accountservice.repositories.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final PaymentService paymentService;
    private final OrderService orderService;

    public CustomerServiceImpl(final CustomerRepository customerRepository, final PaymentService paymentService, final OrderService orderService) {
        this.customerRepository = customerRepository;
        this.paymentService = paymentService;
        this.orderService = orderService;
    }

    @Override
    public Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public CustomerDTO findById(final Long id) {
        Customer customer = findPrunedById(id);
        if (customer == null) {
            return null;
        }
        List<PaymentDTO> paymentList = paymentService.findByCustomerId(customer.getId());
        List<OrderDTO> orderList = orderService.findByCustomerId(customer.getId());
        return new CustomerDTO(customer.getId(), customer.getName(), paymentList, orderList);
    }

    @Override
    public Customer findPrunedById(final Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public Customer save(final Customer customer) {
        return customerRepository.save(customer);
    }
}
