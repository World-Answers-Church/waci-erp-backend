package com.waci.erp.shared.models;

import com.waci.erp.shared.constants.RecordStatus;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 *
 * @author RayGdhrt
 */
@MappedSuperclass
public class BaseEntity implements Auditable {

    private static final long serialVersionUID = 6095671201979163425L;

    @Id
    @GeneratedValue
    protected long id;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "record_status", nullable = false)
    private RecordStatus recordStatus;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = true)
    private User createdBy;
    @ManyToOne
    @JoinColumn(name = "changed_by", nullable = true)
    private User changedBy;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_created", nullable = true)
    private Date dateCreated;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_changed", nullable = true)
    private Date dateChanged;

    @Column(name = "custom_prop_one")
    private String customPropOne;

    public BaseEntity() {
        this.id = 0;
        this.recordStatus = RecordStatus.ACTIVE;
    }

    @PrePersist
    public void prePersist() {
        this.dateCreated = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        this.dateChanged = new Date();
    }


    public long getId() {
        return this.id;
    }

    public void setId(final long id) {
        this.id = id;
    }


    public RecordStatus getRecordStatus() {
        return this.recordStatus;
    }

    public void setRecordStatus(final RecordStatus recordStatus) {
        this.recordStatus = recordStatus;
    }


    @Override
    public User getCreatedBy() {
        return this.createdBy;
    }

    @Override
    public void setCreatedBy(final User userAccount) {
        this.createdBy = userAccount;
    }


    @Override
    public Date getDateCreated() {
        return this.dateCreated;
    }

    @Override
    public void setDateCreated(final Date dateCreated) {
        this.dateCreated = dateCreated;
    }


    @Override
    public User getChangedBy() {
        return this.changedBy;
    }

    @Override
    public void setChangedBy(final User changedBy) {
        this.changedBy = changedBy;
    }


    @Override
    public Date getDateChanged() {
        return this.dateChanged;
    }

    @Override
    public void setDateChanged(final Date dateChanged) {
        this.dateChanged = dateChanged;
    }


    public String getCustomPropOne() {
        return this.customPropOne;
    }

    public void setCustomPropOne(final String customPropOne) {
        this.customPropOne = customPropOne;
    }

    @Override
    public String toString() {
        return "Equals not implemented";
    }

    @Transient
    public boolean isNew() {
        return this.id == 0;
    }

    @Transient
    public boolean isSaved() {
        return !this.isNew();
    }
}
