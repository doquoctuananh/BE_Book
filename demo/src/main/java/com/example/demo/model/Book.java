package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.List;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message= "khong duo de trong")
    @Column(name = "book_name")
    private String bookName;

    @Column(columnDefinition = "text")
    @Length(min = 20, max = 100, message = "toi thieu 20 ki tu toi da 100 ki tu")
    private String description;

    @Min(value = 0,message = "gia phai lon hon 0")
    private float price;

    @Min(value = 0, message ="so luong lon hon 0")
    private int quantity;

    @NotBlank(message = "khong duoc de trong")
    private String author;

    @NotBlank(message="khong duoc de trong")
    private String language;

    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    private Type type;

    @OneToMany(mappedBy = "book")
    private List<Cart> listCart;

    @OneToMany(mappedBy ="importBook")
    private List<ImportBook> listImportBooks;

    @OneToMany(mappedBy = "book")
    private List<OrderDetail> listOrderDetails;
}
