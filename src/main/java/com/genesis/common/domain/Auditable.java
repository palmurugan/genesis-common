package com.genesis.common.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * @author PalMurugan C
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable<U> {

    private static final String ACTIVE = "A";

    @CreatedBy
    @Column(name = "CREATED_BY", nullable = false)
    @JsonIgnore
    private U createdBy;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_DATE", insertable = true, updatable = false)
    @JsonIgnore
    private Date createdDate;

    @LastModifiedBy
    @Column(name = "UPDATED_BY")
    @JsonIgnore
    private U updatedBy;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATED_DATE", insertable = false, updatable = true)
    @JsonIgnore
    private Date updatedDate;

    @Column(name = "STATUS", nullable = false)
    private String status = ACTIVE;

    @PreUpdate
    public void preUpdate() {
        this.createdDate = new Date();
        this.createdBy = (U) "Admin";
        this.updatedBy = (U) "Admin";
        this.updatedDate = new Date();
    }

    public U getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(U createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public U getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(U updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
