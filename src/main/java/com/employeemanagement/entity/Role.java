package com.employeemanagement.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ROLE")
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ROLE_NAME", nullable = false)
    private String roleName;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UsersRoles> userRoles = new HashSet<>();

    public Role() {
    }

    public Role(Long id, String roleName, Set<UsersRoles> userRoles) {
        this.id = id;
        this.roleName = roleName;
        this.userRoles = userRoles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<UsersRoles> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UsersRoles> userRoles) {
        this.userRoles = userRoles;
    }
}
