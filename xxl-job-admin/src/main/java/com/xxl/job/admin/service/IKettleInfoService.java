package com.xxl.job.admin.service;

import com.xxl.job.admin.core.model.KettleJobInfo;
import com.xxl.job.admin.core.model.KettleTransInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by xieyufang on 2017/7/5.
 */
public interface IKettleInfoService {

    public Map<String,Object> jobPageList(int start, int length);

    public Map<String,Object> transPageList(int start, int length);

    public Map<String,Object> pageList(int start, int length,String type);
}
