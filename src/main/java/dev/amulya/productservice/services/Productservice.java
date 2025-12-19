package dev.amulya.productservice.services;

import dev.amulya.productservice.dtos.FakeStoreProductDto;
import dev.amulya.productservice.model.Product;
import org.springframework.stereotype.Service;

@Service
public interface Productservice {

    public Product getSingleProduct(Long id );
    public Product createProduct(String title, String description,
                                 String image, String category, double price);
}
