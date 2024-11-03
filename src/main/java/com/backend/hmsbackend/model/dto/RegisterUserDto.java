package com.backend.hmsbackend.model.dto;

import lombok.Data;

@Data

public class RegisterUserDto {

    private String username;
    private String email;
    private String password;
}
