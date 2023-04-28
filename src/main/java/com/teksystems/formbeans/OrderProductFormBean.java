package com.teksystems.formbeans;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderProductFormBean {

    @Max(value=4, message="Quantity can not be greater than 4")
    @Min(value=1, message="Quantity can not be less than 1")
    private Integer quantity;
}
