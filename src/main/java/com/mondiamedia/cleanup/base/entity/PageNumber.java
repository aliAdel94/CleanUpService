package com.mondiamedia.cleanup.base.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Ali.Ismail
 */
@Entity(name = "pagination")
@Table(name = "pagination")
public class PageNumber {

    /** The Unique user id per identity. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", unique = true, nullable = false)
    private Long Id;

    /** The user id. */
    @Column(name = "PageNum", nullable = false)
    private int pageNum;

    /**
     * @return the id
     */
    public Long getId() {
        return Id;
    }

    /**
     * @return the pageNum
     */
    public int getPageNum() {
        return pageNum;
    }

    /**
     * @param pageNum the pageNum to set
     */
    public void setPageNum(final int pageNum) {
        this.pageNum = pageNum;
    }

}
