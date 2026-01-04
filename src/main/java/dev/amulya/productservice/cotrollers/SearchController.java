package dev.amulya.productservice.cotrollers;

import dev.amulya.productservice.dtos.SearchRequestDto;
import dev.amulya.productservice.model.Product;
import dev.amulya.productservice.services.SearchService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {

    SearchService searchService;

    SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @PostMapping("/search")
    public Page<Product>search(@RequestBody   SearchRequestDto searchRequestDto) {
        return searchService.searchProducts(
                searchRequestDto.getQuery(),
                searchRequestDto.getPageNumber(),
                searchRequestDto.getPageSize());
    }
}
