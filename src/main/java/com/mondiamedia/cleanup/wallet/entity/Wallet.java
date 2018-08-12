/*
 * Copyright 2009 arvato mobile GmbH. All rights reserved.
 * This software is the confidential and proprietary information
 * of arvato mobile GmbH ("Confidential Information").
 * You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement
 * you entered into with arvato mobile GmbH.
 */
package com.mondiamedia.cleanup.wallet.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;


/**
 * Represents a users wallet in the context of an affiliate. A wallet contains all licenses for content items the user
 * has purchased.
 *
 * @author WOROF01
 */
@Entity(name = "Wallet")
@Table(name = "wallet")
@Proxy(lazy = false)
public class Wallet {

    /** wallet id. */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name= "walletId")
    private Long id;

    /** user the wallet belongs to. */
    @Column(nullable = false)
    private String userId;

    /**
     * Returns the user id of this wallet.
     *
     * @return the userId
     */
    public String getUserId() {
        return this.userId;
    }

    /**
     * Sets the user id of this wallet.
     *
     * @param userId the userId to set
     */
    public void setUserId(final String userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }
}
