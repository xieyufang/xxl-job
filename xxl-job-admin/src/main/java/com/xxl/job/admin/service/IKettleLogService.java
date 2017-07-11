package com.xxl.job.admin.service;

import java.util.Map;

/**
 * Created by xieyufang on 2017/7/11.
 */
public interface IKettleLogService {

    public Map<String,Object> pageList(int start, int length, String name, String type, String status,String filterTime);
}
