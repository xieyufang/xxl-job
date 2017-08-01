package com.xxl.job.admin.service;

import com.xxl.job.admin.core.model.KettleInfo;
import com.xxl.job.admin.core.model.KettleJobInfo;
import com.xxl.job.admin.core.model.KettleTransInfo;
import com.xxl.job.core.biz.model.ReturnT;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by xieyufang on 2017/7/5.
 */
public interface IKettleInfoService {

    public Map<String,Object> jobPageList(int start, int length);

    public Map<String,Object> transPageList(int start, int length);

    public Map<String,Object> pageList(int start, int length,String name,String type,String status);

    public ReturnT<String> save(KettleInfo kettleInfo) throws IOException;

    public ReturnT<String> delete(KettleInfo kettleInfo) throws IOException;
}
