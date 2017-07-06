package com.xxl.job.admin.service;

import com.xxl.job.admin.core.model.KettleJobInfo;
import com.xxl.job.admin.core.model.KettleTransInfo;

import java.util.List;

/**
 * Created by xieyufang on 2017/7/5.
 */
public interface IKettleInfoService {

    public List<KettleJobInfo> jobPageList(int start, int length);

    public List<KettleTransInfo> transPageList(int start, int length);
}
