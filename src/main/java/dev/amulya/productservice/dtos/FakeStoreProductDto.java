package dev.amulya.productservice.dtos;

import dev.amulya.productservice.model.Category;
import dev.amulya.productservice.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private String category;
    private String image;

    public Product convertToProduct() {
        Product product = new Product();

        product.setId(getId());
        product.setTitle(getTitle());
        product.setDescription(getDescription());
        product.setImageurl(getImage());

        Category category = new Category();
        category.setTitle(getCategory());
        product.setCategory(category);
        return product;
    }
}
