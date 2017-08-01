package com.xxl.job.admin.controller;

import com.xxl.job.admin.core.model.KettleInfo;
import com.xxl.job.admin.service.IKettleInfoService;
import com.xxl.job.core.biz.model.ReturnT;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
                                        @RequestParam(required = false) String name,
                                        @RequestParam(required = false) String type,
                                        @RequestParam(required = false) String status
                                        ){

        return kettleInfoService.pageList(start,length,name,type,status);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/upload")
    @ResponseBody
    public ReturnT<String> upload(@RequestParam(value = "file",required = true) MultipartFile file) throws IOException {
        File temp = new File(System.getProperty("catalina.home"),"repository/temp");
        if(!temp.exists()){
            temp.mkdirs();
        }

        String fileNameTemp = UUID.randomUUID().toString().toUpperCase();

        file.transferTo(new File(temp, fileNameTemp));
        Map<String, Object> map = new HashMap<String, Object>();

        return new ReturnT<>(fileNameTemp);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    @ResponseBody
    public ReturnT<String> add(KettleInfo kettleInfo) throws IOException{
        return kettleInfoService.save(kettleInfo);
    }

}
