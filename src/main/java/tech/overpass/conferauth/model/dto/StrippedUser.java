package tech.overpass.conferauth.model.dto;

import tech.overpass.conferauth.model.db.User;

public class StrippedUser {

    private Long id;
    private String email;
    private String fullName;
    private String username;

    public StrippedUser(Long id, String email, String fullName, String username) {
        this.id = id;
        this.email = email;
        this.fullName = fullName;
        this.username = username;
    }

    public StrippedUser(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.fullName = user.getFullName();
        this.username = user.getUsername();
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }

    public String getUsername() {
        return username;
    }
}
