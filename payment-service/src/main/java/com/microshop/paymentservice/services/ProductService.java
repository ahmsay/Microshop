package com.microshop.paymentservice.services;

import com.microshop.paymentservice.configuration.remote.IRequestService;
import com.microshop.paymentservice.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class ProductService implements IProductService {

    @Value("${inventoryServiceUrl}")
    private String inventoryServiceUrl;

    private final IRequestService requestService;

    public ProductService(final IRequestService requestService) {
        this.requestService = requestService;
    }

    @Override
    public List<ProductDTO> findByPaymentId(final Long paymentId) {
        ProductDTO[] products = requestService.createRequest(inventoryServiceUrl)
                .toPath("/products/ofPayment/" + paymentId)
                .withHttpMethod(HttpMethod.GET)
                .withResponseType(ProductDTO[].class)
                .send();
        return Arrays.asList(products);
    }
}
