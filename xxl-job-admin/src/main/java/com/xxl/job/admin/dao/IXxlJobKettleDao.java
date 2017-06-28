package com.xxl.job.admin.dao;

import com.xxl.job.admin.core.model.XxlJobInfo;

import java.util.List;

/**
 * Created by xieyufang on 17/6/28.
 */
public interface IXxlJobKettleDao {


    public List<XxlJobInfo> transLogPageList(int offset, int pagesize,String jobName);
    public List<XxlJobInfo> jobLogPageList(int offset, int pagesize);


    public int transLogPageListCount(int offset, int pagesize,String jobName);
    public int jobLogPageListCount(int offset, int pagesize, int jobGroup, String executorHandler);


    public XxlJobInfo loadTransLogByJobName(String jobName);

}
