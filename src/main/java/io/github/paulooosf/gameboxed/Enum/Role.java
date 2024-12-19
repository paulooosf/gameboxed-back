package io.github.paulooosf.gameboxed.Enum;

public enum Role {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_USUARIO("ROLE_USUARIO");

    private String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
