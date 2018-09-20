package cn.itcast.core.service;

import java.util.List;

import cn.itcast.core.bean.TestTb;

public interface TestTbService {
	
	//保存
	public void insertTestTb(TestTb testTb);
	
	//查询
	public List<TestTb> selectTestTbList();

}
