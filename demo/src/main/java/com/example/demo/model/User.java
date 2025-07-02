package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "ten dang nhap khong duong de trong")
    @Length(min=8,max=20,message  ="username toi thieu 8 ki tu toi da 20 ki tu")
    private String username;

    @NotBlank(message = "mat khau khong duoc cde trong")
//    @Length(min = 6, max = 20, message = "password toi thieu 6 ki tu toi da 20 ki tu")
    private String password;

    @Email(message ="chua dung dinh dang email")
    private String email;

    @NotBlank(message = "khong duoc de trong")
    private String address;

    private boolean gender;

    @NotBlank(message = "khong duoc de trong")
    @Pattern(regexp ="^0[0-9]{9}$",message = "chua dung dinh dang")
    private String phone;

    private String status = "active";

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy="user",cascade=CascadeType.ALL,orphanRemoval = true)
    private List<Cart> listCart;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Orders> listOrder;
}
