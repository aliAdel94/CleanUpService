package com.mondiamedia.cleanup.user.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mondiamedia.cleanup.user.entity.ExternalUserIdEntity;

/**
 * ExternalUserIdDao interface that is automatically instantiated via spring data.
 *
 * @author jtammen
 */
public interface ExternalUserIdDao extends JpaRepository<ExternalUserIdEntity, Long> {

    /**
     * Select an external user ID by it's ID value.
     *
     * @param userId The external user ID.
     * @return An external user ID or null if no entry for the given ID could be found.
     */
    ExternalUserIdEntity findByUserId(String userId);
}