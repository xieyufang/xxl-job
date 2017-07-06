package com.xxl.job.admin.dao.impl;

import com.xxl.job.admin.core.model.KettleTransInfo;
import com.xxl.job.admin.dao.IKettleTransInfoDao;
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
public class KettleTransInfoDaoImpl implements IKettleTransInfoDao {

    @Resource
    public SqlSessionTemplate sqlSessionTemplate;

    @Override
    public List<KettleTransInfo> pageList(int offset, int pageSize) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("offset", offset);
        params.put("pageSize", pageSize);
        return sqlSessionTemplate.selectList("KettleTransInfoMapper.pageList",params);
    }

    @Override
    public Integer count() {
        return sqlSessionTemplate.selectOne("KettleTransInfoMapper.count");
    }

    @Override
    public KettleTransInfo load(Integer id) {
        return sqlSessionTemplate.selectOne("KettleTransInfoMapper.load",id);
    }

    @Override
    public List<KettleTransInfo> loadByJobId(Integer id) {
        return sqlSessionTemplate.selectList("KettleTransInfoMapper.loadByJobId",id);
    }
}
