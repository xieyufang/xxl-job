package com.xxl.job.admin.dao;

import com.xxl.job.admin.core.model.KettleInfo;

import java.util.List;

/**
 * Created by xieyufang on 17/7/10.
 */
public interface IKettleInfoDao {

    public List<KettleInfo> pageList(int start, int length, String name,String type,String status);

    public Integer count(String name,String type,String status);
}
