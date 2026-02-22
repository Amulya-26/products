package dev.amulya.productservice.cotrollers;

import dev.amulya.productservice.dtos.CreateProductRequestDto;
import dev.amulya.productservice.dtos.FakeStoreProductDto;

import dev.amulya.productservice.services.Productservice;
import dev.amulya.productservice.model.Product;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;
import dev.amulya.productservice.dtos.ErrorDto;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.List;

@RestController
@Tag(name = "Product", description = "Operations related to products")
public class ProductController {

    Productservice productservice;

    /**
     * Controller constructor.
     * @param productservice the Productservice implementation to delegate business logic to.
     *                       The controller expects the bean qualified as "selfproductservice".
     */
    public ProductController( @Qualifier("selfproductservice") Productservice productservice) {
        this.productservice = productservice;
    }

    /**
     * Create a new product.
     * HTTP POST /products
     * @param productRequestDto JSON request body containing title, description, image, category and price
     * @return the created Product object
     */
    @PostMapping("/products")
    public Product createProduct(@RequestBody CreateProductRequestDto productRequestDto) {
        return productservice.createProduct(
                productRequestDto.getTitle(),
                productRequestDto.getDescription(),
                productRequestDto.getImage(),
                productRequestDto.getCategory(),
                productRequestDto.getPrice()
        );
    }

    /**
     * Retrieve a single product by id.
     * HTTP GET /products/{id}
     * @param id path variable representing product id
     * @return the Product with the given id, or null if not found
     */
    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable("id") Long id) {
       Product product= productservice.getSingleProduct(id);
       return product;
    }

    /**
     * Delete a product by id.
     * HTTP DELETE /products/{id}
     * Note: the method parameter here is named productId; it corresponds to the path variable `id` in the mapping.
     * @param productId id of the product to delete
     * @return the deleted Product
     */
    @DeleteMapping("/products/{id}")
    public Product deleteProduct(Long productId) {
        return productservice.deleteProduct(productId);
    }

//     @ExceptionHandler(NullPointerException.class)
//    public ResponseEntity<ErrorDto> handleNullPointerException(NullPointerException ex) {
//        ErrorDto errorDto = new ErrorDto();
//        errorDto.setMessage("The requested product was not found.");
//        return new ResponseEntity<>(errorDto, HttpStatus.valueOf(400));
//    }

    /**
     * Get all products.
     * HTTP GET /products
     * @return list of all Product objects
     */
    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productservice.getAllProducts();
    }

    /**
     * Update (or create) a product.
     * HTTP PUT /products
     * Accepts the same payload as creation and delegates to the service. This functions as an upsert.
     * @param productRequestDto request body with product fields to update
     * @return the created/updated Product
     */
    @PutMapping("/products")
    public Product updateProduct(@RequestBody CreateProductRequestDto productRequestDto) {
        return productservice.createProduct(
                productRequestDto.getTitle(),
                productRequestDto.getDescription(),
                productRequestDto.getImage(),
                productRequestDto.getCategory(),
                productRequestDto.getPrice()
        );
    }

    /**
     * Retrieve a product by id using a stored procedure on the database.
     * HTTP GET /products/proc/{id}
     * @param id product id
     * @return Product returned by the stored procedure
     */
    @GetMapping("/products/proc/{id}")
    public Product getProductByIdUsingProcedure(@PathVariable("id") Long id) {
        return productservice.getProductByProcedure(id);
    }
@GetMapping("/products/description/{id}")
    /**
     * Get only the product description string for the given id.
     * HTTP GET /products/description/{id}
     * @param id product id
     * @return the product description as plain text
     */
    public  String getProductbByDescription(@PathVariable("id") Long id){
        return productservice.getProductDescription(id);
    }


}
