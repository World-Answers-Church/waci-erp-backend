/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.waci.erp.shared.models;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * @author RayGdhrt
 */
public interface Auditable extends Serializable {


    public long getCreatedById();

    public void setCreatedById(long createdById);

    public long getChangedById();

    public void setChangedById(long changedById);

    public String getCreatedByUsername() ;

    public void setCreatedByUsername(String createdByUsername) ;

    public String getChangedByUsername() ;

    public void setChangedByUsername(String changedByUsername) ;

    public LocalDateTime getDateCreated();

    public void setDateCreated(LocalDateTime date);

    public LocalDateTime getDateChanged();

    public void setDateChanged(LocalDateTime date);
}
