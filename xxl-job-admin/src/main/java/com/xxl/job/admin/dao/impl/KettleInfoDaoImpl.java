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
    public List<KettleInfo> pageList(int offset, int pageSize, String name,String type,String status) {

        Map<String,Object> params = new HashMap<String,Object>();
        params.put("offset", offset);
        params.put("pageSize", pageSize);
        params.put("name",name);
        params.put("type",type);
        params.put("status",status);
        return sqlSessionTemplate.selectList("KettleInfoMapper.pageList",params);
    }

    @Override
    public int count(String name,String type,String status) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("name",name);
        params.put("type",type);
        params.put("status",status);
        return sqlSessionTemplate.selectOne("KettleInfoMapper.count",params);
    }

    @Override
    public int insert(KettleInfo kettleInfo) {
        return sqlSessionTemplate.insert("KettleInfoMapper.insert",kettleInfo);
    }

    @Override
    public int update(KettleInfo kettleInfo) {
        return sqlSessionTemplate.update("KettleInfoMapper.update",kettleInfo);
    }

    @Override
    public int delete(int id) {
        return sqlSessionTemplate.delete("KettleInfoMapper.delete",id);
    }
}
