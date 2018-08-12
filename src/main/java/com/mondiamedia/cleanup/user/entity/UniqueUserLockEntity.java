/*
 * Copyright 2017 Mondia Media Group GmbH. All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Mondia Media Group GmbH ("Confidential Information").
 * You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement
 * you entered into with Mondia Media Group GmbH.
 */
package com.mondiamedia.cleanup.user.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


/**
 * A UniqueUesrLock is created for locking creation of users, it deleted right after user creation.
 *
 * @author pair1
 */
@Entity
@Table(name = "uulUniqueUserLock", uniqueConstraints = { @UniqueConstraint(columnNames = { "uulAffiliateId", "uulEuitId", "uulUserId" }) })
public class UniqueUserLockEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uulId", unique = true, nullable = false)
    private Long id;

    /** The affiliate id. */
    @Column(name = "uulAffiliateId", nullable = false)

    private Long affiliateId;

    /** The application id. */
    @Column(name = "uulApplicationId", nullable = true)
    private Long applicationId;

    /** The external user id type id. */
    @Column(name = "uulEuitId", nullable = false)

    private Long externalUserIdTypeId;

    @Column(name = "uulUserId", nullable = false, length = 255)

    private String userId;

    /** The created. */
    @Column(name = "uulCreated", nullable = false)

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    /**
     * Default constructor.
     */
    public UniqueUserLockEntity() {
        super();
        this.created = new Date();
    }

    /**
     * Create unique user lock for user and external id
     *
     * @param user user
     * @param externalId and his external id to lock
     */
    public UniqueUserLockEntity(final UserEntity user, final ExternalUserIdEntity externalId) {
        this.affiliateId = user.getAffiliateId();
        this.userId = externalId.getUserId();
        this.externalUserIdTypeId = externalId.getExternalUserIdType().getTypeId();
        this.created = new Date();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UniqueUserLockEntity other = (UniqueUserLockEntity) obj;
        if (!ObjectUtils.equals(userId, other.userId)) {
            return false;
        }
        if (!ObjectUtils.equals(externalUserIdTypeId, other.externalUserIdTypeId)) {
            return false;
        }
        if (!ObjectUtils.equals(affiliateId, other.affiliateId)) {
            return false;
        }
        return true;
    }

    /**
     * Gets the affiliate id.
     *
     * @return the affiliate id
     */
    public Long getAffiliateId() {
        return this.affiliateId;
    }

    /**
     * Gets the application id.
     *
     * @return the application id
     */
    public Long getApplicationId() {
        return this.applicationId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int applicationIdHashCode = applicationId == null ? 0 : applicationId.hashCode();
        final int affiliateIdHashCode = affiliateId == null ? 0 : affiliateId.hashCode();
        final int userIdHashCode = userId == null ? 0 : userId.hashCode();
        final int externalUserIdTypeIdHashCode = externalUserIdTypeId == null ? 0 : externalUserIdTypeId.hashCode();

        return applicationIdHashCode ^ affiliateIdHashCode ^ userIdHashCode ^ externalUserIdTypeIdHashCode;
    }

    /**
     * Sets the affiliate id.
     *
     * @param affiliateId the new affiliate id
     */
    public void setAffiliateId(final Long affiliateId) {
        this.affiliateId = affiliateId;
    }

    /**
     * Sets the application id.
     *
     * @param applicationId the new application id
     */
    public void setApplicationId(final Long applicationId) {
        this.applicationId = applicationId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    /**
     * Gets the user id.
     *
     * @return the user id
     */
    public String getUserId() {
        return this.userId;
    }

    /**
     * Sets the user id.
     *
     * @param userId the new user id
     */
    public void setUserId(final String userId) {
        this.userId = userId;
    }

    public Long getExternalUserIdTypeId() {
        return externalUserIdTypeId;
    }

    public void setExternalUserIdTypeId(final Long externalUserIdTypeId) {
        this.externalUserIdTypeId = externalUserIdTypeId;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(final Date created) {
        this.created = created;
    }
}
