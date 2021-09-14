package com.codecool.sweeteclipse.model.user;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@MappedSuperclass
public abstract class AbstractAncestorOfUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected String name;

    @ElementCollection(targetClass = UserRole.class, fetch = FetchType.EAGER)
    @CollectionTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id")
    )
    @Column(name = "role_id")
    protected Set<UserRole> roles;


    public AbstractAncestorOfUser() {
        this.roles = new HashSet<>(List.of(UserRole.USER));
    }

    public AbstractAncestorOfUser(Long id, String name, Set<UserRole> roles) {
        this.id = id;
        this.name = name;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    // custom functions

    public void addRole(UserRole newRole) {
        this.roles.add(newRole);
    }

    public void removeRole(UserRole userRole) {
        this.roles.remove(userRole);
    }
}
