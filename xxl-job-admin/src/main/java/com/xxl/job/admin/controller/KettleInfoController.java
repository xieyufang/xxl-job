package com.xxl.job.admin.controller;

import com.xxl.job.admin.core.enums.ExecutorFailStrategyEnum;
import com.xxl.job.admin.core.model.KettleJobInfo;
import com.xxl.job.admin.core.model.XxlJobGroup;
import com.xxl.job.admin.core.route.ExecutorRouteStrategyEnum;
import com.xxl.job.admin.service.IKettleInfoService;
import com.xxl.job.core.enums.ExecutorBlockStrategyEnum;
import com.xxl.job.core.glue.GlueTypeEnum;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xieyufang on 2017/7/6.
 */

@Controller
@RequestMapping("/kettleInfo")
public class KettleInfoController {

    @Resource
    private IKettleInfoService kettleInfoService;


    @RequestMapping
    public String index(Model model) {
        return "kettleInfo/kettleInfo.index";
    }

    @RequestMapping("/job/pageList")
    @ResponseBody
    public Map<String, Object> jobPageList(@RequestParam(required = false, defaultValue = "0") int start,
                                           @RequestParam(required = false, defaultValue = "10") int length){
        return  kettleInfoService.jobPageList(start,length);


    }

    @RequestMapping("/trans/pageList")
    @ResponseBody
    public Map<String, Object> transPageList(@RequestParam(required = false, defaultValue = "0") int start,
                                             @RequestParam(required = false, defaultValue = "10") int length){
        return kettleInfoService.transPageList(start,length);
    }

    @RequestMapping("/pageList")
    @ResponseBody
    public Map<String, Object> pageList(@RequestParam(required = false, defaultValue = "0") int start,
                                        @RequestParam(required = false, defaultValue = "10") int length,
                                        @RequestParam(required = false) String type
                                        ){
        return kettleInfoService.pageList(start,length,type);
    }

}
