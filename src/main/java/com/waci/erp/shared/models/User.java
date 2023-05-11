/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.waci.erp.shared.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.waci.erp.shared.constants.Gender;
import com.waci.erp.shared.constants.PermissionConstant;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;
@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @JsonIgnore
    @Column(name = "password", nullable = false)
    private String password;

    @JsonIgnore
    @Column(name = "api_password")
    private String apiPassword;



    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_users", joinColumns = {
            @JoinColumn(name = "user_id")}, inverseJoinColumns = {
            @JoinColumn(name = "role_id")})
    private Set<Role> roles;

    @JsonIgnore
    private String clearTextPassword;
    @JsonIgnore
    @Column(name = "salt", nullable = false)
    private String salt = "RAYGDHRT";

    @Column(name = "email_address", nullable = true)
    private String emailAddress;

    @Column(name = "last_name", nullable = true)
    private String lastName;

    @Column(name = "first_name", nullable = true)
    private String firstName;
    @Column(name = "gender", nullable = true)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "phone_number", nullable = true)
    private String phoneNumber;
    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_last_password_change", nullable = true)
    private Date dateOfLastPasswordChange;
    private boolean changePassword;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne
    @JoinColumn(name = "country_id", nullable = true)
    private Country country;
    @Column(name = "api_locked", columnDefinition = "bit default 0")
    private boolean apiLocked;
    @Column(name = "api_token_change_locked", columnDefinition = "bit default 0")
    private boolean apiTokenChangeLocked;

    public User(final String username, final String password, final Set<Role> roles, final String clearTextPassword, final String salt) {
        this.apiLocked = false;
        this.apiTokenChangeLocked = false;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.clearTextPassword = clearTextPassword;
        this.salt = salt;
    }

    public User(final String username, final String password) {
        this.apiLocked = false;
        this.apiTokenChangeLocked = false;
        this.username = username;
        this.password = password;

    }

    public User(String username, String password, Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    @Transient
    public String getClearTextPassword() {
        return this.clearTextPassword;
    }


    @Transient
    public boolean isChangePassword() {
        return this.changePassword;
    }

    public void addRole(final Role role) {
        if (this.roles == null) {
            this.roles = new HashSet<Role>();
        }
        if (!this.roles.contains(role)) {
            this.roles.add(role);
        }
    }

    public void removeRole(final Role role) {
        if (this.roles != null) {
            for (final Role r : this.roles) {
                if (r.getName().equals(role.getName())) {
                    this.roles.remove(role);
                    break;
                }
            }
        }
    }




    public boolean hasNewPassword() {
        return this.clearTextPassword != null && this.clearTextPassword.trim().length() > 0;
    }


    public List<PermissionConstant> findPermissions() {
        List<PermissionConstant> permissions = null;
        if (this.roles != null && !this.roles.isEmpty()) {
            permissions = new ArrayList<>();
            for (final Role role : this.roles) {
                if (role.getPermissions() != null && !role.getPermissions().isEmpty()) {
                    permissions.addAll(role.getPermissions());
                }
            }
        }
        return permissions;
    }

    public boolean hasAdministrativePrivileges() {
        if (this.roles != null) {
            for (final Role role : this.roles) {
                if (role.checkIfDefaultAdminRole()) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        if (this.lastName != null && this.firstName != null) {
            return this.firstName + " " + this.lastName;
        }
        if (this.username != null) {
            return this.username;
        }
        return super.toString();
    }

    @Transient
    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    public boolean hasPermission(final PermissionConstant perm) {
        if (this.hasAdministrativePrivileges()) {
            return true;
        }
        if (this.roles != null) {
            for (final Role role : this.roles) {
                if (role.hasPermission(perm)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hasRole(final String roleName) {
        if (this.hasAdministrativePrivileges()) {
            return true;
        }
        if (this.roles != null) {
            for (final Role role : this.roles) {
                if (role.getName().equals(roleName)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean equals(final Object other) {
        return (other instanceof User && this.id != 0) ? this.id==(((User) other).id) : (other == this);
    }

    @Override
    public int hashCode() {
        return (this.id != 0) ? (int) (this.getClass().hashCode() + this.id) : super.hashCode();
    }
}
