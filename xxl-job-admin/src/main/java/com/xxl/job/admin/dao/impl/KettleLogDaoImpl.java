package com.xxl.job.admin.dao.impl;

import com.xxl.job.admin.core.model.KettleLog;
import com.xxl.job.admin.dao.IKettleLogDao;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xieyufang on 2017/7/11.
 */
@Repository
public class KettleLogDaoImpl implements IKettleLogDao{

    @Resource
    public SqlSessionTemplate sqlSessionTemplate;
    @Override
    public List<KettleLog> pageList(int offset, int pageSize, String name, String type, String status,Date startDate,Date endDate) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("offset", offset);
        params.put("pageSize", pageSize);
        params.put("name",name);
        params.put("type",type);
        params.put("status",status);
        params.put("startDate",startDate);
        params.put("endDate",endDate);
        System.out.println(startDate);
        return sqlSessionTemplate.selectList("KettleLogMapper.pageList",params);
    }

    @Override
    public Integer count(String name, String type, String status,Date startDate,Date endDate) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("name",name);
        params.put("type",type);
        params.put("status",status);
        params.put("startDate",startDate);
        params.put("endDate",endDate);
        return sqlSessionTemplate.selectOne("KettleLogMapper.count",params);
    }
}
