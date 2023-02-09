/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.waci.erp.shared.models;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author RayGdhrt
 */
public interface Auditable extends Serializable {
    public User getCreatedBy();

    public void setCreatedBy(User user);

    public Date getDateCreated();

    public void setDateCreated(Date date);

    public User getChangedBy();

    public void setChangedBy(User user);

    public Date getDateChanged();

    public void setDateChanged(Date date);
}
