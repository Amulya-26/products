package dev.amulya.productservice.repositories;

import dev.amulya.productservice.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Categoryrepository extends JpaRepository<Category, Long> {

    Category save(Category category);
    Category findByTitle(String title);
    Category findById(long id);
}
