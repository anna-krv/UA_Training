package ua.finalproject.periodicals.old.entity;

public enum Role {
    USER,
    ADMIN;          // should not be reordered

    public String getAuthority() {
        return name();
    }
}
