/*
 * Copyright 2012 arvato mobile GmbH. All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of arvato mobile GmbH ("Confidential Information").
 * You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement
 * you entered into with arvato mobile GmbH.
 */
package com.mondiamedia.cleanup.user.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * The persistent class for the uatkUserAttributeKey database table.
 *
 * @author ANTU1
 */
@Entity
@Table(name = "uatkUserAttributeKey", uniqueConstraints = @UniqueConstraint(columnNames = { "uatkKey" }))
public class UserAttributeKeyEntity implements Serializable {

    /**
     * The Constants serialVersionUID.
     */
    private static final long serialVersionUID = 6671981010326226227L;

    /** The id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uatkId", unique = true, nullable = false)
    private Long id;

    /** The key. */
    @Column(name = "uatkKey", length = 255, nullable = false, unique = true)

    private String key;

    /**
     * Default constructor.
     */
    public UserAttributeKeyEntity() {
        super();
    }

    /**
     * Instantiates a new user attribute key entity.
     *
     * @param id the id
     * @param key the key
     */
    public UserAttributeKeyEntity(final Long id, final String key) {
        this.id = id;
        this.key = key;
    }

    /**
     * Sets the id.
     *
     * @param id the id to set
     */
    public void setId(final Long id) {
        this.id = id;
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
     * Sets the key.
     *
     * @param key the key to set
     */
    public void setKey(final String key) {
        this.key = key;
    }

    /**
     * Gets the key.
     *
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (id == null ? 0 : id.hashCode());
        return result;
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
        if (!(obj instanceof UserAttributeKeyEntity)) {
            return false;
        }
        final UserAttributeKeyEntity other = (UserAttributeKeyEntity) obj;
        if (id == null) {
            if (other.getId() != null) {
                return false;
            }
        } else if (!id.equals(other.getId())) {
            return false;
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("UserAtttributeKey [");
        if (id != null) {
            builder.append("id=");
            builder.append(id);
            builder.append(", ");
        }
        if (key != null) {
            builder.append("key=");
            builder.append(key);
        }
        builder.append("]");
        return builder.toString();
    }

}
