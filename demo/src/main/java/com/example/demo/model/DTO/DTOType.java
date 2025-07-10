package com.example.demo.model.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DTOType {
    @NotBlank(message = "khong duoc de trong")
    @Length(min = 6,message = "toi thieu 6 ki tu")
    private String typeName;
}
