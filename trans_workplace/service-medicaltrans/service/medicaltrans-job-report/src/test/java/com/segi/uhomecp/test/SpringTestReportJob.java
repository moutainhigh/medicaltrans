package com.segi.uhomecp.test;

import javax.annotation.Resource;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import com.segi.uhomecp.medicaltrans.reportjob.inf.JobExecute;

@ContextConfiguration(locations = { "classpath:spring/spring-common.xml" })
@RunWith(JUnit4ClassRunner.class)
public class SpringTestReportJob {
	
	@Resource(name="reportJobExecute")
	JobExecute jobExecute;
	
//	@Test
//	public void test() throws ParseException  {
//		jobExecute.execute();
//	}

}
