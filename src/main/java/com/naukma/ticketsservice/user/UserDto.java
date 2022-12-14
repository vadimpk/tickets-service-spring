package com.naukma.ticketsservice.user;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class UserDto {

    private Long id;
    @NotNull
    @NotBlank
//    @Email
    private String email;
    @NotNull
    @NotBlank
    private String password;
    @NotNull
    @NotBlank
    private String firstName;
    @NotNull
    @NotBlank
    private String lastName;

    public UserDto(@JsonProperty(value = "email", required = true) String email,
                   @JsonProperty(value = "password", required = true) String password,
                   @JsonProperty(value = "first_name", required = true) String firstName,
                   @JsonProperty(value = "last_name", required = true) String lastName) {
        this.email = email;
        this.password = password;
    }

    public UserDto() {

    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}