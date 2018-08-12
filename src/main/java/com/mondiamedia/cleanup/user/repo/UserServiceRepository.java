/*
 * Copyright 2017 Mondia Media Group GmbH. All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Mondia Media Group GmbH ("Confidential Information").
 * You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement
 * you entered into with Mondia Media Group GmbH.
 */
package com.mondiamedia.cleanup.user.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mondiamedia.cleanup.user.entity.UserEntity;

@Repository
public interface UserServiceRepository extends JpaRepository<UserEntity, Long> {

    /**
     * Returns all user entities matching the given specification.
     *
     * @param specification the query specification
     * @param pageable the paging information
     * @return a list of subscriptions matching the specification
     */
    @Override
    Page<UserEntity> findAll(Pageable pageable);

    @Override
    List<UserEntity> findAll();

    UserEntity findByUserId(Long userId);

    /**
     * Returns all user entities matching the given specification.
     *
     * @param specification the query specification
     * @param pageable the paging information
     * @return a list of subscriptions matching the specification
     */
    @Modifying
    @Query(value = "Update usrUser user set user.userId = ?2 where user.userId = ?1", nativeQuery = true)
    void update(String userId, String updatedUserId);

    //    @Modifying
    //    @Query(value = "Update euiExternalUserId ExternalUserId set ExternalUserId.euiEuitId = ?2 and ExternalUserId.euiUserId = ?3 where ExternalUserId.euiUsrId = ?1", nativeQuery = true)
    //    void addExternalIdforUpdatedUserId(Long userId, Long externalUserIdTypeId, String externalUserIdTypeValue);

    @Modifying
    @Query(value = "delete from usrUser user where usrUser.userId = ?1", nativeQuery = true)
    void delete(String userId);
}
