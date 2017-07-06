package com.xxl.job.admin.dao.impl;

import com.xxl.job.admin.core.model.KettleJobInfo;
import com.xxl.job.admin.dao.IKettleJobInfoDao;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;


import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xieyufang on 2017/7/3.
 */

@Repository
public class KettleJobInfoDaoImpl implements IKettleJobInfoDao {

    @Resource
    public SqlSessionTemplate sqlSessionTemplate;

    @Override
    public List<KettleJobInfo> pageList(int offset, int pageSize) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("offset", offset);
        params.put("pageSize", pageSize);
        return sqlSessionTemplate.selectList("KettleJobInfoMapper.pageList",params);
    }

    @Override
    public Integer count() {
        return sqlSessionTemplate.selectOne("KettleJobInfoMapper.count");
    }

    @Override
    public KettleJobInfo load(Integer id) {
        return sqlSessionTemplate.selectOne("KettleJobInfoMapper.load",id);
    }
}
