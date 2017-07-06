package com.xxl.job.admin.service.impl;

import com.xxl.job.admin.core.model.KettleJobInfo;
import com.xxl.job.admin.core.model.KettleTransInfo;
import com.xxl.job.admin.dao.IKettleJobInfoDao;
import com.xxl.job.admin.dao.IKettleTransInfoDao;
import com.xxl.job.admin.service.IKettleInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xieyufang on 2017/7/5.
 */
@Service
public class KettleInfoServiceImpl implements IKettleInfoService {

    @Resource
    private IKettleJobInfoDao kettleJobInfoDao;

    @Resource
    private IKettleTransInfoDao kettleTransInfoDao;


    @Override
    public List<KettleJobInfo> jobPageList(int start, int length) {
        return null;
    }

    @Override
    public List<KettleTransInfo> transPageList(int start, int length) {
        return null;
    }
}
