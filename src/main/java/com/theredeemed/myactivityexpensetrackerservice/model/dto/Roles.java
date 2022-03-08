package com.theredeemed.myactivityexpensetrackerservice.model.dto;

public enum Roles {
    ADMIN("Admin"),
    USER("User");

    private final String roleName;

    Roles(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}
