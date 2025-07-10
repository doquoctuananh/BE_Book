package com.example.demo.model.DTO;

import com.example.demo.model.Role;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DTOUser {

    @NotBlank(message = "ten dang nhap khong duong de trong")
    @Length(min=6,max=20,message  ="username toi thieu 8 ki tu toi da 20 ki tu")
    private String username;

    @NotBlank(message = "mat khau khong duoc de trong")
    @Length(min = 6, max = 20, message = "password toi thieu 6 ki tu toi da 20 ki tu")
    private String password;

    @Email(message ="chua dung dinh dang email")
    private String email;

    @NotBlank(message = "Address khong duoc de trong")
    private String address;

    private boolean gender;

    @NotBlank(message = "khong duoc de trong")
    @Pattern(regexp ="^0[0-9]{9}$",message = "chua dung dinh dang")
    private String phone;

}
