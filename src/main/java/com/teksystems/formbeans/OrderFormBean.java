package com.teksystems.formbeans;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@ToString
public class OrderFormBean {

    @NotEmpty(message = "Full Name is required")
    @Length(max=45, message="Full Name name can not be longer than 45 characters")
    @Pattern(regexp="^([a-zA-Z'-.]+ [a-zA-Z'-.]+)$", message="Invalid Name Format")
    private String fullName;

    @NotEmpty(message = "Country name is required")
    @Length(max=45, message="Country name can not be longer than 45 characters")
    @Pattern(regexp="^([a-zA-Z'-.]+ [a-zA-Z'-.]+)$", message="Invalid Country Format")
    private String country;

    @NotEmpty(message = "City is required")
    @Pattern(regexp="^[A-Z][-' a-zA-Z]+$", message="Invalid City Format")
    private String city;

    @NotEmpty(message = "State is required")
    @Pattern(regexp="^[A-Z][-' a-zA-Z]+$", message="Invalid State Format")
    private String state;

    @NotEmpty(message = "ZIP Code is required")
    @Pattern(regexp="^\\d{5}(?:[-\\s]\\d{4})?$", message="Invalid ZIP Code Format")
    private String zipcode;

    @NotEmpty(message = "Address is required")
    @Pattern(regexp="^[#.0-9a-zA-Z\\s,-]+$", message="Invalid Address Format")
    private String addressLine1;

    private String addressLine2;

    @NotEmpty(message = "Card Number is required")
    @Pattern(regexp="\\b(?:\\d[ -]*?){13,16}\\b", message="Invalid Card Number Format")
    private String cardNumber;

    @NotEmpty(message = "Card Name is required")
    @Length(max=45, message="Card Name name can not be longer than 45 characters")
    @Pattern(regexp="^([a-zA-Z'-.]+ [a-zA-Z'-.]+)$", message="Invalid Card Name Format")
    private String cardName;


}
