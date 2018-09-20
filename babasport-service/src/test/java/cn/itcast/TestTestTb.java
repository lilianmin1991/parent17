package cn.itcast;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.itcast.common.junit.SpringJunitTest;
import cn.itcast.core.bean.TestTb;
import cn.itcast.core.service.TestTbService;

/**
 * 测试 Junit 
 * @author lx
 *
 */

public class TestTestTb extends SpringJunitTest{

	@Autowired
	private TestTbService testTbService;
	@Test
	public void testAdd() throws Exception {
		TestTb testTb = new TestTb();
		testTb.setName("范冰冰33");
		
		testTbService.insertTestTb(testTb);;
	}
	
}
