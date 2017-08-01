package com.xxl.job.admin.dao;

import com.xxl.job.admin.core.model.KettleInfo;

import java.util.List;

/**
 * Created by xieyufang on 17/7/10.
 */
public interface IKettleInfoDao {

    public List<KettleInfo> pageList(int offset, int pageSize, String name,String type,String status);

    public int count(String name,String type,String status);

    public int insert(KettleInfo kettleInfo);

    public int update(KettleInfo kettleInfo);

    public int delete(int id);
}
