package com.xxl.job.admin.dao;

import com.xxl.job.admin.core.model.KettleTransInfo;

import java.util.List;

/**
 * Created by xieyufang on 2017/7/3.
 */
public interface IKettleTransInfoDao {

    public List<KettleTransInfo> pageList(int start, int length);

    public Integer count();

    public KettleTransInfo load(Integer id);

    public List<KettleTransInfo> loadByJobId(Integer id);
}
