package dev.amulya.productservice.cotrollers;

import dev.amulya.productservice.dtos.CreateProductRequestDto;
import dev.amulya.productservice.dtos.FakeStoreProductDto;

import dev.amulya.productservice.services.Productservice;
import dev.amulya.productservice.model.Product;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;
import dev.amulya.productservice.dtos.ErrorDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
public class ProductController {

    Productservice productservice;

    public ProductController( @Qualifier("selfproductservice") Productservice productservice) {
        this.productservice = productservice;
    }

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

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable("id") Long id) {
       Product product= productservice.getSingleProduct(id);
       return product;
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(Long productId) {}

//     @ExceptionHandler(NullPointerException.class)
//    public ResponseEntity<ErrorDto> handleNullPointerException(NullPointerException ex) {
//        ErrorDto errorDto = new ErrorDto();
//        errorDto.setMessage("The requested product was not found.");
//        return new ResponseEntity<>(errorDto, HttpStatus.valueOf(400));
//    }

    //get all the products
    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productservice.getAllProducts();
    }

}
