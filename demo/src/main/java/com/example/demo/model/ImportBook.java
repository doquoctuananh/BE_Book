package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Entity

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImportBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "khong duoc de trong")
    @Length(min = 6,message = "toi thieu 6 ki tu")
    private String name;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate importDate;

    @Min(value = 0,message = "Gia lon hon 0")
    @Column(name = "import_price")
    private float importPrice;

    @Min(value = 0,message = "so luong lon hon 0")
    @Column(name = "import_quantity")
    private float importQuantity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    @JsonManagedReference
    private Book importBook;
}
