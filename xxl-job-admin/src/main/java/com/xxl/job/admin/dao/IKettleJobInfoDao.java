package com.xxl.job.admin.dao;

import com.xxl.job.admin.core.model.KettleJobInfo;

import java.util.List;

/**
 * Created by xieyufang on 2017/7/3.
 */
public interface IKettleJobInfoDao {

    public List<KettleJobInfo> pageList(int start, int length);

    public Integer count();

    public KettleJobInfo load(Integer id);

}
