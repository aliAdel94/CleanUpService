package com.mondiamedia.cleanup.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import de.arvatomobile.services.user.userservice.client.ExternalUserIdType;

/**
 * An external user ID type can be used to do validation of format {@link ExternalUserIdEntity}.
 */
@Entity
@Table(name = "euitExternalUserIdType")
public class ExternalUserIdTypeEntity {

    /** The external user type id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "euitId", unique = true, nullable = false)
    private Long externalUserTypeId;

    /** The external user type name */
    @Column(name = "euitName", nullable = false, length = 100)

    private String externalUserTypeName;

    /** The external user id type name */
    @Column(name = "euitValidationType", nullable = false, length = 120)

    @Enumerated(EnumType.STRING)
    private ValidationType validationType;

    /**
     * default constructor used by JPA
     */
    public ExternalUserIdTypeEntity() {
        // default constructor
    }

    /**
     * Construct a new ExternalUserIdType object with external user id type and validation strategy
     *
     * @param externalUserIdType external user id type
     * @param validationType validation type
     */
    public ExternalUserIdTypeEntity(final ExternalUserIdType externalUserIdType, final ValidationType validationType) {
        this.externalUserTypeId = externalUserIdType.getTypeId();
        this.externalUserTypeName = externalUserIdType.name();
        this.validationType = validationType;
    }

    /**
     * Gets external user type id
     *
     * @return external user type id
     */
    public Long getExternalUserTypeId() {
        return externalUserTypeId;
    }

    /**
     * Sets external user type id
     *
     * @param externalUserTypeId external user type id
     */
    public void setExternalUserTypeId(final Long externalUserTypeId) {
        this.externalUserTypeId = externalUserTypeId;
    }

    /**
     * Gets external user type name
     *
     * @return external user type name
     */
    public String getExternalUserTypeName() {
        return externalUserTypeName;
    }

    /**
     * Sets external user type name
     *
     * @param externalUserTypeName external user type name
     */
    public void setExternalUserTypeName(final String externalUserTypeName) {
        this.externalUserTypeName = externalUserTypeName;
    }

    /**
     * Gets validation type for external user id type
     *
     * @return validation type for external user id type
     */
    public ValidationType getValidationType() {
        return validationType;
    }

    /**
     * Sets validation type for external user id type
     *
     * @param validationType validation type for external user id type
     */
    public void setExternalValidationClassName(final ValidationType validationType) {
        this.validationType = validationType;
    }

}
