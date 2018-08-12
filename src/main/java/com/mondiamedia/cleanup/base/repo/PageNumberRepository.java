package com.mondiamedia.cleanup.base.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.mondiamedia.cleanup.base.entity.PageNumber;

public interface PageNumberRepository extends JpaRepository<PageNumber, Long> {

    @Modifying
    @Transactional
    @Query(value = "Update pagination pageNum set pageNum.PageNum = ?1 where pageNum.Id = 1", nativeQuery = true)
    void update(int pageNum);
}
