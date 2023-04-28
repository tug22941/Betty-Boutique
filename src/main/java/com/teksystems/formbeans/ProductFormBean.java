package com.teksystems.formbeans;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
public class ProductFormBean {

    private Integer id;

    @NotEmpty(message = "Product name is required")
    @Length(max=45, message="Product name can not be longer than 45 characters")
    private String name;

    @NotEmpty(message = "Product description is required")
    @Length(max=100, message="Product description can not be longer than 100 characters")
    private String description;

    @NotNull(message = "Product price is required")
    private Double price;

    @NotEmpty(message = "Product name type required")
    @Length(max=100, message="Product type can not be longer than 45 characters")
    private String productType;

    @NotNull(message = "Product picture is required")
    private MultipartFile picture;

    private String imageUrl;
}
