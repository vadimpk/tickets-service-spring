package com.naukma.ticketsservice.user;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserDto {
    private String email;
    private String password;

    public UserDto(@JsonProperty(value = "email", required = true) String email,
                   @JsonProperty(value = "password", required = true) String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
