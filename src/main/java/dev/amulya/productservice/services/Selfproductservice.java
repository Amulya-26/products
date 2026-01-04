package dev.amulya.productservice.services;

import dev.amulya.productservice.model.Category;
import dev.amulya.productservice.model.Product;
import dev.amulya.productservice.repositories.Categoryrepository;
import dev.amulya.productservice.repositories.Productrepository;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("selfproductservice")
@Primary
public class Selfproductservice implements Productservice{


    Productrepository productrepository;

    Categoryrepository categoryrepository;
    public Selfproductservice(Productrepository productrepository, Categoryrepository categoryrepository) {
        this.productrepository = productrepository;
        this.categoryrepository = categoryrepository;
    }

    @Override
    @Cacheable(value="products",key="#id")
    public Product getSingleProduct(Long id) {

        return productrepository.findByIdIs(id);
    }

    @Override
    public Product createProduct(String title, String description, String image, String categorytitle, double price) {
        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setImageurl(image);
        product.setPrice(price);
        Category categoryfromdb= categoryrepository.findByTitle(categorytitle);
        if(categoryfromdb==null) {
            //fetch category from db
            Category categorynew = new Category();
            categorynew.setTitle(categorytitle);
//            categoryfromdb = categorynew;
          categoryfromdb = categoryrepository.save(categorynew);
        }
        product.setCategory(categoryfromdb);
        productrepository.save(product);
        return product;
    }
    //get all products
    @Override
    @Cacheable(value="allproducts")
    public List<Product> getAllProducts(){
        return productrepository.findAll();
    }

}
