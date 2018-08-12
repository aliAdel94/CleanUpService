package com.mondiamedia.cleanup.user.entity;

import java.util.Date;

/**
 * Utils for calculating default delete date.
 */
public class DeleteDateUtils {

    /**
     * Entities when deleted should have set delete date to value '1970-01-01 01:00:00' in UTC.
     */
    public final static long DELETE_DATE_MS = 1L * 60L * 60L * 1000L;

    /**
     * Create Date with value '1970-01-01 01:00:00' in UTC. That date say that entity is NOT deleted. It is assumed that
     * server is working in UTC!
     * 
     * @return date '1970-01-01 01:00:00' in UTC
     */
    public static Date getDefaultDeletedDate() {
        return new Date(DELETE_DATE_MS);
    }
}
