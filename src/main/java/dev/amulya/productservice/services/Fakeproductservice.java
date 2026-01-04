package dev.amulya.productservice.services;

import dev.amulya.productservice.dtos.FakeStoreProductDto;
import dev.amulya.productservice.model.Category;
import dev.amulya.productservice.model.Product;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service("fakeproductservice")
public class Fakeproductservice implements Productservice {

    private RestTemplate restTemplate;

    private RedisTemplate<String,Object> redisTemplate;

    public Fakeproductservice(RestTemplate restTemplate,
                              RedisTemplate<String, Object> redisTemplate) {
        this.restTemplate = restTemplate;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Product getSingleProduct(Long id) {
        Product productfromcache=(Product)redisTemplate.opsForValue().get(String.valueOf(id));
        if(productfromcache!=null){
            return productfromcache;
        }
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.getForEntity("https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);
        FakeStoreProductDto fakeStoreProductDto = responseEntity.getBody();
        redisTemplate.opsForValue().set(String.valueOf(id),fakeStoreProductDto.convertToProduct());
        return fakeStoreProductDto.convertToProduct();
    }

    @Override
    public Product createProduct(String title, String description,
                                 String image, String category, double price) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(title);
        fakeStoreProductDto.setDescription(description);
        fakeStoreProductDto.setImage(image);
        fakeStoreProductDto.setCategory(category);
        fakeStoreProductDto.setPrice(price);

        FakeStoreProductDto response = restTemplate.postForObject("https://fakestoreapi.com/products", fakeStoreProductDto, FakeStoreProductDto.class);
        return response.convertToProduct();
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

}
