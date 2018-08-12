package com.mondiamedia.cleanup.user.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.apache.commons.lang.time.DateUtils;

/**
 * The persistent class for the liLoginInfo database table.
 *
 * @author maqu1
 */
@Entity
@Table(name = "liLoginInfo")
public class LoginInfoEntity implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "liId", unique = true, nullable = false)
    private Long id;

    /** The external user id. */
    @OneToOne
    @JoinColumn(name = "liEuiId", nullable = false)

    private ExternalUserIdEntity externalUserId;

    /** The activation code. */
    @Column(name = "liActivationCode", nullable = false)

    private String activationCode;

    /** The created date. */
    @Column(name = "liCreated", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    /** The hash type. */
    @Column(name = "liHashType", nullable = false)

    private String hashType;

    /** The last modified. */
    @Column(name = "liLastModified", nullable = false)

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;

    /** The password. */
    @Column(name = "liPassword", nullable = false)

    private String password;

    /** The salt. */
    @Column(name = "liSalt", nullable = false)

    private String salt;

    /** The status. */
    @Column(name = "liStatus", nullable = false)

    private byte status;

    /** The version. */
    @Version
    @Column(name = "liVersion", nullable = false)
    private Long version;

    /** The login count. */
    @Column(name = "liLoginCount")
    private Long loginCount;

    /** The failure login count. */
    @Column(name = "liFailureLoginCount")
    private Long failureLoginCount;

    /** The last activity. */
    @Column(name = "liLastActivity", nullable = false)

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastActivity;

    /**
     * Default constructor.
     */
    public LoginInfoEntity() {
        super();
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * Gets the external user id.
     *
     * @return the external user id
     */
    public ExternalUserIdEntity getExternalUserId() {
        return externalUserId;
    }

    /**
     * Sets the external user id.
     *
     * @param externalUserId the new external user id
     */
    public void setExternalUserId(final ExternalUserIdEntity externalUserId) {
        this.externalUserId = externalUserId;
    }

    /**
     * Gets the activation code.
     *
     * @return the activation code
     */
    public String getActivationCode() {
        return activationCode;
    }

    /**
     * Sets the activation code.
     *
     * @param activationCode the new activation code
     */
    public void setActivationCode(final String activationCode) {
        this.activationCode = activationCode;
    }

    /**
     * Gets the created date.
     *
     * @return the created date
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * Sets the created date.
     *
     * @param createdDate the new created date
     */
    public void setCreatedDate(final Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Gets the hash type.
     *
     * @return the hash type
     */
    public String getHashType() {
        return hashType;
    }

    /**
     * Sets the hash type.
     *
     * @param hashType the new hash type
     */
    public void setHashType(final String hashType) {
        this.hashType = hashType;
    }

    /**
     * Gets the last modified.
     *
     * @return the last modified
     */
    public Date getLastModified() {
        return lastModified;
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
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     *
     * @param password the new password
     */
    public void setPassword(final String password) {
        this.password = password;
    }

    /**
     * Gets the salt.
     *
     * @return the salt
     */
    public String getSalt() {
        return salt;
    }

    /**
     * Sets the salt.
     *
     * @param salt the new salt
     */
    public void setSalt(final String salt) {
        this.salt = salt;
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
     * Gets the version.
     *
     * @return the version
     */
    public Long getVersion() {
        return version;
    }

    /**
     * Sets the version.
     *
     * @param version the new version
     */
    public void setVersion(final Long version) {
        this.version = version;
    }

    /**
     * Gets the login count.
     *
     * @return the login count
     */
    public Long getLoginCount() {
        return loginCount;
    }

    /**
     * Sets the login count.
     *
     * @param loginCount the new login count
     */
    public void setLoginCount(final Long loginCount) {
        this.loginCount = loginCount;
    }

    /**
     * Gets the failure login count.
     *
     * @return the failure login count
     */
    public Long getFailureLoginCount() {
        return failureLoginCount;
    }

    /**
     * Sets the failure login count.
     *
     * @param failureLoginCount the new failure login count
     */
    public void setFailureLoginCount(final Long failureLoginCount) {
        this.failureLoginCount = failureLoginCount;
    }

    /**
     * Gets the last activity.
     *
     * @return the last activity
     */
    public Date getLastActivity() {
        return lastActivity;
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
     * Sets the creation and last activity date to now.
     */
    @PrePersist
    private void prePersist() {
        final Date now = DateUtils.truncate(new Date(), Calendar.SECOND);
        setCreatedDate(now);
        if (lastActivity == null) {
            setLastActivity(now);
        }
        if (lastModified == null) {
            setLastModified(now);
        }
        if (loginCount == null) {
            setLoginCount(Long.valueOf(0));
        }
        if (failureLoginCount == null) {
            setFailureLoginCount(Long.valueOf(0));
        }
    }

    /**
     * Sets the last modification date to now.
     */
    @PreUpdate
    private void preUpdate() {
        final Date now = DateUtils.truncate(new Date(), Calendar.SECOND);
        setLastModified(now);
        if (loginCount == null) {
            setLoginCount(Long.valueOf(0));
        }
        if (failureLoginCount == null) {
            setFailureLoginCount(Long.valueOf(0));
        }
    }
}
