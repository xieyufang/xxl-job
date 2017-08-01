package com.xxl.job.admin.service.impl;

import com.xxl.job.admin.core.model.KettleInfo;
import com.xxl.job.admin.core.model.KettleJobInfo;
import com.xxl.job.admin.core.model.KettleTransInfo;
import com.xxl.job.admin.dao.IKettleInfoDao;
import com.xxl.job.admin.dao.IKettleJobInfoDao;
import com.xxl.job.admin.dao.IKettleTransInfoDao;
import com.xxl.job.admin.service.IKettleInfoService;
import com.xxl.job.core.biz.model.ReturnT;
import org.apache.commons.io.FileExistsException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
    public Map<String, Object> pageList(int start, int length, String name, String type, String status) {
        List<KettleInfo> list = kettleInfoDao.pageList(start, length, name, type, status);
        Integer count = kettleInfoDao.count(name, type, status);
        Map<String, Object> maps = new HashMap<>();
        maps.put("recordsTotal", count);        // 总记录数
        maps.put("recordsFiltered", count);    // 过滤后的总记录数
        maps.put("data", list);
        return maps;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ReturnT<String> save(KettleInfo kettleInfo) throws IOException {

        if (kettleInfo.getId() < 1) {
            kettleInfoDao.insert(kettleInfo);
        } else {
            kettleInfoDao.update(kettleInfo);
            if (StringUtils.isBlank(kettleInfo.getFileNameTemp())) {
                return ReturnT.SUCCESS;
            }
        }

        File repository = new File(System.getProperty("catalina.home"), "repository");
        File temp = new File(repository, "temp");
        File file = new File(temp, kettleInfo.getFileNameTemp());
        if (file.exists()) {
            File path = new File(repository,kettleInfo.getPath());
            if(!path.exists()){
                path.mkdirs();
            }
            if (!file.renameTo(new File(path, kettleInfo.getFileName()))) {
                throw new IOException("文件复制出错");
            }
        } else {
            throw new FileNotFoundException("上传文件不存在");
        }

        if (kettleInfo.getId() < 1) {
            file.delete();
            return new ReturnT<String>(ReturnT.FAIL_CODE, "新增任务失败");
        }

        return ReturnT.SUCCESS;

    }


    @Override
    public ReturnT<String> delete(KettleInfo kettleInfo) throws IOException{

        kettleInfoDao.delete(kettleInfo.getId());

        File repository = new File(System.getProperty("catalina.home"), "repository");
        File file = new File(repository, kettleInfo.getFileName());

        if(file.exists()){
            if(!file.delete()){
                throw new IOException("文件存在，但删除失败");
            }
        }
        return ReturnT.SUCCESS;
    }

}
