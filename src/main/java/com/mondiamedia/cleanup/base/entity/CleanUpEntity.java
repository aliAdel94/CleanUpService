package com.mondiamedia.cleanup.base.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Ali.Ismail
 */
@Entity(name = "CleanUpEntity")
@Table(name = "cleanup")
public class CleanUpEntity {

    /** The Unique user id per identity. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", unique = true, nullable = false)
    private Long Id;

    /** The user id. */
    @Column(name = "userId", nullable = false)
    private Long userId;

    /** The affiliate id. */
    @Column(name = "affiliateId", nullable = false)
    private Long affiliateId;

    /** The external user type. */
    @Column(name = "externalUserTypeId", nullable = false)
    private Long externalUserTypeId;

    /** The external user type value. */
    @Column(name = "externalUserTypeValue", nullable = false, length = 255)
    private String externalUserTypeValue;

    @Override
    public int hashCode() {
        return new String(getAffiliateId() + "-" + getExternalUserTypeId() + "-" + getExternalUserTypeValue()).hashCode();
    }

    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof CleanUpEntity) || obj == null) {
            return false;
        }
        final CleanUpEntity cleanUpEntity = (CleanUpEntity) obj;

        return cleanUpEntity.getAffiliateId().equals(getAffiliateId())//
                && cleanUpEntity.getExternalUserTypeId().equals(getExternalUserTypeId())//
                && cleanUpEntity.getExternalUserTypeValue().equals(getExternalUserTypeValue());

    }

    /**
     * @return the id
     */
    public Long getId() {
        return Id;
    }

    /**
     * @return the userId
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @return the affiliateId
     */
    public Long getAffiliateId() {
        return affiliateId;
    }

    /**
     * @return the externalUserType
     */
    public Long getExternalUserTypeId() {
        return externalUserTypeId;
    }

    /**
     * @return the externalUserTypeValue
     */
    public String getExternalUserTypeValue() {
        return externalUserTypeValue;
    }

    /**
     * @param id the id to set
     */
    public void setId(final Long id) {
        Id = id;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(final Long userId) {
        this.userId = userId;
    }

    /**
     * @param affiliateId the affiliateId to set
     */
    public void setAffiliateId(final Long affiliateId) {
        this.affiliateId = affiliateId;
    }

    /**
     * @param externalUserType the externalUserType to set
     */
    public void setExternalUserType(final Long externalUserTypeId) {
        this.externalUserTypeId = externalUserTypeId;
    }

    /**
     * @param externalUserTypeValue the externalUserTypeValue to set
     */
    public void setExternalUserTypeValue(final String externalUserTypeValue) {
        this.externalUserTypeValue = externalUserTypeValue;
    }

}
