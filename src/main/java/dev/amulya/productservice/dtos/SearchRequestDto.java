package dev.amulya.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchRequestDto {
    String Query;
    int pageNumber;
    int pageSize;

}
