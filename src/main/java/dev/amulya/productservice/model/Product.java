package dev.amulya.productservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.amulya.productservice.services.Fakeproductservice;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product extends BaseModel {
    private String title;
    private String description;
    private Double price;

    private String imageurl;
    @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    private Category category;
}

