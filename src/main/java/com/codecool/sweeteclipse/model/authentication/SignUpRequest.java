package com.codecool.sweeteclipse.model.authentication;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SignUpRequest implements Serializable {

    @NotBlank(message = "User name is mandatory")
    private String username;

    @NotBlank(message = "Password is mandatory")
    @Length(min = 8, max = 255, message = "Password length must be between {min} and {max} characters")
    private String password;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email field must be a well-formed email address")
    private String email;


    // Constructors
    public SignUpRequest() {
        // empty constructor needed for JSON serialization
    }

    public SignUpRequest(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }


    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "SignUpRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
