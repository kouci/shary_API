package fr.ynov.shary.models;

import lombok.Getter;

public class AuthResponse {
    @Getter
    private final String jwt;

    @Getter
    private final Long id;

    public AuthResponse(String jwt, Long id) {
        this.jwt = jwt;
        this.id = id;
    }
}
