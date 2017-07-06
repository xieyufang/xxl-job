package com.xxl.job.admin.core.model;

import java.util.Date;
import java.util.List;

/**
 * Created by xieyufang on 17/6/28.
 */
public class KettleJobInfo {

    private int id;
    private int idDirectory;
    private String name;
    private String description;
    private String extendedDescription;
    private int version;
    private int status;
    private Date createDate;
    private Date modifiedDate;


    private List<KettleTransInfo> kettleTransInfoList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdDirectory() {
        return idDirectory;
    }

    public void setIdDirectory(int idDirectory) {
        this.idDirectory = idDirectory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExtendedDescription() {
        return extendedDescription;
    }

    public void setExtendedDescription(String extendedDescription) {
        this.extendedDescription = extendedDescription;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public List<KettleTransInfo> getKettleTransInfoList() {
        return kettleTransInfoList;
    }

    public void setKettleTransInfoList(List<KettleTransInfo> kettleTransInfoList) {
        this.kettleTransInfoList = kettleTransInfoList;
    }
}
