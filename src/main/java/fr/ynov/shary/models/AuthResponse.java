package fr.ynov.shary.models;

import lombok.Getter;

public class AuthResponse {
    @Getter
    private final String jwt;

    public AuthResponse(String jwt) {
        this.jwt = jwt;
    }
}
