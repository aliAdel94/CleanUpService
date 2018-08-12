/*
 * Copyright 2017 Mondia Media Group GmbH. All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Mondia Media Group GmbH ("Confidential Information").
 * You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement
 * you entered into with Mondia Media Group GmbH.
 */
package com.mondiamedia.cleanup.subscription.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mondiamedia.cleanup.subscription.entity.Subscription;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    /**
     * Returns all user entities matching the given specification.
     *
     * @param specification the query specification
     * @param pageable the paging information
     * @return a list of subscriptions matching the specification
     */
    @Modifying
    @Transactional
    @Query(value = "Update subscription sub set sub.userId = ?1 where sub.userId = ?2", nativeQuery = true)
    void update(String userId, String updatedUserId);

    Subscription findByUserId(String userId);

    @Query(value = "select * from subscription sub where sub.userId = ?1" , nativeQuery = true)
    List<Subscription> findAllSubscriptionForUserId(String userId);
}
