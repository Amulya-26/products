package dev.amulya.productservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.amulya.productservice.services.Fakeproductservice;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.FetchMode;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@NamedStoredProcedureQuery(
        name = "Product.getDescription",              // JPA name
        procedureName = "get_product_description",    // DB procedure name

        parameters = {
                @StoredProcedureParameter(
                        mode = ParameterMode.IN,
                        name = "p_id",
                        type = Long.class
                ),
                @StoredProcedureParameter(
                        mode = ParameterMode.OUT,
                        name = "p_description",
                        type = String.class
                )
        }
)
@Entity public class Product extends BaseModel implements Serializable {
    private String title;
    private String description;
    private Double price;

    private String imageurl;
    @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    private Category category;
}

