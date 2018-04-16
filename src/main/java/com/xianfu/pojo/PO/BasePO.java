package com.xianfu.pojo.PO;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

/**
 * @author Created by xianfuWang
 * @version 2018/4/13.
 */
public class BasePO {

    private Date createdAt;

    private Date updatedAt;

    private String createdBy;

    private String updatedBy;

    private String archive;

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getArchive() {
        return archive;
    }

    public void setArchive(String archive) {
        this.archive = archive;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
