package com.xxl.job.admin.controller;

import com.xxl.job.admin.service.IKettleInfoService;
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
    private IKettleInfoService kettleInfoService;


    @RequestMapping
    public String index(Model model) {
        return "kettleLog/kettleLog.index";
    }

    @RequestMapping("/pageList")
    @ResponseBody
    public Map<String, Object> pageList(@RequestParam(required = false, defaultValue = "0") int start,
                                           @RequestParam(required = false, defaultValue = "10") int length){
        return  kettleInfoService.jobPageList(start,length);


    }



}
