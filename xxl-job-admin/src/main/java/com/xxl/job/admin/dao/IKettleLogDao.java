package com.xxl.job.admin.dao;

import com.xxl.job.admin.core.model.KettleLog;

import java.util.List;

/**
 * Created by xieyufang on 2017/7/11.
 */
public interface IKettleLogDao {

    public List<KettleLog> pageList(int start, int length, String name,String type,String status);

    public Integer count( String name,String type,String status);
}
