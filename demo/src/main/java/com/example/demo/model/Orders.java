package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDateTime orderDate;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDateTime deliverDate;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String orderStatus ="pending";

    private float totalPrice;

    private String paymentMethod;

    @OneToMany(mappedBy = "orders")
    private List<OrderDetail> listOrderDetails;
}
