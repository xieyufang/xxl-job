package com.xxl.job.admin.controller;

import com.xxl.job.admin.dao.IKettleLogDao;
import com.xxl.job.admin.service.IKettleInfoService;
import com.xxl.job.admin.service.IKettleLogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by xieyufang on 2017/7/6.
 */

@Controller
@RequestMapping("/kettleLog")
public class KettleLogController {

    @Resource
    private IKettleLogService kettleLogService;


    @RequestMapping
    public String index(Model model) {
        return "kettleLog/kettleLog.index";
    }

    @RequestMapping("/pageList")
    @ResponseBody
    public Map<String, Object> pageList(@RequestParam(required = false, defaultValue = "0") int start,
                                        @RequestParam(required = false, defaultValue = "10") int length,
                                        @RequestParam(required = false) String name,
                                        @RequestParam(required = false) String type,
                                        @RequestParam(required = false) String status,
                                        @RequestParam(required = false) String filterTime
    ) {
        return kettleLogService.pageList(start, length, name, type, status, filterTime);
    }


}
