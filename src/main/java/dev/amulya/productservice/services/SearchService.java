package dev.amulya.productservice.services;

import dev.amulya.productservice.model.Product;
import dev.amulya.productservice.repositories.Productrepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SearchService {

    Productrepository productRepository;

    SearchService (Productrepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> searchProducts(String query, int page, int pageSize) {
        Sort sort= Sort.by("title").descending();
        Pageable pageable = PageRequest.of(page,pageSize,sort);
        Page<Product> productsPage = productRepository.findByTitleContaining(query, pageable);
        return productsPage;

    }

}
