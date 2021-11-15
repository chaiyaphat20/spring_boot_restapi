package com.chaiyaphat.developer.backend.api;

import com.chaiyaphat.developer.backend.business.ProductBusiness;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductAPI {

    private final ProductBusiness business;

    public ProductAPI(ProductBusiness business) {
        this.business = business;
    }

    @GetMapping("/{id}")
    //PathVariable = Params
    public ResponseEntity<String> getProductById(@PathVariable("id") String id) throws Exception{
        String response = business.getProductById(id);
        return  ResponseEntity.ok(response);
    }
}
