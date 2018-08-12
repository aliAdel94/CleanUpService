package com.mondiamedia.cleanup.base.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "cleanup_report")
@Table(name = "cleanup_report")
public class CleanUpReportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", unique = true, nullable = false)
    private Long id;

    /** The user id. */
    @Column(name = "userId", nullable = false)
    private String userId;

    /** The duplicate users id. */
    @Column(name = "duplictaeUsersId", nullable = false)
    private String duplictaeUsersId;

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @return the duplictaeUsersId
     */
    public String getDuplictaeUsersId() {
        return duplictaeUsersId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(final String userId) {
        this.userId = userId;
    }

    /**
     * @param duplictaeUsersId the duplictaeUsersId to set
     */
    public void setDuplictaeUsersId(final String duplictaeUsersId) {
        this.duplictaeUsersId = duplictaeUsersId;
    }

}
