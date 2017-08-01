package com.xxl.job.admin.core.model;

import java.util.Date;

/**
 * Created by xieyufang on 17/7/10.
 */
public class KettleInfo {

    private int id;

    private String fileName;

    private String fileNameTemp;

    private String path;

    private String description;

    private String kettleType;

    private String kettleStatus;

    private Date createdDate;

    private Date modifiedDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileNameTemp() {
        return fileNameTemp;
    }

    public void setFileNameTemp(String fileNameTemp) {
        this.fileNameTemp = fileNameTemp;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKettleType() {
        return kettleType;
    }

    public void setKettleType(String kettleType) {
        this.kettleType = kettleType;
    }

    public String getKettleStatus() {
        return kettleStatus;
    }

    public void setKettleStatus(String kettleStatus) {
        this.kettleStatus = kettleStatus;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
