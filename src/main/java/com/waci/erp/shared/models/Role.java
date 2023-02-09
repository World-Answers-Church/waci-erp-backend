/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.waci.erp.shared.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Data
@NoArgsConstructor
@Entity
@Table(name = "roles")
@Inheritance(strategy = InheritanceType.JOINED)
public class Role extends BaseEntity {

    public static final String SUPER_ADMIN_ROLE = "Super Admin";
    public static final String ADMIN_ROLE = "Admin";
    public static final String STORE_MANAGER = "Store Manager";
    public static final String CUSTOMER = "Customer";
    @Column(name = "role_name", nullable = false)
    private String name;
    @Column(name = "description", nullable = false)
    private String description;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_permissions", joinColumns = {
            @JoinColumn(name = "role_id")}, inverseJoinColumns = {
            @JoinColumn(name = "permission_id")})
    private Set<Permission> permissions;
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;


    public Role(final String name, final String description, final Set<Permission> permissions) {
        this.name = name;
        this.description = description;
        this.permissions = permissions;

    }

    public Role(final String name, final String description) {
        this.name = name;
        this.description = description;

    }


    public void addPermission(final Permission permission) {
        this.getPermissions().add(permission);
    }

    public void removePermission(final Permission permission) {
        if (this.getPermissions().contains(permission)) {
            this.getPermissions().remove(permission);
        }
    }


    public Set<User> getUsers() {
        return this.users;
    }

    public void setUsers(final Set<User> users) {
        this.users = users;
    }

    public void addUser(final User user) {
        if (this.users == null) {
            this.users = new HashSet<User>();
        }
        if (user != null && !this.users.contains(user)) {
            this.users.add(user);
            user.addRole(this);
        }
    }

    public void removeUser(final User user) {
        if (user == null || this.users == null || this.users.size() == 0) {
            return;
        }
        if (this.getUsers().contains(user)) {
            this.getUsers().remove(user);
            user.removeRole(this);
        }
    }

    public boolean checkIfDefaultAdminRole() {
        return this.getName().equals("ROLE_ADMINISTRATOR");
    }

    @Override
    public String toString() {
        if (this.getName() != null && this.getName().trim().length() > 0) {
            return this.getName();
        }
        return super.toString();
    }

    @Override
    public boolean equals(final Object other) {
        return (other instanceof Role && this.id != 0) ? this.id==(((Role) other).id) : (other == this);
    }

    @Override
    public int hashCode() {
        return (this.id != 0) ? (int) (this.getClass().hashCode() + this.id) : super.hashCode();
    }

    public boolean hasPermission(final String perm) {
        if (this.permissions != null) {
            for (final Permission permission : this.permissions) {
                if (permission.getName().equalsIgnoreCase(perm)) {
                    return true;
                }
            }
        }
        return false;
    }
}
