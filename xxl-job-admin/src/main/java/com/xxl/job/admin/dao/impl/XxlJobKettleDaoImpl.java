package com.xxl.job.admin.dao.impl;

import com.xxl.job.admin.core.model.XxlJobInfo;
import com.xxl.job.admin.dao.IXxlJobKettleDao;

import java.util.List;

/**
 * Created by xieyufang on 17/6/28.
 */
public class XxlJobKettleDaoImpl implements IXxlJobKettleDao {

    @Override
    public List<XxlJobInfo> transPageList(int offset, int pagesize, String jobName) {
        return null;
    }

    @Override
    public List<XxlJobInfo> jobPageList(int offset, int pagesize) {
        return null;
    }

    @Override
    public int transPageListCount(int offset, int pagesize, String jobName) {
        return 0;
    }

    @Override
    public int jobPageListCount(int offset, int pagesize, int jobGroup, String executorHandler) {
        return 0;
    }

    @Override
    public XxlJobInfo loadTransLogByJobName(String jobName) {
        return null;
    }
}
