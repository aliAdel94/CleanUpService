package com.mondiamedia.cleanup.user.entity;

import static javax.persistence.CascadeType.REMOVE;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.ws.rs.core.EntityTag;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.lang.time.DateUtils;

import de.arvatomobile.services.user.userservice.client.ExternalUserIdType;


/**
 * An external user ID can be used to attach third-party or external identification tokens to a {@link UserEntity}.
 *
 * @author jtammen
 */
@Entity
@Table(name = "euiExternalUserId")
public class ExternalUserIdEntity {

    /** The external user id id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "euiId", unique = true, nullable = false)
    private Long externalUserIdId;

    /** The created. */
    @Column(name = "euiCreated", nullable = false)

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    /** The last modified. */
    @Column(name = "euiLastModified", nullable = false)

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;

    /** The user id. */
    @Column(name = "euiUserId", nullable = false, length = 255)

    private String userId;

    /** The external user id type. */
    @Transient
    private ExternalUserIdType externalUserIdType;

    /** The external user id type id. */
    @Column(name = "euiEuitId", nullable = false)

    private Long externalUserIdTypeId;

    // bi-directional many-to-one association to User
    /** The user. */
    @ManyToOne
    @JoinColumn(name = "euiUsrId", nullable = false)
    private UserEntity user;

    /** The valid until. */
    @Column(name = "euiValidUntil")
    @Temporal(TemporalType.TIMESTAMP)
    private Date validUntil;

    /** The login info. */
    @OneToOne(optional = true, fetch = FetchType.EAGER, mappedBy = "externalUserId", cascade = REMOVE)
    private LoginInfoEntity loginInfo;

    /** The validation result */
    @Transient
    private Boolean isValid;

    /**
     * Create a HTTP ETag for the given external user ID composed of the entity's ID and last modification date.
     *
     * @param externalUserId The external user ID.
     * @return A HTTP ETag for the given entity.
     */
    public static EntityTag getEntityTag(final ExternalUserIdEntity externalUserId) {
        return new EntityTag(String.format("%s-%d", externalUserId.getExternalUserIdId(), externalUserId.getLastModified() == null ? externalUserId
                .getCreated().getTime() : externalUserId.getLastModified().getTime()));
    }

    /**
     * Default constructor.
     */
    public ExternalUserIdEntity() {
        super();
    }

    /**
     * Construct an external user ID with the given external user ID type and external user ID.
     *
     * @param externalUserIdType The type of the external user ID.
     * @param userId The external user ID.
     */
    public ExternalUserIdEntity(final ExternalUserIdType externalUserIdType, final String userId) {
        // TODO format and/or validate the userId? e.g. MSIDN should be stored in E164 format
        this.externalUserIdType = externalUserIdType;
        this.userId = userId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        return EqualsBuilder.reflectionEquals(this, o);
    }

    /**
     * Gets the created.
     *
     * @return the created
     */
    public Date getCreated() {
        return this.created;
    }

    /**
     * Gets the external user id id.
     *
     * @return the external user id id
     */
    public Long getExternalUserIdId() {
        return this.externalUserIdId;
    }

    /**
     * Gets the external user id type.
     *
     * @return the external user id type
     */
    public ExternalUserIdType getExternalUserIdType() {
        return this.externalUserIdType;
    }

    /**
     * Gets the last modified.
     *
     * @return the last modified
     */
    public Date getLastModified() {
        return this.lastModified;
    }

    /**
     * Gets the user.
     *
     * @return the user
     */
    public UserEntity getUser() {
        return this.user;
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
     * Gets the valid until.
     *
     * @return the valid until
     */
    public Date getValidUntil() {
        return validUntil;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return getUserId() != null ? getUserId().hashCode() : 0;
    }

    /**
     * Sets the external user id type according to the ID.
     */
    @PostLoad
    private void postLoad() {
        setExternalUserIdType(ExternalUserIdType.getById(externalUserIdTypeId));
    }

    /**
     * Sets the external user ID according to the type and the creation and last modification date to now.
     */
    @PrePersist
    private void prePersist() {
        externalUserIdTypeId = externalUserIdType.getTypeId();
        final Date now = DateUtils.truncate(new Date(), Calendar.SECOND);
        setCreated(now);
        setLastModified(now);
    }

    /**
     * Sets the external user ID according to the type and the last modification date to now.
     */
    @PreUpdate
    private void preUpdate() {
        externalUserIdTypeId = externalUserIdType.getTypeId();
        final Date now = DateUtils.truncate(new Date(), Calendar.SECOND);
        setLastModified(now);
    }

    /**
     * Sets the created.
     *
     * @param created the new created
     */
    public void setCreated(final Date created) {
        this.created = created;
    }

    /**
     * Sets the external user id id.
     *
     * @param id the new external user id id
     */
    public void setExternalUserIdId(final Long id) {
        this.externalUserIdId = id;
    }

    /**
     * Sets the external user id type.
     *
     * @param externalUserIdType the new external user id type
     */
    public void setExternalUserIdType(final ExternalUserIdType externalUserIdType) {
        this.externalUserIdType = externalUserIdType;
    }

    /**
     * Sets the last modified.
     *
     * @param lastModified the new last modified
     */
    public void setLastModified(final Date lastModified) {
        this.lastModified = lastModified;
    }

    /**
     * Sets the user.
     *
     * @param user the new user
     */
    public void setUser(final UserEntity user) {
        this.user = user;
    }

    /**
     * Sets the user id.
     *
     * @param userId the new user id
     */
    public void setUserId(final String userId) {
        this.userId = userId;
    }

    /**
     * Sets the valid until.
     *
     * @param validUntil the new valid until
     */
    public void setValidUntil(final Date validUntil) {
        this.validUntil = validUntil;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    /**
     * Sets the login info.
     *
     * @param loginInfo the loginInfo to set
     */
    public void setLoginInfo(final LoginInfoEntity loginInfo) {
        this.loginInfo = loginInfo;
    }

    /**
     * Gets the login info.
     *
     * @return the loginInfo
     */
    public LoginInfoEntity getLoginInfo() {
        return loginInfo;
    }

    /**
     * Gets validation result
     *
     * @return true if external user id is valid
     */
    public Boolean getIsValid() {
        return isValid;
    }

    /**
     * Sets validation result
     *
     * @param isValid true if external user id is valid
     */
    public void setIsValid(final Boolean isValid) {
        this.isValid = isValid;
    }

    public Long getExternalUserIdTypeId() {
        return externalUserIdTypeId;
    }

}
