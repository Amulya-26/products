package dev.amulya.productservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.amulya.productservice.cotrollers.ProductController;
import dev.amulya.productservice.dtos.CreateProductRequestDto;
import dev.amulya.productservice.model.Category;
import dev.amulya.productservice.model.Product;
import dev.amulya.productservice.services.Productservice;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean(name = "selfproductservice")
    private Productservice productservice;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private Product sampleProduct(Long id) {
        Product p = new Product();
        p.setId(id);
        p.setTitle("Test Product " + id);
        p.setDescription("Description " + id);
        p.setPrice(9.99);
        Category c = new Category();
        c.setId(1L);
        p.setCategory(c);
        p.setImageurl("http://example.com/image.png");
        return p;
    }

    @Test
    public void testGetProductById() throws Exception {
        Product p = sampleProduct(1L);
        when(productservice.getSingleProduct(1L)).thenReturn(p);

        mockMvc.perform(get("/products/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Test Product 1"));
    }

    @Test
    public void testGetAllProducts() throws Exception {
        Product p1 = sampleProduct(1L);
        Product p2 = sampleProduct(2L);
        when(productservice.getAllProducts()).thenReturn(Arrays.asList(p1, p2));

        mockMvc.perform(get("/products").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2));
    }

    @Test
    public void testCreateProduct() throws Exception {
        CreateProductRequestDto req = new CreateProductRequestDto();
        req.setTitle("New Title");
        req.setDescription("New Desc");
        req.setImage("http://img.png");
        req.setCategory("Category1");
        req.setPrice(12.5);

        Product returned = sampleProduct(5L);

        when(productservice.createProduct(anyString(), anyString(), anyString(), anyString(), anyDouble()))
                .thenReturn(returned);

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(5))
                .andExpect(jsonPath("$.title").value("Test Product 5"));
    }

    @Test
    public void testUpdateProduct() throws Exception {
        CreateProductRequestDto req = new CreateProductRequestDto();
        req.setTitle("Updated Title");
        req.setDescription("Updated Desc");
        req.setImage("http://img.png");
        req.setCategory("Category1");
        req.setPrice(22.5);

        Product returned = sampleProduct(6L);

        when(productservice.createProduct(anyString(), anyString(), anyString(), anyString(), anyDouble()))
                .thenReturn(returned);

        mockMvc.perform(put("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(6))
                .andExpect(jsonPath("$.title").value("Test Product 6"));
    }

    @Test
    public void testGetProductByProcedure() throws Exception {
        Product p = sampleProduct(7L);
        when(productservice.getProductByProcedure(7L)).thenReturn(p);

        mockMvc.perform(get("/products/proc/7").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(7));
    }

    @Test
    public void testGetProductDescription() throws Exception {
        when(productservice.getProductDescription(8L)).thenReturn("A description");

        mockMvc.perform(get("/products/description/8").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("A description"));
    }
}

