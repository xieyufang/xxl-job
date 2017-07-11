package com.xxl.job.admin.dao.impl;

import com.xxl.job.admin.core.model.KettleInfo;
import com.xxl.job.admin.dao.IKettleInfoDao;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xieyufang on 17/7/10.
 */
@Repository
public class KettleInfoDaoImpl implements IKettleInfoDao{

    @Resource
    public SqlSessionTemplate sqlSessionTemplate;
    @Override
    public List<KettleInfo> pageList(int offset, int pageSize, String type) {

        Map<String,Object> params = new HashMap<String,Object>();
        params.put("offset", offset);
        params.put("pageSize", pageSize);
        params.put("type",type);
        return sqlSessionTemplate.selectList("KettleInfoMapper.pageList",params);
    }

    @Override
    public Integer count(String type) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("type",type);
        return sqlSessionTemplate.selectOne("KettleInfoMapper.count",type);
    }


}
