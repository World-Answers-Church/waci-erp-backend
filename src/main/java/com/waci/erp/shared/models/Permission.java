package com.waci.erp.shared.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@NoArgsConstructor
@Entity
@Table(name = "permissions")
@Inheritance(strategy = InheritanceType.JOINED)
public class Permission extends BaseEntity {

   private static final long serialVersionUID = -6233936258089900467L;
    @Column(name = "permission_name")
    private String name;
    @Column(name = "description")
    private String description;

    public Permission(final String description) {
        this.description = description;
    }
    
    public Permission(final String name, final String description) {
        this.name = name;
        this.description = description;
    }
    

    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    

    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(final String description) {
        this.description = description;
    }
    
    @Override
    public String toString() {
        if (this.name != null && !this.name.isEmpty()) {
            return this.name;
        }
        return super.toString();
    }
    
    @Override
    public boolean equals(final Object other) {
        return (other instanceof Permission && this.id != 0) ? this.id==(((Permission) other).id) : (other == this);
    }
    
    public static String getObjectNameFromPermisionName(final Permission p) {
        String subString = "";
        if (p.getName().indexOf(95) == -1) {
            return p.getName();
        }
        subString = p.getName().substring(p.getName().indexOf(95) + 1);
        if (subString.indexOf(95) != -1) {
            return subString.substring(subString.indexOf(95) + 1);
        }
        return subString;
    }
}
