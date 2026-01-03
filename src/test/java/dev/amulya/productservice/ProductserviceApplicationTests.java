package dev.amulya.productservice;

import dev.amulya.productservice.model.Category;
import dev.amulya.productservice.model.Product;
import dev.amulya.productservice.projections.Productprojections;
import dev.amulya.productservice.repositories.Categoryrepository;
import dev.amulya.productservice.repositories.Productrepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ProductserviceApplicationTests {
    @Autowired
    Productrepository productrepository;
    @Autowired
    Categoryrepository categoryrepository;

    @Test
    void contextLoads() {
    }

    @Test
    void getQuery() {
        Product p = productrepository.getProductWithTitleAndId("phone", 4L);
        System.out.println(p.getTitle());

    }

    @Test
    void getNativeQuery() {
        Product p = productrepository.geProductWithTitleAndId("amulya", 4L);
        System.out.println(p.getTitle());

    }
@Test
    void getProjectionQuery() {
        Productprojections p = productrepository.getProductWithASpecificTitleAndId("amulya", 4L);
        System.out.println(p.getTitle());
        System.out.println(p.getDescription());

    }
    @Test
    @Transactional
    void getFetchModes() {
        Category category = categoryrepository.findById(1L);
        System.out.println("Category Title: " + category.getTitle());
        System.out.println("Products in this Category: " + category.getProducts());
//        for (Product p : category.getProducts()) {
//            System.out.println("Product Title: " + p.getTitle());


    }
  @Test
  @Transactional
    void nplusprblm() {
        //n+1 problem
        //solution fetch type eager and join fetch
       List<Category> cate = categoryrepository.findAll();
        for(Category category: cate){
            for(Product product: category.getProducts()){
                System.out.println("Product Title: " + product.getTitle());
            }
        }
    }
    @Test
    @Transactional
    void testfetchtype(){
        //fetchmodes
        //select,subselect,join
        Category category = categoryrepository.findById(3L);
        System.out.println("Category Title: " + category.getTitle());
        for(Product product: category.getProducts()){
            System.out.println("Product Title: " + product.getTitle());
        }

    }

}
