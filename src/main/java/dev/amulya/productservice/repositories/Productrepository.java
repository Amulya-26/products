package dev.amulya.productservice.repositories;

import dev.amulya.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Productrepository extends JpaRepository<Product,Long> {
    Product findByIdIs(Long id);
    Product save(Product entity);
}
