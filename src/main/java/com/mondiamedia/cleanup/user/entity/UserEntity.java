package com.mondiamedia.cleanup.user.entity;

import static javax.persistence.CascadeType.REMOVE;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.ws.rs.core.EntityTag;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.lang.time.DateUtils;


/**
 * A User is identified by Affiliate ID and Application ID and can have several additional external user IDs.
 *
 * @author jtammen
 */
@Entity
@Table(name = "usrUser")
public class UserEntity {

    /** The user id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usrId", unique = true, nullable = false)
    private Long userId;

    /** The affiliate id. */
    @Column(name = "usrAffiliateId", nullable = false)
    private Long affiliateId;

    /** The application id. */
    @Column(name = "usrApplicationId", nullable = true)
    private Long applicationId;

    /** The created. */
    @Column(name = "usrCreated", nullable = false)

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    /** The last activity. */
    @Column(name = "usrLastActivity", nullable = false)

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastActivity;

    /** The last modified. */
    @Column(name = "usrLastModified", nullable = false)

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;

    /** The last time when user has deleted attribute/s **/
    @Column(name = "usrAttributeLastDeletion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date attributeLastDeletion;

    /** The valid until. */
    @Column(name = "usrValidUntil")
    @Temporal(TemporalType.TIMESTAMP)
    private Date validUntil;

    /** The status. */
    @Column(name = "usrStatus", nullable = false)

    private byte status;

    /** The external user ids. */
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = REMOVE)
    private List<ExternalUserIdEntity> externalUserIds = new ArrayList<>();

    /** The deactivation flag. Will be set on user deletion. */
    @Column(name = "usrDeactivated")
    private Boolean deactivated;

    /** The deactivation date. */
    @Column(name = "usrDeactivatedOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deactivatedOn;

    // @OneToMany(mappedBy = "user", cascade = REMOVE)
    /** The user attributes. */
    @Transient
    private List<UserAttributeValueEntity> userAttributes = new ArrayList<>();

    /**
     * Default constructor.
     */
    public UserEntity() {
        super();
    }

    /**
     * Construct a new User object with Affiliate ID and Application ID.
     *
     * @param affiliateId The Affiliate ID.
     * @param applicationId The Application ID.
     */
    public UserEntity(final Long affiliateId, final Long applicationId) {
        this.affiliateId = affiliateId;
        this.applicationId = null;
    }

    /**
     * Construct a new User object with Affiliate ID.
     *
     * @param affiliateId The Affiliate ID.
     */
    public UserEntity(final Long affiliateId) {
        this.affiliateId = affiliateId;
    }
    /**
     * Create a HTTP ETag for the given user composed of the entity's ID and last modification date.
     *
     * @param user The user.
     * @return A HTTP ETag for the given entity.
     */
    public static EntityTag getEntityTag(final UserEntity user) {
        return new EntityTag(String.format("%s-%d", user.getUserId(), user.getLastModified() == null ? user.getCreated().getTime() : user.getLastModified()
                .getTime()));
    }

    /**
     * Helper method that converts the given list of external user IDs to a multi valued map.
     *
     * @param externalUserIds List of external user IDs that should be converted to a map.
     * @return A map with external user ID types as keys and external user IDs as values.
     */
    public static ExternalUserIdTypeToUserIdMap getExternalUserIdMap(final List<ExternalUserIdEntity> externalUserIds) {
        final StringBuilder stringBuilder = new StringBuilder();
        for (final ExternalUserIdEntity externalUserId : externalUserIds) {
            stringBuilder.append(externalUserId.getExternalUserIdType()).append(":").append(externalUserId.getUserId()).append(",");
        }
        if (stringBuilder.length() > 0) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        return new ExternalUserIdTypeToUserIdMap(stringBuilder.toString());
    }

    /**
     * Add an ExternalUserId to the list of the user's external IDs.
     *
     * @param externalUserId The external user ID that should be added.
     */
    public void addExternalUserId(final ExternalUserIdEntity externalUserId) {
        externalUserId.setUser(this);
        getExternalUserIds().add(externalUserId);
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
        final UserEntity other = (UserEntity) obj;
        if (userId == null) {
            if (other.userId != null) {
                return false;
            }
        } else if (!userId.equals(other.userId)) {
            return false;
        }
        return true;
    }

    /**
     * Gets the status.
     *
     * @return the status
     */
    public byte getStatus() {
        return status;
    }

    /**
     * Sets the status.
     *
     * @param status the new status
     */
    public void setStatus(final byte status) {
        this.status = status;
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
     * Gets the created.
     *
     * @return the created
     */
    public Date getCreated() {
        return this.created;
    }

    /**
     * Gets the external user ids.
     *
     * @return the external user ids
     */
    public List<ExternalUserIdEntity> getExternalUserIds() {
        return externalUserIds;
    }

    /**
     * Gets the last activity.
     *
     * @return the last activity
     */
    public Date getLastActivity() {
        return this.lastActivity;
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
     * Gets the last attribute deletion time
     *
     * @return the last attribute deletion time
     */
    public Date getAttributeLastDeletion() {
        return attributeLastDeletion;
    }

    /**
     * Gets the user id.
     *
     * @return the user id
     */
    public Long getUserId() {
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
        final int prime = 31;
        int result = 1;
        result = prime * result + (userId == null ? 0 : userId.hashCode());
        return result;
    }

    /**
     * Sets the creation and last activity date to now.
     */
    @PrePersist
    private void prePersist() {
        final Date now = DateUtils.truncate(new Date(), Calendar.SECOND);
        setCreated(now);
        setLastActivity(now);
        if (lastModified == null) {
            setLastModified(now);
        }
    }

    /**
     * Sets the last modification date to now.
     */
    @PreUpdate
    private void preUpdate() {
        final Date now = DateUtils.truncate(new Date(), Calendar.SECOND);
        setLastModified(now);
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
     * Sets the created.
     *
     * @param created the new created
     */
    public void setCreated(final Date created) {
        this.created = created;
    }

    /**
     * Sets the external user ids.
     *
     * @param externalUserIds the new external user ids
     */
    public void setExternalUserIds(final List<ExternalUserIdEntity> externalUserIds) {
        this.externalUserIds = externalUserIds;
    }

    /**
     * Sets the last activity.
     *
     * @param lastActivity the new last activity
     */
    public void setLastActivity(final Date lastActivity) {
        this.lastActivity = lastActivity;
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
     * Sets the last attribute deletion time
     *
     * @param attributeLastDeletion the new last attribute deletion time
     */
    public void setAttributeLastDeletion(final Date attributeLastDeletion) {
        this.attributeLastDeletion = attributeLastDeletion;
    }

    /**
     * Sets the user id.
     *
     * @param id the new user id
     */
    public void setUserId(final Long id) {
        this.userId = id;
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
     * returns true if user was deactivated
     *
     * @return true if the user is deactivated
     */
    public Boolean getDeactivated() {
        return deactivated;
    }

    /**
     * sets deactivated flag
     *
     * @param deactivated flag
     */
    public void setDeactivated(final Boolean deactivated) {
        this.deactivated = deactivated;
    }

    /**
     * returns the timestamp of deactivation
     *
     * @return the {@link Date} of deactivation
     */
    public Date getDeactivatedOn() {
        return deactivatedOn;
    }

    /**
     * sets the timestamp of deactivation
     *
     * @param deactivatedOn the {@link Date} of deactivation
     */
    public void setDeactivatedOn(final Date deactivatedOn) {
        if (deactivatedOn != null) {
            this.deactivatedOn = DateUtils.truncate(deactivatedOn, Calendar.SECOND);
        } else if (this.deactivatedOn != null) {
            this.deactivatedOn = null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    /**
     * Sets the user attributes.
     *
     * @param userAttributes the userAttributes to set
     */
    public void setUserAttributes(final List<UserAttributeValueEntity> userAttributes) {
        this.userAttributes = userAttributes;
    }

    /**
     * Gets the user attributes.
     *
     * @return the userAttributes
     */
    public List<UserAttributeValueEntity> getUserAttributes() {
        return userAttributes;
    }

}
