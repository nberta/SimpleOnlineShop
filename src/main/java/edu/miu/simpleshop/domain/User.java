package edu.miu.simpleshop.domain;

import edu.miu.simpleshop.domain.enums.Role;

import java.util.Set;

public class User {
    private Long id;
    private String username;
    private String password;
    private String email;
    private Set<Role> roles;

    public User(){}

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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
