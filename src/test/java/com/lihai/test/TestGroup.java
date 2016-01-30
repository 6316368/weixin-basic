package com.lihai.test;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.lihai.weixin.user.group.IgroupService;
import org.lihai.weixin.user.model.Wgroup;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans.xml")
public class TestGroup {
	@Inject
  private IgroupService igroupService;
	
	@Test
	public  void add(){
		Wgroup  g=new Wgroup();
		g.setId(100);
		g.setName("DEVELOPDER");
		igroupService.add(g);
	}
	@Test
	public  void testqueryallGroup(){
		List<Wgroup> quaryall = igroupService.quaryall();
		for (Wgroup wgroup : quaryall) {
			System.out.println(wgroup.getName());
		}
	}
	@Test
	public  void testUsreGroupByOpenid(){
		Wgroup g = igroupService.quaryUserGroupByOpenId("oKAtBuN6KGfNIdxdB6y3rqHtdCSY");
		System.out.println(g.getName());
	}
	
	@Test
	public  void testdeleteGroupBugroupId(){
		igroupService.deleteGroupByGroupId(104);
	}
  
}
