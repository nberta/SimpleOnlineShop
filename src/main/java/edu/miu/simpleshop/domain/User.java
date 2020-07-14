package edu.miu.simpleshop.domain;

import edu.miu.simpleshop.domain.enums.Role;
//import edu.miu.simpleshop.validation.annotation.ValidPassword;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String username;

    @NotBlank
    //@ValidPassword
    private String password;

    @NotBlank
    private String email;

    private Boolean enabled = true;

    //@NotEmpty
//    @ElementCollection(targetClass = Role.class)
//    @Enumerated(EnumType.STRING)
//    @CollectionTable(name = "user_role")
//    private Set<Role> roles = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @Column(name="authority")
    private Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User(){}

    public User(String username,  String password,  String email, Role role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public Long getId() { return this.id; }

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

    public String getEmail() { return email; }

    public void setEmail(String email) {
        this.email = email;
    }


//    public Set<Role> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(Set<Role> roles) {
//        this.roles = roles;
//    }
}
