package com.chaiyaphat.developer.backend.business;

import com.chaiyaphat.developer.backend.exception.ProductException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ProductBusiness {
    public String getProductById(String id) throws ProductException {
        if (Objects.equals("1234", id)) {
            throw ProductException.notFound();
        }
        return id;
    }
}
