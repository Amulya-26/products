gitpackage dev.amulya.productservice.services;

import dev.amulya.productservice.model.Category;
import dev.amulya.productservice.model.Product;
import dev.amulya.productservice.repositories.Categoryrepository;
import dev.amulya.productservice.repositories.Productrepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class SelfproductserviceTest {

    Productrepository productrepository;
    Categoryrepository categoryrepository;
    Selfproductservice service;

    @BeforeEach
    public void setup() {
        productrepository = Mockito.mock(Productrepository.class);
        categoryrepository = Mockito.mock(Categoryrepository.class);
        service = new Selfproductservice(productrepository, categoryrepository);
    }

    @Test
    public void createProduct_savesCategoryAndProduct_whenCategoryMissing() {
        when(categoryrepository.findByTitle("Cat1")).thenReturn(null);
        when(categoryrepository.save(any(Category.class))).thenAnswer(invocation -> {
            Category c = invocation.getArgument(0);
            c.setId(10L);
            return c;
        });

        Product saved = new Product();
        when(productrepository.save(any(Product.class))).thenAnswer(invocation -> {
            Product p = invocation.getArgument(0);
            p.setId(5L);
            return p;
        });

        Product result = service.createProduct("t", "d", "i", "Cat1", 1.2);

        assertThat(result.getId()).isEqualTo(5L);
        verify(categoryrepository).findByTitle("Cat1");
        verify(categoryrepository).save(any(Category.class));
        verify(productrepository).save(any(Product.class));
    }

    @Test
    public void getSingleProduct_returnsFromRepository() {
        Product p = new Product();
        p.setId(2L);
        when(productrepository.findByIdIs(2L)).thenReturn(p);

        Product out = service.getSingleProduct(2L);
        assertThat(out.getId()).isEqualTo(2L);
    }

    @Test
    public void getAllProducts_callsRepository() {
        when(productrepository.findAll()).thenReturn(List.of(new Product()));
        List<Product> all = service.getAllProducts();
        assertThat(all).hasSize(1);
    }

    @Test
    public void deleteProduct_returnsDeletedProduct() {
        Product p = new Product();
        p.setId(3L);
        when(productrepository.findById(3L)).thenReturn(Optional.of(p));

        Product out = service.deleteProduct(3L);
        verify(productrepository).deleteById(3L);
        assertThat(out.getId()).isEqualTo(3L);
    }
}

