package org.example.dung_s_spring_boot.dto;

public class UserResponse {
    public Long id;
    public String username;
    public String fullName;
    public String role;
    public Boolean enabled;

    public UserResponse() {}

    public UserResponse(Long id, String username, String fullName, String role, Boolean enabled) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.role = role;
        this.enabled = enabled;
    }
}
