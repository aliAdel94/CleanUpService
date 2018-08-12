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

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 * The persistent class for the uatUserAttribute database table.
 *
 * @author maqu1
 */
@Entity
@Table(name = "uatUserAttribute")

public class UserAttributeValueEntity implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uatId", unique = true, nullable = false)
    private Long id;

    /** The user. */
    @ManyToOne
    @JoinColumn(name = "uatUsrId", nullable = false)

    private UserEntity user;

    /** The created date. */
    @Column(name = "uatCreated", nullable = false)

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    /** The user attribute key. */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "uatUatkId", nullable = false)

    private UserAttributeKeyEntity userAttributeKey;

    /** The last modified. */
    @Column(name = "uatLastModified", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)

    private Date lastModified;

    /** The value. */
    @Column(name = "uatValue", nullable = false)

    private String value;

    /** The version. */
    @Version
    @Column(name = "uatVersion", nullable = false)
    private Long version;

}
