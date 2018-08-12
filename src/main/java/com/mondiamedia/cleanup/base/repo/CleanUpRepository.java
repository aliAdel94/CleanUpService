package com.mondiamedia.cleanup.base.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.mondiamedia.cleanup.base.entity.CleanUpEntity;

public interface CleanUpRepository extends JpaRepository<CleanUpEntity, Long> {

    CleanUpEntity findByAffiliateIdAndExternalUserTypeIdAndExternalUserTypeValue(Long affiliateId, Long externalUserType,
            String externalUserTypeValue);

    @Modifying
    @Query(value = "insert into cleanup (userId, affiliateId, externalUserTypeId, externalUserTypeValue) VALUES (?1, ?2, ?3, ?4)", nativeQuery = true)
    void insertEntity(Long userId, Long affiliateId, Long externalUserTypeId, String externalUserTypeValue);

    List<CleanUpEntity> findByUserId(Long userId);

    CleanUpEntity findByAffiliateIdAndExternalUserTypeIdAndExternalUserTypeValueAndUserId(Long affiliateId, Long externalUserType,
            String externalUserTypeValue, Long userId);

    @Modifying
    @Transactional
    @Query(value = "delete from cleanup clean where clean.userId = ?4 and clean.affiliateId = ?1 and clean.externalUserTypeId = ?2 and clean.externalUserTypeValue = ?3", nativeQuery = true)
    void deleteByAffiliateIdAndExternalUserTypeIdAndExternalUserTypeValueAndUserId(Long affiliateId, Long externalUserTypeId, String externalUserTypeValue, Long userId);


    @Modifying
    @Transactional
    @Query(value = "delete from cleanup clean where clean.userId = ?1", nativeQuery = true)
    void deleteByUserId(Long userId);
}
