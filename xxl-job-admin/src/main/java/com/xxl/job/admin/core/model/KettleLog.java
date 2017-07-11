package com.xxl.job.admin.core.model;

/**
 * Created by xieyufang on 2017/7/11.
 */
public class KettleLog {

    private Integer id;
    private String status;
    private int errors;
    private String startDate;
    private String endDate;

    private String logField;

    private KettleInfo kettleInfo;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getErrors() {
        return errors;
    }

    public void setErrors(int errors) {
        this.errors = errors;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getLogField() {
        return logField;
    }

    public void setLogField(String logField) {
        this.logField = logField;
    }

    public KettleInfo getKettleInfo() {
        return kettleInfo;
    }

    public void setKettleInfo(KettleInfo kettleInfo) {
        this.kettleInfo = kettleInfo;
    }
}
