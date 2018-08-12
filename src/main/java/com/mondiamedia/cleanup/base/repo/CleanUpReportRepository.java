package com.mondiamedia.cleanup.base.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.mondiamedia.cleanup.base.entity.CleanUpReportEntity;

public interface CleanUpReportRepository extends JpaRepository<CleanUpReportEntity, Long> {

    CleanUpReportEntity findByUserId(String userId);

    @Modifying
    @Transactional
    @Query(value = "Update cleanup_report clean set clean.duplictaeUsersId = ?2 where clean.userId = ?1", nativeQuery = true)
    void update(String userId, String duplicateUsersId);
}
