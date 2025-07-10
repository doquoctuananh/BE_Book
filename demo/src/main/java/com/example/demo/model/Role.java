package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message= "Khong duoc de trong")
    private String name;

    @OneToMany(mappedBy="role",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonManagedReference
    private List<User> listUser;


}
