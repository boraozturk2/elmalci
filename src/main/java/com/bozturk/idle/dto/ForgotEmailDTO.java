package com.bozturk.idle.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by daveburke on 8/8/16.
 */
public class ForgotEmailDTO implements Serializable {
    private static final long serialVersionUID = -934031990312257019L;

    @NotEmpty
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
