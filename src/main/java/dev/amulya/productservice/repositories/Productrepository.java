package dev.amulya.productservice.repositories;

import dev.amulya.productservice.model.Product;
import dev.amulya.productservice.projections.Productprojections;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface Productrepository extends JpaRepository<Product,Long> {
    Product findByIdIs(Long id);
    Product save(Product entity);

    @Query("Select p from Product p Where p.category.title = :title and p.id = :id")
    Product getProductWithTitleAndId(@Param("title")String title, @Param("id")Long id);
    @Query(value = "Select * from Product p Where p.title =:title and p.id = :id",nativeQuery = true)
    Product geProductWithTitleAndId(@Param("title")String title, @Param("id")Long id);

    @Query("select p.id, p.title from Product p where p.title = :title and p.id = :id")
    Productprojections getProductWithASpecificTitleAndId(@Param("title") String title, @Param("id") Long id);

}
