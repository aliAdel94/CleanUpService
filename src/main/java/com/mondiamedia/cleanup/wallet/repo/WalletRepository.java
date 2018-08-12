/*
 * Copyright 2017 Mondia Media Group GmbH. All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Mondia Media Group GmbH ("Confidential Information").
 * You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement
 * you entered into with Mondia Media Group GmbH.
 */
package com.mondiamedia.cleanup.wallet.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.mondiamedia.cleanup.wallet.entity.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

    /**
     * Returns all user entities matching the given specification.
     *
     * @param specification the query specification
     * @param pageable the paging information
     * @return a list of subscriptions matching the specification
     */
    @Modifying
    @Transactional
    @Query(value = "Update Wallet wallet set wallet.userid = ?1 where wallet.userid = ?2", nativeQuery = true)
    void update(String userId, String updatedUserId);

    Wallet findByUserId(String userId);

    @Query(value = "select * from wallet wallet where wallet.userId = ?1" , nativeQuery = true)
    List<Wallet> findAllWalletForUserId(String userId);
}
