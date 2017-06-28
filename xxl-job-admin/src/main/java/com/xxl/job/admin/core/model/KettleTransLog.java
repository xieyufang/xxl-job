package com.xxl.job.admin.core.model;

import java.util.Date;

/**
 * Created by xieyufang on 17/6/28.
 */
public class KettleTransLog {

    private int idBatch;
    private String channelId;
    private String status;
    private int errors;
    private Date startDate;
    private Date endDate;
    private Date logDate;
    private Date depDate;
    private Date replayDate;
    private String logField;

    public int getIdBatch() {
        return idBatch;
    }

    public void setIdBatch(int idBatch) {
        this.idBatch = idBatch;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getLogDate() {
        return logDate;
    }

    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }

    public Date getDepDate() {
        return depDate;
    }

    public void setDepDate(Date depDate) {
        this.depDate = depDate;
    }

    public Date getReplayDate() {
        return replayDate;
    }

    public void setReplayDate(Date replayDate) {
        this.replayDate = replayDate;
    }

    public String getLogField() {
        return logField;
    }

    public void setLogField(String logField) {
        this.logField = logField;
    }
}
