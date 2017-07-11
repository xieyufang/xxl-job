package com.xxl.job.admin.service.impl;

import com.xxl.job.admin.core.model.KettleInfo;
import com.xxl.job.admin.core.model.KettleLog;
import com.xxl.job.admin.dao.IKettleLogDao;
import com.xxl.job.admin.service.IKettleLogService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xieyufang on 2017/7/11.
 */
@Service
public class KettleLogServiceImpl implements IKettleLogService {


    @Resource
    private IKettleLogDao kettleLogDao;

    @Override
    public Map<String, Object> pageList(int start, int length, String name, String type, String status, String filterTime) {
        // parse param
        Date startDate = null;
        Date endDate = null;
        if (StringUtils.isNotBlank(filterTime)) {
            String[] tempStr = filterTime.split(" - ");
            if (tempStr != null && tempStr.length == 2) {
                try {
                    endDate = DateUtils.parseDate(tempStr[1], new String[]{"yyyy-MM-dd HH:mm:ss"});
                    startDate = DateUtils.parseDate(tempStr[0], new String[]{"yyyy-MM-dd HH:mm:ss"});
                } catch (ParseException e) {
                }
            }
        }

        List<KettleLog> list = kettleLogDao.pageList(start, length, name, type, status, startDate, endDate);
        Integer count = kettleLogDao.count(name, type, status, startDate, endDate);
        Map<String, Object> maps = new HashMap<>();
        maps.put("recordsTotal", count);        // 总记录数
        maps.put("recordsFiltered", count);    // 过滤后的总记录数
        maps.put("data", list);
        return maps;
    }
}
