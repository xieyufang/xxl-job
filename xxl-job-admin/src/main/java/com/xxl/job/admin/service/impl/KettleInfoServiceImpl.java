package com.xxl.job.admin.service.impl;

import com.xxl.job.admin.core.model.KettleInfo;
import com.xxl.job.admin.core.model.KettleJobInfo;
import com.xxl.job.admin.core.model.KettleTransInfo;
import com.xxl.job.admin.dao.IKettleInfoDao;
import com.xxl.job.admin.dao.IKettleJobInfoDao;
import com.xxl.job.admin.dao.IKettleTransInfoDao;
import com.xxl.job.admin.service.IKettleInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xieyufang on 2017/7/5.
 */
@Service
public class KettleInfoServiceImpl implements IKettleInfoService {

    @Resource
    private IKettleJobInfoDao kettleJobInfoDao;

    @Resource
    private IKettleTransInfoDao kettleTransInfoDao;

    @Resource
    private IKettleInfoDao kettleInfoDao;

    @Override
    public Map<String, Object> jobPageList(int start, int length) {
        List<KettleJobInfo> list = kettleJobInfoDao.pageList(start, length);
        Integer count = kettleJobInfoDao.count();
        Map<String, Object> maps = new HashMap<>();
        maps.put("recordsTotal", count);        // 总记录数
        maps.put("recordsFiltered", count);    // 过滤后的总记录数
        maps.put("data", list);
        return maps;
    }

    @Override
    public Map<String, Object> transPageList(int start, int length) {
        List<KettleTransInfo> list = kettleTransInfoDao.pageList(start, length);
        Integer count = kettleTransInfoDao.count();
        Map<String, Object> maps = new HashMap<>();
        maps.put("recordsTotal", count);        // 总记录数
        maps.put("recordsFiltered", count);    // 过滤后的总记录数
        maps.put("data", list);
        return maps;
    }

    @Override
    public Map<String, Object> pageList(int start, int length, String type) {
        List<KettleInfo> list = kettleInfoDao.pageList(start, length, type);
        Integer count = kettleInfoDao.count(type);
        Map<String, Object> maps = new HashMap<>();
        maps.put("recordsTotal", count);        // 总记录数
        maps.put("recordsFiltered", count);    // 过滤后的总记录数
        maps.put("data", list);
        return maps;
    }
}
