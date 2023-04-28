package com.teksystems.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    //field attributes, with mapping
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name="shipping_date")
    @Temporal(TemporalType.DATE)
    private Date shippingDate;

    @Column(name="order_status")
    private String orderStatus;

    @Column(name="total", columnDefinition="decimal", precision=18, scale=3)
    private Double total;

    @Column(name="card_number")
    private String cardNumber;

    @Column(name="country")
    private String country;

    @Column(name="city")
    private String city;

    @Column(name="state")
    private String state;

    @Column(name="zipcode")
    private String zipcode;

    @Column(name="address_line1")
    private String addressLine1;

    @Column(name="address_line2")
    private String addressLine2;

    @Column(name="receiver")
    private String receiver;

}
