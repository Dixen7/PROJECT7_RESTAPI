package com.nnk.springboot.domain.DTO;

import com.nnk.springboot.validation.ValidPassword;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserDTO {

    private Integer Id;
    @NotBlank(message = "Username is mandatory")
    private String username;
    @ValidPassword
    @NotBlank(message = "Password is mandatory")
    private String password;
    @NotBlank(message = "Full name is mandatory")
    private String fullname;
    @NotBlank(message = "Role is mandatory")
    private String role;


    public UserDTO(String username, String password, String fullname, String role) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.role = role;
    }

    public UserDTO() {
    }
}
