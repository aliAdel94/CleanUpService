package com.mondiamedia.cleanup.user.entity;

/**
 * Enumeration that holds the possible types of validation strategies.
 */
public enum ValidationType {

    /**
     * Validation type for Facebook id.
     */
    FACEBOOK_ID,

    /**
     * Validation type for UUID id.
     */
    UUID,

    /**
     * The dummy validation type which always return true for validation.
     */
    NO_VALIDATION,

    /**
     * The validation which check if external user id is not blank or null.
     */
    NOT_BLANK_VALIDATION,

    /**
     * Validation type for IMEI id.
     */
    IMEI,

    /**
     * Validation type for email id.
     */
    EMAIL,

    /**
     * Validation type for MSISDN id.
     */
    MSISDN,

    /**
     * Validation type for my pass sso id.
     */
    SSO,

    /**
     * Validation type for any numeric only id. Used for VF_CONTRACT_ID
     */
    NUMERIC,

    /**
     * Validation type for SHA-1 Hash.
     */
    SHA1_HASH;

}
