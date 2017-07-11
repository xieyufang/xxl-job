package com.xxl.job.dao.impl;

import com.xxl.job.admin.core.model.KettleJobInfo;
import com.xxl.job.admin.core.model.KettleLog;
import com.xxl.job.admin.core.model.XxlJobInfo;
import com.xxl.job.admin.dao.IKettleJobInfoDao;
import com.xxl.job.admin.dao.IKettleLogDao;
import com.xxl.job.admin.dao.IKettleTransInfoDao;
import com.xxl.job.admin.dao.IXxlJobInfoDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/applicationcontext-*.xml")
public class XxlJobInfoTest {
	
	@Resource
	private IXxlJobInfoDao xxlJobInfoDao;

	@Resource
	private IKettleTransInfoDao kettleTransInfoDao;
	@Resource
	private IKettleJobInfoDao kettleJobInfoDao;
	@Resource
	private IKettleLogDao kettleLogDao;

	@Test
	public void kettleLogTest(){
		List<KettleLog> list = kettleLogDao.pageList(0,10,"企业","KETTLE_TRANS","end");

		Integer count = kettleLogDao.count("test","KETTLE_TRANS","end");
	}

	@Test
	public void kettleTest(){
		KettleJobInfo jobInfo = kettleJobInfoDao.load(1);

		System.out.println(jobInfo.getDescription());
		System.out.println(jobInfo.getKettleTransInfoList().size());
	}

	@Test
	public void pageList(){
		List<XxlJobInfo> list = xxlJobInfoDao.pageList(0, 20, 0, null);
		int list_count = xxlJobInfoDao.pageListCount(0, 20, 0, null);
		
		System.out.println(list);
		System.out.println(list_count);
	}
	
	@Test
	public void save_load(){
		XxlJobInfo info = new XxlJobInfo();
		info.setJobGroup(1);
		info.setJobCron("jobCron");
		int count = xxlJobInfoDao.save(info);
		System.out.println(count);
		System.out.println(info.getId());

		XxlJobInfo item = xxlJobInfoDao.loadById(2);
		System.out.println(item);
	}
	
	@Test
	public void update(){
		XxlJobInfo item = xxlJobInfoDao.loadById(2);
		
		item.setJobCron("jobCron2");
		xxlJobInfoDao.update(item);
	}
	
}
