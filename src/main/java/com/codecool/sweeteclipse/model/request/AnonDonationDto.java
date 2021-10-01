package com.codecool.sweeteclipse.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AnonDonationDto implements Serializable {

    @NotNull
    @Positive
    private Double amount;

    @NotNull
    @Positive
    private Long projectId;

//    @NotBlank
    @Email
    private String email;

    public AnonDonationDto() {
        // empty constructor needed for JSON serialization
    }

    public AnonDonationDto(Double amount, @NotNull Long projectId) {
        this.amount = amount;
        this.projectId = projectId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public @NotNull Long getProjectId() {
        return projectId;
    }

    public void setProjectId(@NotNull Long projectId) {
        this.projectId = projectId;
    }

    public String getEmail() {
        return email;
    }

    public String getEmailOrDefault() {
        return (email != null) ? email : "test@test.com";
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
