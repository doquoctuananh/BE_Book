package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.List;
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "khong duoc de trong")
    @Length(min = 8,message = "toi thieu 8 ki tu")
    @Column(name = "type_name")
    private String typeName;

    @OneToMany(mappedBy = "type",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Book> listBooks;
}
